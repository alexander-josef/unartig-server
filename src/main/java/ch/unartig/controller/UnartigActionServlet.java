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
 * Revision 1.2  2007/03/19 20:49:42  alex
 * adding method to stop timer
 *
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.16  2006/10/17 08:07:07  alex
 * creating the order hashes
 *
 * Revision 1.15  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 * Revision 1.14  2006/04/30 16:21:27  alex
 * removing system.outs
 *
 * Revision 1.13  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.12  2006/04/19 21:31:53  alex
 * session will be restored with album-bean (i.e. for bookmarked urls or so...)
 *
 * Revision 1.11  2006/03/21 17:17:03  alex
 * sportsalbum changes, empty etappe now works
 *
 * Revision 1.10  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.9  2006/01/27 09:30:36  alex
 * new pager implemenatation
 *
 * Revision 1.8  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.7  2005/11/25 10:56:58  alex
 *
 * Revision 1.6  2005/11/22 21:33:16  alex
 * ordering process
 *
 * Revision 1.5  2005/11/22 19:45:46  alex
 * admin actions, configurations
 *
 * Revision 1.4  2005/11/06 21:43:22  alex
 * overview, admin menu, index-photo upload
 *
 * Revision 1.3  2005/10/24 13:50:07  alex
 * upload of album
 * import in db 
 * processing of images
 *
 * Revision 1.2  2005/10/03 10:48:05  alex
 * *** empty log message ***
 *
 * Revision 1.1  2005/09/26 18:37:45  alex
 * *** empty log message ***
 *
 ****************************************************************/
package ch.unartig.controller;

import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.businesslogic.PhotoOrderService;
import ch.unartig.studioserver.frontend.TreeGenerator;
import ch.unartig.util.CryptoUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class UnartigActionServlet extends ActionServlet
{
    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * init constants in registry from properties file
     * create the tree for the navigation
     * don't forget to call the super!
     * @throws ServletException
     */
    public void init() throws ServletException
    {
        super.init();
        logger.debug("@@ init unartig action servlet  WITH   STRUTS");
        logger.debug("Calling init on Registry");
        Registry.init(getServletConfig());

        try
        {
            logger.info("Init security");
            CryptoUtil.setPrng(SecureRandom.getInstance("SHA1PRNG"));
            new TreeGenerator().generateTreeItems();
            logger.info("new navigation tree for tigra tree menu generated!");
        } catch (UnartigException e)
        {
            logger.error("Exception while generating menu tree : ",e);
            throw new ServletException("cannot write tree menu",e);
        } catch (NoSuchAlgorithmException e)
        {
            logger.error("Exception while creating SecureRandom instance",e);
             throw new ServletException("initialization failed",e);
         }
        PhotoOrderService orderService = PhotoOrderService.getInstance();
        orderService.startService();
        System.out.println("UnartigActionServlet.init : STARTED ORDER SERVICE !");

        //Initialize SecureRandom
        //This is a lengthy operation, to be done only upon
        //initialization of the application
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException
    {
        logger.debug("@@ processing doGet()");
        logger.debug("@@ processing requestURL : "  + httpServletRequest.getRequestURL());
        super.doGet(httpServletRequest, httpServletResponse);
    }

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException
    {
        logger.debug("@@ processing doPost()");
        // todo remove debug
//        new Throwable().printStackTrace();
        super.doPost(httpServletRequest, httpServletResponse);
    }


    public void destroy() {
        logger.info("destroying unartig action servlet!");
        logger.info("Going to stop order service ......");
        PhotoOrderService.getInstance().stopService();
        logger.info("..... Order Service stopped!");
        logger.info("calling destroy on struts action servlet ....");

        super.destroy();
    }
}
