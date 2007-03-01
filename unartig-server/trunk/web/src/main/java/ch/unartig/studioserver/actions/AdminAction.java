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
 * Revision 1.45  2007/01/29 11:07:33  alex
 * last commit
 *
 * Revision 1.44  2006/12/15 16:26:03  alex
 * reporting works; averages and totals are still incorrect
 *
 * Revision 1.43  2006/12/06 18:42:08  alex
 * no js necessary for basic shopping cart functionality
 *
 * Revision 1.42  2006/12/05 22:51:56  alex
 * album kann jetzt freigeschaltet werden oder geschlossen sein
 *
 * Revision 1.41  2006/11/21 21:56:28  alex
 * small fixes
 *
 * Revision 1.40  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.39  2006/11/10 15:55:30  alex
 * dynamic album ads
 *
 * Revision 1.38  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.37  2006/07/12 20:33:02  alex
 * reporting
 *
 * Revision 1.36  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 * Revision 1.35  2006/04/30 16:21:27  alex
 * removing system.outs
 *
 * Revision 1.34  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.33  2006/04/10 21:07:02  alex
 * finish time mapping works
 *
 * Revision 1.32  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.31  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.30  2006/02/28 16:44:56  alex
 * small fixes
 *
 * Revision 1.29  2006/02/28 14:57:46  alex
 * added more resources (email for order confirmation), small fixes
 *
 * Revision 1.28  2006/02/24 11:52:05  alex
 * admin tool: small fixes; added resources to property files
 *
 * Revision 1.27  2006/02/23 14:37:42  alex
 * admin tool: new category works now
 *
 * Revision 1.26  2006/02/17 10:09:41  alex
 * admin interface: deletion of levels works now
 *
 * Revision 1.25  2006/02/16 17:13:46  alex
 * admin interface: deletion of levels works now
 *
 * Revision 1.24  2006/02/15 15:57:03  alex
 * bug [968] fixed. admin interface does that now
 *
 * Revision 1.1  2006/02/15 15:10:31  alex
 * fehler nach move refactoring ... deleted from cvs now checked in again
 *
 * Revision 1.23  2006/02/13 16:15:28  alex
 * but [968]
 *
 * Revision 1.22  2006/02/10 14:21:49  alex
 * admin tool: edit level partly ...
 *
 * Revision 1.21  2006/02/08 18:04:49  alex
 * first steps for album type configuration
 *
 * Revision 1.20  2006/01/11 20:40:53  alex
 * level update form works
 *
 * Revision 1.19  2006/01/11 16:52:55  alex
 * admin formular
 *
 * Revision 1.18  2005/11/28 17:52:16  alex
 * bug fixes
 *
 * Revision 1.17  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.16  2005/11/25 10:56:58  alex
 * liste, admin tool, sonstiges
 *
 * Revision 1.15  2005/11/19 20:32:32  alex
 * price segments inserted
 *
 * Revision 1.14  2005/11/12 23:15:27  alex
 * using indexed properties ... first step
 *
 * Revision 1.13  2005/11/08 10:12:42  alex
 * tree items i18n, backend
 *
 * Revision 1.12  2005/11/07 21:57:43  alex
 * admin interface refactored
 *
 * Revision 1.11  2005/11/07 17:38:26  alex
 * admin interface refactored
 *
 * Revision 1.10  2005/11/06 21:43:22  alex
 * overview, admin menu, index-photo upload
 *
 * Revision 1.9  2005/11/05 21:41:57  alex
 * overview und links in tree menu
 *
 * Revision 1.8  2005/10/24 13:50:07  alex
 * upload of album
 * import in db 
 * processing of images
 *
 * Revision 1.7  2005/10/08 10:52:36  alex
 * jstl 1.1 integrated, new web.xml
 *
 * Revision 1.6  2005/10/06 14:30:09  alex
 * generating the nav tree recursivly works
 *
 * Revision 1.5  2005/10/06 11:06:33  alex
 * generating the nav tree
 *
 * Revision 1.4  2005/10/06 08:54:04  alex
 * cleaning up the model
 *
 * Revision 1.3  2005/10/04 11:36:48  alex
 * level imports
 *
 * Revision 1.2  2005/10/03 14:50:25  alex
 * first daos
 *
 * Revision 1.1  2005/10/03 10:48:05  alex
 * *** empty log message ***
 *
 ****************************************************************/
