/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Oct 3, 2005$
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
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.17  2006/11/12 13:32:49  alex
 * dynamic album ads
 *
 * Revision 1.16  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.15  2006/11/10 15:55:30  alex
 * dynamic album ads
 *
 * Revision 1.14  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.13  2006/03/08 17:42:26  alex
 * small fixes
 *
 * Revision 1.12  2006/02/16 17:13:46  alex
 * admin interface: deletion of levels works now
 *
 * Revision 1.11  2006/02/15 15:57:03  alex
 * bug [968] fixed. admin interface does that now
 *
 * Revision 1.10  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.9  2005/11/07 21:57:43  alex
 * admin interface refactored
 *
 * Revision 1.8  2005/11/07 17:38:26  alex
 * admin interface refactored
 *
 * Revision 1.7  2005/11/06 21:43:22  alex
 * overview, admin menu, index-photo upload
 *
 * Revision 1.6  2005/11/05 21:41:58  alex
 * overview und links in tree menu
 *
 * Revision 1.5  2005/10/26 14:34:32  alex
 * first version of album overview
 * new mappings in struts for the /album/** url
 *
 * Revision 1.4  2005/10/08 10:52:36  alex
 * jstl 1.1 integrated, new web.xml
 *
 * Revision 1.3  2005/10/06 08:54:04  alex
 * cleaning up the model
 *
 * Revision 1.2  2005/10/04 11:36:48  alex
 * level imports
 *
 * Revision 1.1  2005/10/03 14:50:25  alex
 * first daos
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.*;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;

import java.util.List;

public class GenericLevelDAO
{
    Logger _logger = Logger.getLogger(getClass().getName());

    /**
     * @param genericLevel
     * @throws UAPersistenceException
     */
    public void saveOrUpdate(GenericLevel genericLevel) throws UAPersistenceException
    {
        try
        {
            HibernateUtil.currentSession().saveOrUpdate(genericLevel);
        } catch (HibernateException e)
        {
            _logger.error("Cannot save or update a Category, see stack trace");
            throw new UAPersistenceException("Cannot save or update a generic Level, see stack trace", e);
        }
    }


    /**
     * @return
     * @throws UAPersistenceException
     * @deprecated use generic
     */
    public List listCategories() throws UAPersistenceException
    {
        try
        {
            return HibernateUtil.currentSession().createCriteria(Category.class).list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("cannot list categories, see stack trace ", e);
        }
    }

    /**
     * @return
     * @throws UAPersistenceException
     * @deprecated use generic list
     */
    public List listEventGroups() throws UAPersistenceException
    {
        try
        {
            return HibernateUtil.currentSession().createCriteria(EventGroup.class).list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("cannot list eventGroups, see stack trace ", e);
        }
    }

    /**
     * @return
     * @throws UAPersistenceException
     * @deprecated
     */
    public List listEvents() throws UAPersistenceException
    {
        try
        {
            return HibernateUtil.currentSession().createCriteria(Event.class).list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("cannot list events, see stack trace ", e);
        }
    }

    /**
     * list all objects of the passed class ordered by id todo good idea to order by id???
     *
     * @param levelClass
     * @return
     * @throws UAPersistenceException
     */
    public List listGenericLevel(Class levelClass) throws UAPersistenceException
    {
        try
        {
            return HibernateUtil.currentSession()
                    .createCriteria(levelClass)
                    .addOrder(org.hibernate.criterion.Order.asc("genericLevelId"))
                    .list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("cannot list a generic level, see stack trace ", e);
        }
    }

    /**
     * generically load a hierachy level. must be casted by calling method to appropriate concrete class
     *
     * @param genericLevelId
     * @param levelClass
     * @return a generic Level ; needs to be casted to appropriate class
     * @throws UAPersistenceException
     */
    public GenericLevel load(Long genericLevelId, Class levelClass) throws UAPersistenceException
    {
        try
        {
            return (GenericLevel) HibernateUtil.currentSession().load(levelClass, genericLevelId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load Generic Level, see stack trace", e);
        }
    }

    /**
     * load an instance of GenericLevel
     *
     * @param genericLevelId
     * @return
     * @throws UAPersistenceException
     */
    public GenericLevel load(Long genericLevelId) throws UAPersistenceException
    {
        try
        {
            return (GenericLevel) HibernateUtil.currentSession().load(GenericLevel.class, genericLevelId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load Generic Level, see stack trace", e);
        }
    }

    /**
     * load by entity name ?
     *
     * @param entityName
     * @param genericLevelId
     * @return
     * @throws UAPersistenceException
     */
    public GenericLevel load(String entityName, Long genericLevelId) throws UAPersistenceException
    {
        try
        {
            return (GenericLevel) HibernateUtil.currentSession().load(entityName, genericLevelId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load Generic Level, see stack trace", e);
        }
    }

    public void countPhotoFor(GenericLevel genericLevel)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void delete(Long genericLevelId) throws UAPersistenceException
    {
        delete(load(genericLevelId));
    }

    public void delete(GenericLevel level) throws UAPersistenceException
    {
        try
        {
            HibernateUtil.currentSession().delete(level);
        } catch (UAPersistenceException e)
        {
            _logger.error("cannot delete generic level", e);
            throw new UAPersistenceException("cannot delete GenericLevel", e);
        }
    }

    /**
     * @param event
     * @return a list of all sports albums for the passed sports event
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     */
    public List getSportsAlbumsFor(Event event) throws UAPersistenceException
    {
        return HibernateUtil.currentSession().createCriteria(SportsAlbum.class)
                .add(Expression.eq("event", event))
                .list();
    }


}
