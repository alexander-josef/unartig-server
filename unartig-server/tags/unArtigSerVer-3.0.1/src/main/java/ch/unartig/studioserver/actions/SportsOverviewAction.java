/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since 14.03.2006$
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
 * Revision 1.2  2006/04/30 16:21:27  alex
 * removing system.outs
 *
 * Revision 1.1  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 ****************************************************************/
package ch.unartig.studioserver.actions;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.model.GenericLevel;
import ch.unartig.studioserver.model.SportsEvent;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.DAOs.GenericLevelDAO;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * this action will only be called for sports event overviews
 * @author Alexander Josef, 2006
 */
public class SportsOverviewAction extends Action
{
    Logger _logger = Logger.getLogger(getClass().getName());

     /**
      * reads the level of the sports - overview to be presented.<br/>
      *
      * @param actionMapping
      * @param actionForm
      * @param request
      * @param httpServletResponse
      * @return overview forward
      */
     public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse httpServletResponse)
     {
         GenericLevelDAO glDao = new GenericLevelDAO();
         String overviewPath = actionMapping.getParameter();
         _logger.debug("actionMapping.getParameter() = " + overviewPath);
         String overviewIdPart = overviewPath.split("/")[0];
         Long levelOverviewId = null;
         String forwardView = "overview";
         SportsEvent sportsEvent;
         try
         {
             levelOverviewId = new Long(overviewIdPart);
             sportsEvent = (SportsEvent) glDao.load(levelOverviewId, SportsEvent.class);
             _logger.debug("going to process overview for sportsEvent [" + sportsEvent.getGenericLevelId().toString() + "]");
             _logger.debug("");
             processOverviewBean(request, sportsEvent);
             request.setAttribute("indexNavEntries", sportsEvent.getIndexNavEntries());

             /*for the select list we only want the sports albums to show up*/
             request.setAttribute("sportsAlbumList", sportsEvent.getSportsAlbums());
//             request.setAttribute("sportsAlbumList", sportsEvent.getSportsAlbums());
             for (Iterator iterator = sportsEvent.getSportsAlbums().iterator(); iterator.hasNext();)
             {
                 StudioAlbum studioAlbum = (StudioAlbum) iterator.next();
             }

             request.setAttribute("level", sportsEvent);
         } catch (NumberFormatException e)
         {
             _logger.error("the album Id part in the URL could not be parsed into a Long : " + overviewIdPart, e);
             // todo: add error message and forward to error page
         } catch (UAPersistenceException e)
         {
             _logger.error("the album id could not be loaded from the db : " + levelOverviewId.toString(), e);
             // todo: add error message and forward to error page
         } catch (UnartigException e)
         {
             _logger.error("the album could not be populated", e);
             // todo: add error message and forward to error page
         }
         return actionMapping.findForward(forwardView);
     }


     /**
      * list at most [Registry._MAX_ENTRIES_FOR_OVERVIEW] entries of the level and put the list to the request
      * todo: max entries for overview should be configurable for Album (limit for events, no limit for albums. we always want to show all albums, but only a selection of other levels)
      *
      * @param request
      * @param level
      */
     private void processOverviewBean(HttpServletRequest request, GenericLevel level)
     {
         List allChildren = level.listChildren();
         List levelEntries = allChildren.subList(0, allChildren.size() > Registry._MAX_ENTRIES_FOR_OVERVIEW ? Registry._MAX_ENTRIES_FOR_OVERVIEW : allChildren.size());
         request.setAttribute("overviewEntriesList", levelEntries);
     }
 }
