/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Sep 21, 2005$
 *
 * Copyright (c) 2005 unartig AG  --  All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 *************************************************
 * $Log$
 * Revision 1.1  2007/03/01 18:23:42  alex
 * initial commit maven setup no history
 *
 * Revision 1.6  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.5  2006/11/05 12:15:45  alex
 * comments
 *
 * Revision 1.4  2006/11/05 12:04:09  alex
 * changing error handling. rollback runs now smoother with less stacktrace. not yet fully tested
 *
 * Revision 1.3  2006/05/02 20:39:44  alex
 * removing verbose system out
 *
 * Revision 1.2  2005/10/26 14:34:32  alex
 * first version of album overview
 * new mappings in struts for the /album/** url
 *
 * Revision 1.1  2005/09/26 18:37:45  alex
 * *** empty log message ***
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.util;

import ch.unartig.exceptions.UAPersistenceException;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


public class HibernateUtil
{
    private static SessionFactory sessionFactory;

    private static final ThreadLocal service = new ThreadLocal();
    private static final ThreadLocal session = new ThreadLocal();
    private static final ThreadLocal transaction = new ThreadLocal();
    private static final String _MYOWN = "sKmArKeR";
    private static final long _SESSION_FACTORY_WAIT_TIME = 2000; // 10 seconds

    /**
     * Tries to create SessionFactory and logs success or failure.
     * Must be called on web application startup.
     */
    public static synchronized void init()
    {
        lazyinit();
        // we defer init to lazyinit which will be called ondemand
    }

    /**
     * Tries to create SessionFactory and logs success or failure.
     * Must be called on web application startup.
     */
    private static synchronized void lazyinit()
    {
        try
        {
            if (sessionFactory == null)
            {
                System.out.println("Going to create SessionFactory");
                sessionFactory = new Configuration().configure().buildSessionFactory();
                System.out.println("Hibernate could create SessionFactory");
            }
        } catch (Throwable t)
        {
            System.out.println("Hibernate could not create SessionFactory");
            t.printStackTrace();
        }
    }

    /**
     * Tries to destroy SessionFactory and logs success or failure.
     * Must be called on web application shutdown.
     */
    public static synchronized void destroy()
    {
        try
        {
            if (sessionFactory != null)
            {
                sessionFactory.close();
                System.out.println("Hibernate could destroy SessionFactory");
            }
        } catch (Throwable t)
        {
            System.out.println("Hibernate could not destroy SessionFactory");
            t.printStackTrace();
        }
        sessionFactory = null;
    }

    /**
     * Does nothing actually, DB connection is acquired on-demand.
     *
     * @see #currentSession
     * @param request Servlet Request
     */
    public static void enterService(HttpServletRequest request)
    {
        if (request.getAttribute(_MYOWN) != null && ((String) request.getAttribute(_MYOWN)).length() != 0)
        {
            return;
        }
        request.setAttribute(_MYOWN, "hibernatefilter");
        if (service.get() == null)
        {
            //            System.out.println("############Entering service setting true "+service.get());
            service.set(Boolean.TRUE); //just something
        } else
        {
            //            System.out.println("$$$$$$$$$$$$$$$$$$$$$ holds lock throwing exception ");
            throw new IllegalStateException("Thread holds service lock.");
        }
    }

    /**
     * If DB connection was acquired, DB connection is released.
     * @param request Servlet Request
     * @throws ch.unartig.exceptions.UAPersistenceException Exception
     */
    public static void leaveService(HttpServletRequest request) throws UAPersistenceException
    {
        //        if(request.getAttribute(_MYOWN) != null && ((String) request.getAttribute(_MYOWN)).length() != 0)
        //        {
        request.removeAttribute(_MYOWN);
        //        }
        if (service.get() != null)
        {
            //            System.out.println("%%%%%%%%%%%%%leaving service setting null "+service.get());
            service.set(null); //just null
        } else
        {
            //            System.out.println("@@@@@@@@@@@@@@@@@@@@ holds no lock throwing exception");
            //            throw new IllegalStateException("Thread holds no service lock.");
        }

        // check if seesion exists and close
        //            System.out.println("HibernateFilter.doFilter: post-chain, trying to close hibernate session if exists");
        if (HibernateUtil.sessionExists())
        {
            HibernateUtil.closeSession();
        }
    }

    public static Session currentSession() throws UAPersistenceException
    {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null)
        {
            SessionFactory sf; //we are unsynced, use stack var for calls
            sf = sessionFactory;
            if (sf == null)
            {
                // todo wait here for sessionFactory ????
                try
                {
                    Thread.sleep(_SESSION_FACTORY_WAIT_TIME);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //retry init
                lazyinit();
            }
            sf = sessionFactory;
            if (sf == null)
            {
                //give up
                throw new UAPersistenceException("problems getting session factory, see stack trace");
            }
            try
            {
                s = sf.openSession();
//                System.out.println("HibernateUtil.currentSession: session opened");
            } catch (HibernateException e)
            {
                throw new UAPersistenceException("problems getting current session, see stack trace", e);
            }
            session.set(s);
        }
        return s;
    }

    /**
     * @return true or false
     */
    public static boolean sessionExists()
    {
        Session s = (Session) session.get();
        return s != null;
    }

    /**
     * this closes an active session. CAUTION: it assumes that the session closes gracefully!
     * <br> for ungraceful closure of the session do not use flush() !
     *
     * @throws UAPersistenceException unexpected Hibernate Problems during close session
     */
    public static void closeSession() throws UAPersistenceException
    {
        Session s = (Session) session.get();
        if (s != null)
        {
            session.set(null);
            try
            {
                if (transaction.get() != null)
                {
                    System.out.println("ERROR:    Open Transaction while trying to close session; Rolling Back Tranaction; Exception will be thrown");
                    try
                    {
                        rollbackTransaction();
                    } finally
                    {
                        //ensure that this http request does not cause side-effect in next http request on this thread.
                        transaction.set(null);
                    }
                    // todo: this exception cannot be seen in the errorlog or standard out
                    System.out.println("HibernateUtil.closeSession");
                    Thread.dumpStack();
                    throw new UAPersistenceException("Open Transaction while trying to close session");
                }
                try
                {
                    if (s.isOpen())
                    {
                        // this is what we expect
//                        System.out.println("HibernateUtil.closeSession: flushing and closing the open session");
                        s.flush();
                        s.close();
                    }
                } catch (HibernateException e)
                {
                    throw new UAPersistenceException("problem while flushing and closing session, see stack trace", e);
                }
            } finally
            {
                try
                {
                    if (s.isOpen())
                    {
                        s.close();
                    }
                } catch (HibernateException e)
                {
                    System.out.println("***** UNRECOVERABLE ERROR WHILE CLOSING SESSION ****");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void beginTransaction() throws UAPersistenceException
    {
        Transaction tx = (Transaction) transaction.get();
        //        System.out.println("HibernateUtil.beginTransaction: " + tx);
        try
        {

            if (tx == null)
            {
                transaction.set(currentSession().beginTransaction());

            } else
            {
                rollbackTransaction();
                throw new IllegalStateException("Unable to open Transaction; Transaction already open, Rollback");
            }
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("problem getting a transaction, see stack trace", e);
        }
    }

    public static void commitTransaction() throws UAPersistenceException
    {
        Transaction tx = (Transaction) transaction.get();
        //        System.out.println("HibernateUtil.commitTransaction: " + tx);
        if (tx != null)
        {
            try
            {
                //                System.out.println("Going to Commit Transaction");
                tx.commit();
                transaction.set(null);
                // added AJO 20050207: make sure the session the session is closed after the transaction
                closeSession();
            } catch (HibernateException e)
            {
                System.out.println("commitTransaction: Throwing Exception e = " + e);
                throw new UAPersistenceException("cannot commit transaction, see stack trace", e);
            }
        } else
        {
            throw new IllegalStateException("Cannot commit transaction, no open transaction available!");
        }

    }

    /**
     * <p>rollback
     * <p>close session
     *
     * @throws UAPersistenceException Hibernate Problems during rollback
     */
    public static void rollbackTransaction() throws UAPersistenceException
    {
        System.out.println("INFO: starting rollback ...");
        Transaction tx = (Transaction) transaction.get();
        Session s = (Session) session.get();

        if (tx != null)
        {
            try
            {
                tx.rollback();
                transaction.set(null);

            } catch (HibernateException e)
            {
                System.out.println("exception in rollback : " + e.getMessage());
                throw new UAPersistenceException("cannot rollback transaction, see stack trace", e);
            }
        } else
        {
            System.out.println("transaction is null in rollback");
            throw new IllegalStateException("Cannot rollback transaction, no open transaction available!");
        }
        if (sessionExists())
        {
            // added AJO 20050207: make sure the session is closed after the transaction
//removed AJO:            closeSession();
            try
            {
                s.close();
            } catch (HibernateException e)
            {
                System.out.println("exception while closing a rollbacked session : " + e.getMessage());
                throw new UAPersistenceException("cannot close session after rollback, see stack trace", e);
            }
        }
    }

    /**
     * Checks for open transaction. If one exists, rollback will be applied, session will be flushed and closed
     * <p>if a session exists, it will be flushed and closed
     */
    public static void finishTransaction()
    {
        if (transaction.get() != null)
        {
            try
            {
                System.out.println("INFO: Transaction not null in Finish Transaction");
                rollbackTransaction();
//                throw new UAPersistenceException("Incorrect Transaction handling! While finishing transaction, transaction still open. Rolling Back.");
            } catch (UAPersistenceException e)
            {
                System.out.println("Finish Transaction threw an exception. Don't know what to do here. TODO find solution for handling this situation");
            }
        }
        // added AJO 20050207: make sure the session the session is closed after the transaction
        if (sessionExists())
        {
            try
            {
                System.out.println("INFO: FinishTransaction is going to close session ...");
                closeSession();
            } catch (UAPersistenceException e)
            {
                e.printStackTrace();
                System.out.println("ERROR: Session close throws an exception.");
            }
            System.out.println("INFO: Session closed in FinishTransaction");

        }
    }

    /**
     * Return a list of objects matching the given hibernate query string, with the given
     * parameter value map.
     *
     * @param query      the Hibernate Query used to find the objects.
     * @param parameters a map of named parameters in the query
     * @return a list of found objects
     * @throws UAPersistenceException if the objects cannot be loaded, or the query is faulty.
     */
    public static List find(String query, Map parameters) throws UAPersistenceException
    {
        //        System.out.println("HibernateUtil.find: will execute HQL=" + query + "; params=" + parameters);
        Session sess = HibernateUtil.currentSession();
        List retVal;
        try
        {
            Query q = sess.createQuery(query);
            setParameters(parameters, q);
            retVal = q.list();
            // System.out.println("HibernateUtil.find: query result=" + retVal);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("HibernateUtil.find:: problems creating query, see stack trace", e);
        }
        return retVal;
    }

    public static List pagedFind(String query, Map parameters, int firstResult, int maxResults) throws UAPersistenceException
    {
        //        System.out.println("HibernateUtil.find: will execute HQL=" + query + "; params=" + parameters);
        Session sess = HibernateUtil.currentSession();
        List retVal;
        try
        {
            Query q = sess.createQuery(query);
            q.setFirstResult(firstResult)
                    .setMaxResults(maxResults);
            setParameters(parameters, q);
            retVal = q.list();
            // System.out.println("HibernateUtil.find: query result=" + retVal);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("HibernateUtil.find:: problems creating query, see stack trace", e);
        }
        return retVal;
    }


    /**
     * query (with given params map) returns exactly one result
     *
     * @param query      Qury
     * @param parameters Query parameters
     * @return result Object
     * @throws UAPersistenceException Hibernate exception
     */
    public static Object getUnique(String query, Map parameters) throws UAPersistenceException
    {
        Session sess = HibernateUtil.currentSession();
        Object retVal;
        try
        {
            Query q = sess.createQuery(query);
            setParameters(parameters, q);
            retVal = q.uniqueResult();

        } catch (HibernateException e)
        {
            throw new UAPersistenceException("problems creating query, see stack trace", e);
        }
        return retVal;
    }

    public static Object getUnique(String query) throws UAPersistenceException
    {
        return getUnique(query, new HashMap());
    }

    /**
     * constructs the parameters for a query
     *
     * @param parameters the query parameter to populate the query with
     * @param query      the Hibernate Query
     * @throws HibernateException Hibernate Error
     */
    private static void setParameters(Map parameters, Query query) throws HibernateException
    {
        for (Iterator iterator = parameters.keySet().iterator(); iterator.hasNext();)
        {
            String key = (String) iterator.next();
            Object val = parameters.get(key);

            if (val instanceof Object[])
            {
                query.setParameterList(key, (Object[]) val);
            } else if (val instanceof Collection)
            {
                query.setParameterList(key, (Collection) val);
            } else
            {
                query.setParameter(key, val);
            }
        }
    }


}
