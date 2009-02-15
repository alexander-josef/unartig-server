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
 * Revision 1.1  2005/09/26 18:37:45  alex
 * *** empty log message ***
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.util;

import ch.unartig.exceptions.UAPersistenceException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HibernateFilter implements Filter
{

    //      logging ready at this stage? probably not!
    //    private static final Logger logger = ((LoggingServiceIF) ServiceManager.getService(LoggingServiceIF._NAME)).getLogger(ResourceManager._error_log_category);

    /**
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        HibernateUtil.init();
        System.out.println("INITING HIBERNATEFILETER");
        // Initialize hibernate lazily
    }

    /**
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
//        System.out.println("HibernateFilter.doFilter :  pre-chain++++");
        try
        {
            HibernateUtil.enterService((HttpServletRequest) request);
            //            System.out.println("HibernateFilter.doFilter: processing request");
            chain.doFilter(request, response);
        } finally
        {
            try
            {
                HibernateUtil.leaveService((HttpServletRequest) request);
            } catch (UAPersistenceException e)
            {
                throw new ServletException("Problems while closing the Hibernate Session, see stack trace", e);
            }
        }
    }

    /**
     */
    public void destroy()
    {
        HibernateUtil.destroy();
    }

}