package ch.unartig.studioserver.actions;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.exceptions.UnartigInvalidArgument;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.beans.AdminForm;
import ch.unartig.studioserver.beans.ReportBean;
import ch.unartig.studioserver.businesslogic.*;
import ch.unartig.studioserver.frontend.TreeGenerator;
import ch.unartig.studioserver.model.*;
import ch.unartig.studioserver.persistence.DAOs.*;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import ch.unartig.util.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;


/**
 *
 */
public class AdminAction extends MappingDispatchAction
{
    Logger _logger = Logger.getLogger(getClass().getName());

    public ActionForward testTest(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        List reportProductSales = new ReportDAO().listProductSales();

        for (int i = 0; i < reportProductSales.size(); i++)
        {
            ReportProductSales productSales = (ReportProductSales) reportProductSales.get(i);
            System.out.println("productSales = " + productSales);
        }
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UnartigException
     */
    public ActionForward reportProductSales(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {

        Date startDate;
        Date endDate;
        String startDateString = ((DynaActionForm) form).getString("startDate");
        String endDateString = ((DynaActionForm) form).getString("endDate");
        try
        {
            if (startDateString != null && !"".equals(startDateString))
            {
                startDate = ReportBean._SIMPLE_FORMAT.parse(startDateString);
            } else
            {
                startDate=null;
            }
            if (endDateString != null && !"".equals(endDateString))
            {
                endDate = ReportBean._SIMPLE_FORMAT.parse(endDateString);
            } else
            {
                // set end date to todays date
                endDate = new Date();
            }
        } catch (ParseException e)
        {
            _logger.error("wrong date format", e);
            // todo : set message to be shown with report view
            throw new UnartigException("Date Parse Exception",e);
//            ((DynaActionForm) form).set("startDate", "");
//            ((DynaActionForm) form).set("endDate", "");
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        ReportBean reportBean = ReportBean.prepareConsSalesPerAlbum(startDate, endDate);
        request.setAttribute("reportBean", reportBean);
        _logger.debug("set report bean to request");

        //debug
        for (int i = 0; i < reportBean.getConsSalesPerAlbum().size(); i++)
        {
            ReportConsSalesPerAlbumDate reportConsSalesPerAlbumDate = (ReportConsSalesPerAlbumDate) reportBean.getConsSalesPerAlbum().get(i);
            System.out.println("************************** by date **************************************");
            System.out.println("reportConsSalesPerAlbumDate = " + reportConsSalesPerAlbumDate);
        }
        return mapping.findForward("success");
    }

    /**
     * prepare all levels to be displayed in the level overview
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UnartigException
     */
    public ActionForward getOverview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {

        List categories;
        GenericLevelDAO levelDao = new GenericLevelDAO();
        categories = levelDao.listGenericLevel(Category.class);
//        System.out.println("categories = " + categories.size());
        request.setAttribute("categories", categories);
        return mapping.findForward("levelOverview");
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UAPersistenceException
     */
    public ActionForward toggleAlbumPublishStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UAPersistenceException
    {

        try
        {
            HibernateUtil.beginTransaction();
            AdminForm adminForm = (AdminForm) form;
            GenericLevelDAO levelDao = new GenericLevelDAO();
            Long levelId = adminForm.getGenericLevelId();
            GenericLevel level = levelDao.load(levelId);

            // need to check for null: newly created albums have a null value for getPublish
            if (level.getPublish()!=null && level.getPublish().booleanValue())
            {
                level.setPublish(Boolean.FALSE);
            } else
            {
                level.setPublish(Boolean.TRUE);
            }
            HibernateUtil.commitTransaction();
        } catch (UAPersistenceException e)
        {
            try
            {
                HibernateUtil.rollbackTransaction();
            } catch (UAPersistenceException e1)
            {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // todo : use mapping to forward to overview action
//        List categories;
//        GenericLevelDAO levelDao = new GenericLevelDAO();
//        categories = levelDao.listGenericLevel(Category.class);
//        System.out.println("categories = " + categories.size());
//        request.setAttribute("categories", categories);
        return mapping.findForward("levelOverview");
    }

    /**
     * load level and prepare form to edit level
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UnartigException
     */
    public ActionForward editLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        AdminForm adminForm = (AdminForm) form;
        GenericLevelDAO levelDao = new GenericLevelDAO();
        Long levelId = adminForm.getGenericLevelId();
        GenericLevel level = levelDao.load(levelId);

        // get the 'master data' (stammdaten):
        prepareAdminAttributes(level.getLevelType(), request);

        adminForm.setEventDateDisplay(level.getEventDateDisplay());
        adminForm.setLongTitle(level.getLongTitle());
        adminForm.setNavTitle(level.getNavTitle());
        adminForm.setParentLevelId(level.getParentLevel().getGenericLevelId());
        // todo special album treatment:
        // todo (casting level down to album causes problems ... see the Hibernate Visitor pattern recommendation for handling this more elegantly)
        if (Registry._NAME_ALBUM_LEVEL_TYPE.equals(level.getLevelType()))
        {
            adminForm.setPriceSegmentId(level.getPriceSegment().getPriceSegmentId());
            if (level.getAlbumType() != null && level.getAlbumType() instanceof NoTimeAlbum)
            {
                adminForm.setNoTime(Boolean.TRUE);
            }
        }
        adminForm.setPrivateEvent(level.getIsPrivate());
        adminForm.setDescription(level.getDescription());
        adminForm.setQuickAccess(level.getQuickAccess());
        adminForm.setPrivateAccessCode(level.getPrivateAccessCode());
        adminForm.setSkyBannerRightAd((String) level.getAlbumAdvertisment(Registry._AD_BANNER_RIGHT_POSITION));
        adminForm.setLevelType(level.getLevelType());
        if (level.getAlbumType() == AlbumType._noTimeAlbum)
        {
            adminForm.setNoTime(Boolean.TRUE);
        }
        request.setAttribute("level", level);
        return mapping.findForward("success");
    }

/*-******************
* Sports Album Mappings
**********************/

    public ActionForward startnumberMapping(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
// todo: extract method with similar code as finishtime mapping
        GenericLevelDAO glDao = new GenericLevelDAO();
        try
        {
            DynaActionForm startnumberMappingForm = (DynaActionForm) form;

            FormFile mappingFile = (FormFile) startnumberMappingForm.get("mappingFile");
            Long albumId = (Long) startnumberMappingForm.get("sportsAlbumId");
            _logger.debug("mappingFile [" + mappingFile.getFileName() + "] called for albumId [" + albumId + "]");
            StudioAlbum album = (StudioAlbum) glDao.load(albumId, StudioAlbum.class);
            _logger.debug("mapping for albumid " + album.getGenericLevelId());
            SportsAlbumMapper mapper = new SportsAlbumMapper(mappingFile.getInputStream(), album);
            mapper.map();
        } catch (Exception e)
        {
            _logger.error("cannot map sports album : ", e);
            throw new UnartigException(e);
        }
        return mapping.findForward("success");
    }

    public ActionForward finishtimeMapping(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
// todo: extract method with similar code as startnumber mapping
        GenericLevelDAO glDao = new GenericLevelDAO();
        try
        {
            DynaActionForm startnumberMappingForm = (DynaActionForm) form;

            FormFile mappingFile = (FormFile) startnumberMappingForm.get("mappingFile");
            Long albumId = (Long) startnumberMappingForm.get("sportsAlbumId");
            _logger.debug("mappingFile [" + mappingFile.getFileName() + "] called for albumId [" + albumId + "]");
            StudioAlbum album = (StudioAlbum) glDao.load(albumId, StudioAlbum.class);
            _logger.debug("mapping for albumid " + album.getGenericLevelId());
            int photoPointDifference = Integer.parseInt(startnumberMappingForm.getString("photopointFinishDifference"));
            String photoPointTolerance = startnumberMappingForm.getString("photopointTolerance");
            boolean photopointBeforeFinishtime = "beforeFinishTiming".equals(startnumberMappingForm.getString("photopointLocation"));
            SportsAlbumMapper mapper = SportsAlbumMapper.createFinishTimeMapper(mappingFile.getInputStream(), album, photoPointDifference, photoPointTolerance, photopointBeforeFinishtime);
            mapper.mapFinishOrStartTime();
        } catch (Exception e)
        {
            _logger.error("cannot map sports album : ", e);
            throw new UnartigException(e);
        }
        return mapping.findForward("success");
    }

    /**
     * delete all startnumber-mappings for a given album
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return actionforward
     */
    public ActionForward deleteFinishtimeMappings(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UAPersistenceException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        PhotoDAO photoDao = new PhotoDAO();
        DynaActionForm startnumberMappingForm = (DynaActionForm) form;

        Long albumId = (Long) startnumberMappingForm.get("sportsAlbumId");
        _logger.debug("albumId [" + albumId + "]");
        StudioAlbum album = (StudioAlbum) glDao.load(albumId, StudioAlbum.class);

        SportsAlbumMapper mapper = SportsAlbumMapper.createMapper(album);
        mapper.delete();
        return mapping.findForward("success");
    }


    /**
     * deletes a level and uses cascading to delete all levels that are below that level hierarchy<br>
     * needs to generate a new tree structure
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UAPersistenceException
     */
    public ActionForward deleteLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        AdminForm adminForm = (AdminForm) form;
        _logger.warn("Deleting Level : [" + adminForm.getGenericLevelId().toString() + "]");

        GenericLevel level = glDao.load(adminForm.getGenericLevelId());


        HibernateUtil.beginTransaction();
        try
        {
//            level.accept(deleteReferencesInOrderItems);
            level.deleteLevel();
            // todo: we don't need this method ... do we? just create a visitor for StudioAlbum ....
            // then all the deleteLevel mehtods can be deleted.

            glDao.delete(adminForm.getGenericLevelId());
            HibernateUtil.commitTransaction();
        } catch (UAPersistenceException e)
        {
            HibernateUtil.rollbackTransaction();
            throw new UAPersistenceException("rolling back, cannot delete Level");
        } finally
        {
            HibernateUtil.finishTransaction();
        }
        // generate a new tree
        new TreeGenerator().generateTreeItems();
        return mapping.findForward("success");
    }

    /**
     * load level, update with information from view and update on database
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return success
     * @throws UAPersistenceException UnartigInvalidArgument
     */
    public ActionForward updateLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        final GenericLevelDAO glDao = new GenericLevelDAO();
        AlbumAdvertismentDAO adDao = new AlbumAdvertismentDAO();
        final AdminForm adminForm = (AdminForm) form;
        GenericLevel level = glDao.load(adminForm.getGenericLevelId());

        // set ad
        AlbumAdvertisment albumAd = adDao.getAdvertismentFor(level, Registry._AD_BANNER_RIGHT_POSITION);
        if (albumAd == null)
        {
            albumAd = new AlbumAdvertisment();
            albumAd.setPosition(Registry._AD_BANNER_RIGHT_POSITION);
            albumAd.setAlbum(level);
        }
        albumAd.setAdLinkText(adminForm.getSkyBannerRightAd());
        adDao.saveOrUpdate(albumAd);
        System.out.println("AdminAction.updateLevel   ... nach addalbumadds");
        _logger.debug("trying to save level ......");


        if (adminForm.getNoTime() != null && adminForm.getNoTime().booleanValue())
        {
            level.setAlbumType(new NoTimeAlbum());
        } else
        {
            level.setAlbumType(new TimedAlbum());
        }
        level.setDescription(adminForm.getDescription());
        level.setEventDateDisplay(adminForm.getEventDateDisplay());

        //////////////////////
        //////////////// new visitor ////////////////////////////////////////
        ///////////////////////
        GenericLevelVisitor setParentLevelVisitor = getParentLevelVisitor(adminForm);
        // we use the level's accept method to pass an instance of the setParentLevelVisitor
        // setParentLevelVisitor is implemented as an anonymous inner class (advantages: we can use the method-scoped variables)
        level.accept(setParentLevelVisitor);
        //////////////////////
        //////////////// new visitor ////////////////////////////////////////
        ///////////////////////

        level.setIsPrivate(adminForm.getPrivateEvent());
        level.setLongTitle(adminForm.getLongTitle());
        level.setNavTitle(adminForm.getNavTitle());
        level.setQuickAccess(adminForm.getQuickAccess());

        GenericLevelVisitor setPriceSegmentVisitor = getPriceSegmentVisitor(adminForm);

        level.accept(setPriceSegmentVisitor);
        level.setPrivateAccessCode(adminForm.getPrivateAccessCode());
        HibernateUtil.beginTransaction();

        try
        {
            glDao.saveOrUpdate(level);
            HibernateUtil.commitTransaction();
        } catch (UAPersistenceException e)
        {
            _logger.error("cannot update generic level information", e);
            HibernateUtil.rollbackTransaction();
        } finally
        {
            HibernateUtil.finishTransaction();
        }

        finishLevelUpdate(level, adminForm, request);
        return new ActionForward(mapping.findForward("success").getPath() + "?genericLevelId=" + adminForm.getGenericLevelId());
    }

    /**
     * provide a ParentLeveltVisitor
     *
     * @param adminForm
     * @return ParentLevelVisitor Implementation
     */
    private GenericLevelVisitor getParentLevelVisitor(final AdminForm adminForm)
    {
        final GenericLevelDAO glDao = new GenericLevelDAO();
        GenericLevelVisitor setParentLevelVisitor = new GenericLevelVisitor()
        {
            // todo: setParentLevel wird nicht mehr gebraucht, oder?
            // todo: wieso nicht gleich ein ParentLevel vom typ generic level fuer jedes level?
            public void visit(Category category) throws UnartigException
            {
                // category has itself as parent, since it's root
                category.setParentLevel(category);
            }

            public void visit(EventGroup eventGroup) throws UAPersistenceException
            {
                eventGroup.setCategory((Category) glDao.load(adminForm.getParentLevelId(), Category.class));
            }

            public void visit(Event event) throws UAPersistenceException
            {
                event.setEventGroup((EventGroup) glDao.load(adminForm.getParentLevelId(), EventGroup.class));
            }

            public void visit(StudioAlbum album) throws UAPersistenceException
            {
                album.setEvent((Event) glDao.load(adminForm.getParentLevelId(), Event.class));
            }
        };
        return setParentLevelVisitor;
    }

    /**
     * return a concrete visitor as anonymous inner class
     *
     * @param adminForm
     * @return PriceSegmentVisitor
     */
    private GenericLevelVisitor getPriceSegmentVisitor(final AdminForm adminForm)
    {
        GenericLevelVisitor setPriceSegmentVisitor;
        setPriceSegmentVisitor = new GenericLevelVisitorAdaptor()
        {
            public void visit(StudioAlbum album) throws UnartigException
            {
                PriceSegmentDAO psDao = new PriceSegmentDAO();
                try
                {
                    album.setPriceSegment(psDao.load(adminForm.getPriceSegmentId()));
                } catch (UAPersistenceException e)
                {
                    _logger.error("Error loading price segment, see stack trace", e);
                    throw new UnartigInvalidArgument("Error loading Price Segment ");
                }
            }
        };
        return setPriceSegmentVisitor;
    }

    /**
     * prepare all data for creating a new level and put attributes to request
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return the admin page
     * @throws UAPersistenceException
     */
    public ActionForward levels(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        AdminForm adminForm = (AdminForm) form;
        String levelType = request.getParameter("levelType");
        adminForm.setLevelType(levelType);


        List levelTypeList = new ArrayList();

        levelTypeList.add(Registry._NAME_EVENTGROUP_LEVEL_TYPE);
        levelTypeList.add(Registry._NAME_EVENT_LEVEL_TYPE);
        levelTypeList.add(Registry._NAME_SPORTSEVENT_LEVEL_TYPE);
        levelTypeList.add(Registry._NAME_ALBUM_LEVEL_TYPE);
        levelTypeList.add(Registry._NAME_SPORTSALBUM_LEVEL_TYPE);

        request.setAttribute("levelTypeList", levelTypeList);
        prepareAdminAttributes(levelType, request);
        return mapping.findForward("adminResult");
    }

    /**
     * helper method to set all needed attributes for creating or editing a level
     *
     * @param levelType
     * @param request
     * @throws UnartigException
     */
    private void prepareAdminAttributes(String levelType, HttpServletRequest request) throws UnartigException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        PriceSegmentDAO psDao = new PriceSegmentDAO();
        List parents;
        if (levelType != null && !"".equals(levelType) && !"Category".equals(levelType))
        {
            try
            {
                GenericLevel genericLevel = ((GenericLevel) Class.forName(Registry.getModelPackageName() + levelType).newInstance());
                _logger.debug("genericLevel = " + genericLevel);
                _logger.debug("genericLevel.getParentClazz() = " + genericLevel.getParentClazz().getName());
                parents = glDao.listGenericLevel(genericLevel.getParentClazz());
            } catch (ClassNotFoundException e)
            {
                throw new UnartigException("class [" + Registry.getModelPackageName() + levelType + "] not found", e);
            } catch (IllegalAccessException e)
            {
                throw new UnartigException("class [" + Registry.getModelPackageName() + levelType + "] not found", e);
            } catch (InstantiationException e)
            {
                throw new UnartigException("class [" + Registry.getModelPackageName() + levelType + "] not found", e);
            }
        } else
        {
            parents = Collections.EMPTY_LIST;
        }

        request.setAttribute("parentList", parents);

        List categoryList = glDao.listGenericLevel(Category.class);
        List eventGroupList = glDao.listGenericLevel(EventGroup.class);
        List eventList = glDao.listGenericLevel(Event.class);
        List priceSegmentList = psDao.listPriceSegments();
        List productTypeList = psDao.listPriceSegments();

//        request.setAttribute("levelList", categoryList);

        request.setAttribute("eventGroupList", eventGroupList);
        request.setAttribute("eventList", eventList);
        request.setAttribute("priceSegmentList", priceSegmentList);
    }

    /**
     * triggers the generations of a new tree items file
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UAPersistenceException
     */
    public ActionForward generateNavTree(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        new TreeGenerator().generateTreeItems();
        request.setAttribute("navTree", "check the tree_itmes.js files for the result");
        return mapping.findForward("navTree");
    }

    /**
     * trigger an oips order process without using the timer
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UAPersistenceException
     */
    public ActionForward triggerOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UAPersistenceException
    {
        TimedOrderProcess orderProcess = new TimedOrderProcess();
        orderProcess.run();
        return mapping.findForward("showAdminPage");
    }

    /**
     * simply creates a new category using the values from the form and saves it<br>
     * a new navigation tree needs to be generated
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UAPersistenceException
     */
    public ActionForward newCategory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        AdminForm adminForm = (AdminForm) form;
        final String navTitle = adminForm.getNavTitle();
        final String longTitle = adminForm.getLongTitle();
        final String description = adminForm.getDescription();
        GenericLevel category = new Category(navTitle, longTitle, description);

        HibernateUtil.beginTransaction();
        try
        {
            glDao.saveOrUpdate(category);
            HibernateUtil.commitTransaction();
        } catch (UAPersistenceException e)
        {
            _logger.error("Error saving category", e);
            HibernateUtil.rollbackTransaction();
            throw new UAPersistenceException(e);
        } finally
        {
            HibernateUtil.finishTransaction();
        }

        ActionMessages msgs = new ActionMessages();
        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("admin.added.Category"));
        finishLevelUpdate(category, adminForm, request);

        return mapping.findForward("success");

    }


