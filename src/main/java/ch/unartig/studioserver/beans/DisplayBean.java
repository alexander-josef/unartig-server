/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 1, 2005$
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
 * Revision 1.12  2006/05/01 12:43:48  alex
 * fix for album reload for sports and event album
 *
 * Revision 1.11  2006/04/19 21:31:53  alex
 * session will be restored with album-bean (i.e. for bookmarked urls or so...)
 *
 * Revision 1.10  2006/04/12 18:30:32  alex
 * back to album links reworked
 *
 * Revision 1.9  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.8  2006/03/08 17:42:26  alex
 * small fixes
 *
 * Revision 1.7  2006/03/03 16:54:56  alex
 * minor fixes
 *
 * Revision 1.6  2006/02/22 16:10:25  alex
 * added back link
 *
 * Revision 1.5  2006/02/22 14:00:51  alex
 * new album nav concept works also in display
 *
 * Revision 1.4  2006/02/20 16:54:49  alex
 * new album nav concept works
 *
 * Revision 1.3  2005/11/18 17:28:36  alex
 * back link, incl. new interface for naviable objects
 *
 * Revision 1.2  2005/11/05 21:41:58  alex
 * overview und links in tree menu
 *
 * Revision 1.1  2005/11/02 09:10:09  alex
 * album view works
 *
 ****************************************************************/
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UnartigException;
import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.businesslogic.NavigableObject;
import ch.unartig.studioserver.businesslogic.NavigationHelper;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class DisplayBean implements NavigableObject
{
    Logger _logger = Logger.getLogger(getClass().getName());
    private Photo displayPhoto;
    private Photo previousPhoto;
    private Photo nextPhoto;
    private AbstractAlbumBean albumBean;
    private Map backToAlbumParams;
    private Long displayPhotoId;


    public DisplayBean()
    {

    }

    public DisplayBean(Long displayPhotoId)
    {
        this.displayPhotoId = displayPhotoId;
    }

    /**
     * main mehtod called from action;
     * <br> will populate all the necessary information to display the display-view of the album
     * <br> todo: can albumbean be null? sure it can
     * <br> set the back to album link using the navigationhelper object
     */
    public void processDisplayBean() throws UnartigException
    {
        _logger.debug("album bean : " + albumBean);
        if (loadPhotosFromAlbumBean())
        {
            _logger.debug("success!");
//            _logger.debug("previous photo id: " + previousPhoto.getPhotoId());
//            _logger.debug("display photo id: " + displayPhoto.getPhotoId());
//            _logger.debug("next photo id: " + nextPhoto.getPhotoId());
        } else
        {
            _logger.debug("reload needed!");
            reloadAlbumBean();
            if (!loadPhotosFromAlbumBean())
            {
                _logger.warn("Can not load Photos for display view after reload!!!");
                throw new UnartigException("Not possible to load album bean");
            }
        }
        NavigationHelper.setBackToAlbumLink(this,albumBean);
    }

    /**
     * reload is needed, if the relevant photos are not found in the albumbean
     */
    private void reloadAlbumBean() throws UnartigException
    {
        // todo:
        _logger.debug("reloading photos for albumBean ["+albumBean.getClass().getName()+"]");
        albumBean.reloadPhotosTemplate(displayPhotoId);
    }

    /**
     * check the albumBean Photo List for the display photo, and the preview photos (next and previous thumbnails)
     *<pre>
     *Index
     *List:     ++---------++--------++-------++
     *                     |         |        |
     *Photo:              prev     disp     next
     * </pre>
     * @return true if all photos have been found and set or false, if at least one photo has not been found
     */
    private boolean loadPhotosFromAlbumBean() throws UnartigException
    {
        ListIterator albumPhotosIterator = albumBean.getPhotos().listIterator();
        _logger.debug("loading photos from album; found ["+albumBean.getPhotos().size()+"] photos");
        while (albumPhotosIterator.hasNext())
        {
            Photo photo = (Photo) albumPhotosIterator.next();
            _logger.debug("checking photo with id : " + photo.getPhotoId());
            // todo: what about first and last photo in album????
//            if (photo.getPhotoId().equals(displayPhotoId) && albumBean.getPhotos().get(albumPhotosIterator.nextIndex()-1)!=null && albumPhotosIterator.hasNext())
            if (photo.getPhotoId().equals(displayPhotoId))
            {   // found display photo
                _logger.debug("found displayphoto with id :" + photo.getPhotoId());
                _logger.debug("album photos next index : "+ albumPhotosIterator.nextIndex());
                displayPhoto = photo;

                // checking for next photo:
                if (albumPhotosIterator.hasNext())
                {// next photo exists, ok, set nextphoto
                    _logger.debug("next photo exists, ok, set nextphoto");
                    nextPhoto = (Photo)albumBean.getPhotos().get(albumPhotosIterator.nextIndex());
                }
                else if (displayPhoto.equals(albumBean.getLastPhotoInAlbumAndSelection()))
                { // display photo = last photo in album
                    // last photo does not need a prieview photo or link
                    _logger.debug("display photo = last photo in album");
                }
                else
                { // not end of album, next preview not available; reload needed
                    _logger.debug("not end of album, next preview not available; reload needed");
                    return false;
                }

                // one step back
                albumPhotosIterator.previous();

                // checking for previous photo
                if (albumPhotosIterator.hasPrevious())
                {// previous photo exists; done, return true
                    _logger.debug("previous photo exists; done, return true");
                    previousPhoto=(Photo)albumPhotosIterator.previous();
                    return true;
                }
                else //noinspection RedundantIfStatement
                    if (displayPhoto.equals(albumBean.getFirstPhotoInAlbumAndSelection()))
                    {// display photo = first photo in album
                        // first photo does not need back photo or link
                        _logger.debug("display photo = first photo in album");
                        return true;
                    }
                    else
                    { // not first photo and photo not available, reload needed
                        _logger.debug("not first photo and photo not available, reload needed");
                        return false;
                    }

            }
        }
        _logger.debug("Could not find display photo or preview photos; returning false");
        return false;
    }

    /**
     *
     * @return
     * @throws UAPersistenceException
     */
    public StudioAlbum getAlbumFromPhoto() throws UAPersistenceException
    {
        PhotoDAO photoDao= new PhotoDAO();
        StudioAlbum retVal = photoDao.load(displayPhotoId).getAlbum();
        _logger.debug("returning :" + retVal);
        return retVal;
    }
    public void setDisplayPhoto(Photo displayPhoto)
    {
        this.displayPhoto = displayPhoto;
    }

    public void setPreviousPhoto(Photo previousPhoto)
    {
        this.previousPhoto = previousPhoto;
    }

    public void setNextPhoto(Photo nextPhoto)
    {
        this.nextPhoto = nextPhoto;
    }

    public Photo getNextPhoto()
    {
        return nextPhoto;
    }

    public Photo getDisplayPhoto()
    {
        return displayPhoto;
    }

    public Photo getPreviousPhoto()
    {
     
        return previousPhoto;
    }

    public void setAlbumBean(AbstractAlbumBean albumBean)
    {
        this.albumBean = albumBean;
    }

    public AbstractAlbumBean getAlbumBean()
    {
        return albumBean;
    }

//    frontend-getters:

    public Map getPreviousPhotoLinkParams()
    {
        return new HashMap();
    }

    public Map getNextPhotoLinkParams()
    {
        return new HashMap();
    }


    public Map getBackToAlbumParams()
    {
        return backToAlbumParams;
    }

    public void setBackToAlbumParams(Map backToAlbumParams)
    {
        this.backToAlbumParams = backToAlbumParams;
    }

    public Long getDisplayPhotoId()
    {
        return displayPhotoId;
    }

    public void setDisplayPhotoId(Long displayPhotoId)
    {
        this.displayPhotoId = displayPhotoId;
    }



}