    public ActionForward createSportsEvent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UAPersistenceException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();

        List events = glDao.listGenericLevel(Event.class);
        for (int i = 0; i < events.size(); i++)
        {
            Event event = (Event) events.get(i);
        }
        SportsEvent sportsEvent = new SportsEvent();
        sportsEvent.setNavTitle("sportsEvent");
        sportsEvent.setLongTitle("Long sportsEvent");
        sportsEvent.setDescription("Desc sportsEvent");
        sportsEvent.setEventDate(new Date());

        HibernateUtil.beginTransaction();

        try
        {
            glDao.saveOrUpdate(sportsEvent);
            HibernateUtil.commitTransaction();
        } catch (UAPersistenceException e)
        {
            HibernateUtil.rollbackTransaction();
            throw new UAPersistenceException("problem saving sportsevent ....rolling back");
        } finally
        {
            HibernateUtil.finishTransaction();
        }
        return mapping.findForward("success");
    }


    /**
     * create a new level (genericlevel) and show the admin screen afterwards
     * todo: refactor this chaos. use visitors
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return to the admin screen
     * @throws UAPersistenceException
     * @throws java.text.ParseException
     */
    public ActionForward newLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigException, ParseException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        PriceSegmentDAO psDao = new PriceSegmentDAO();
        AdminForm adminForm = (AdminForm) form;
        GenericLevel parentLevel;
        final String navTitle = adminForm.getNavTitle();
        final String longTitle = adminForm.getLongTitle();
        final String description = adminForm.getDescription();
        final Long parentLevelId = adminForm.getGenericLevelId();
        final Boolean privateEvent = adminForm.getPrivateEvent();
        // todo: choose from a list of album-types. possible types could be: timed,paged, startNummer
        final Boolean noTimeAlbum = adminForm.getNoTime();
        final String levelType = adminForm.getLevelType();


        final String eventDateDisplay = adminForm.getEventDateDisplay();

        GenericLevel newLevel;
        try
        {

            /*introspection ....*/
            Class newLevelClazz = Class.forName(Registry.getModelPackageName() + levelType);
            newLevel = ((GenericLevel) newLevelClazz.getConstructor(new Class[]{String.class, String.class, String.class}).newInstance(new Object[]{navTitle, longTitle, description}));


            HibernateUtil.beginTransaction();
            parentLevel = glDao.load(parentLevelId, newLevel.getParentClazz());
            newLevel.setIsPrivate(privateEvent);
            glDao.saveOrUpdate(newLevel);
            // todo: print class names
            newLevel.setParentLevel(parentLevel);
            // visit price segment
            newLevel.accept(getPriceSegmentVisitor(adminForm));

            newLevel.setEventDateDisplay(eventDateDisplay);
            if (noTimeAlbum != null && noTimeAlbum.booleanValue())
            {
                _logger.debug("found albumTpe notime ");
                newLevel.setAlbumType(new NoTimeAlbum());
            }
            addAlbumAds(newLevel, adminForm);
            HibernateUtil.commitTransaction();


        } catch (UAPersistenceException e)
        {
            HibernateUtil.rollbackTransaction();
            throw new UAPersistenceException("cannot write generic parentLevel", e);

        } catch (NoSuchMethodException e)
        {
            throw new UnartigException("cannot write new level", e);
        } catch (IllegalAccessException e)
        {
            throw new UnartigException("cannot write new level", e);
        } catch (InvocationTargetException e)
        {
            throw new UnartigException("cannot write new level", e);
        } catch (ClassNotFoundException e)
        {
            throw new UnartigException("cannot write new level", e);
        } catch (InstantiationException e)
        {
            throw new UnartigException("cannot write new level", e);
        } finally
        {
            HibernateUtil.finishTransaction();
        }

        finishLevelUpdate(newLevel, adminForm, request);

        return mapping.findForward("showNewAdminPage");
    }

    private void addAlbumAds(GenericLevel level, AdminForm adminForm) throws UAPersistenceException
    {
        AlbumAdvertismentDAO adDao = new AlbumAdvertismentDAO();
        Set ads = new HashSet();
        AlbumAdvertisment albumAdvertisment = new AlbumAdvertisment(Registry._AD_BANNER_RIGHT_POSITION, level, adminForm.getSkyBannerRightAd());
//        adDao.saveOrUpdate(albumAdvertisment);
        level.setAlbumAdvertisments(ads);
        ads.add(albumAdvertisment);
    }

    /**
     * create or update the index photo, update the nav-tree and write a success-message to the request
     *
     * @param newLevel
     * @param adminForm
     * @param request
     * @throws UnartigException
     */
    private void finishLevelUpdate(GenericLevel newLevel, AdminForm adminForm, HttpServletRequest request) throws UnartigException
    {
        newLevel.makeDataPath();
        if (!newLevel.isCategoryLevel() && adminForm.getIndexPhoto().getFileSize() != 0)
        { // no index picture for category and no new indexphoto if field is empty
            writeIndexPhotoFile(adminForm, newLevel);
        }
        new TreeGenerator().generateTreeItems();
        ActionMessages msgs = new ActionMessages();
        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("admin.updated.level"));
        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("admin.updated.navtree"));
        saveMessages(request, msgs);
    }

    /**
     * takes the FormFile from the admin form and writes the index photo file to the appropriate location
     *
     * @param adminForm
     * @param newLevel
     * @throws UnartigException
     */
    private void writeIndexPhotoFile(AdminForm adminForm, GenericLevel newLevel) throws UnartigException
    {
        final FormFile indexPhoto = adminForm.getIndexPhoto();
        String nameIndexPhoto = Registry.getDataPath() + newLevel.getGenericLevelId() + "/" + Registry._LEVEL_INDEX_IMAGE_NAME;
        try
        {
            FileUtils.copyFile(indexPhoto.getInputStream(), nameIndexPhoto);
            _logger.debug("wrote file :" + nameIndexPhoto);
        } catch (IOException e)
        {
            _logger.error("cannot write index.jsp file", e);
            throw new UnartigException("cannot write index.jsp file", e);
        }
    }


}
