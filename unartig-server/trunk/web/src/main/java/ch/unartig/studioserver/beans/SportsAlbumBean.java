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
 * Revision 1.6  2006/05/01 12:43:48  alex
 * fix for album reload for sports and event album
 *
 * Revision 1.5  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.4  2006/04/19 21:31:53  alex
 * session will be restored with album-bean (i.e. for bookmarked urls or so...)
 *
 * Revision 1.3  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.2  2006/03/21 17:17:03  alex
 * sportsalbum changes, empty etappe now works
 *
 * Revision 1.1  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 ****************************************************************/
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.businesslogic.EventAlbum;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.SportsAlbum;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Bean for handling sports events
 * <br>implements the page-paramter part of the populate template from the abstract album bean
 * @author Alexander Josef, 2006
 */
public class SportsAlbumBean extends AbstractAlbumBean
{

    Logger _logger = Logger.getLogger(getClass().getName());
    
    private String startNumber;
    /*etappe is equal to a sportsAlbum*/
    private Long etappe;

    private boolean albumLevel;
    private boolean eventAlbum;
    private String levelId;


    public SportsAlbumBean()
    {
    }

    public SportsAlbumBean(SportsAlbum album)
    {
        this.album = album;
    }

    /**
     * implementation of the abstract template method from abstractAlbumBean
     * <br> set page according to start-number
     * @throws UnartigException
     */
    public void setPageBySearchParameter() throws UnartigException
    {
//        todo
        // read startnumber from persons table
        // get photos that are mapped to that person according to "persons2photos"
        // prepare album
        // set page to 1

        // only find first photo that matches a person and then calculate page nr?
        // NO! how should someone find his or her photos, if this person was photographed at the start and at the end?


        // todo: refinde template in a way, that we can redefine the strategy to read the photos per album more flexibel
        if (page < 1)
        {
            page = 1;
        }

    }

    /**
     * count total number of photos that have been selected for this session with the etappe and startnummer AND page
     * @throws UnartigException
     */
    protected void setTotalNumberOfPhotosForSession() throws UnartigException
    {
        // todo: implement for sportsAlbum   USE IN ABSTRACT ALBUM AND IMPLEMENT DIFFERENTLY IN DAO ??
        PhotoDAO photoDao = new PhotoDAO();
        if (album!=null && album.getGenericLevelId().intValue()>0)
        {
            setSize(photoDao.countPhotosFor(startNumber, album));
        }
        else if (album!=null)
        {
            // eventalbum:
            setSize(photoDao.countPhotosFor(startNumber, (EventAlbum) album));
        }
        else
        {
            throw new UnartigException("Can not count photos for session ....");
        }
//        super.setTotalNumberOfPhotosForSession();
    }

    protected void setPhotosForCurrentSession() throws UAPersistenceException
    {
        List photosOnPagePlusPreview = null;
        PhotoDAO photoDao = new PhotoDAO();
        if (album!=null && album.getGenericLevelId().intValue()>0)
        { // this means it's not an event album that shows all photos of an event
            _logger.debug("querying db for album : ["+album.getGenericLevelId()+"] and page ["+page+"]");
            photosOnPagePlusPreview = photoDao.listSportsPhotosOnPagePlusPreview(page, album, Registry.getItemsOnPage(),startNumber);
        } else if (album!=null)
        {
            // this must be the event album that shows all photos of an event with the chosen startnumber
            photosOnPagePlusPreview = photoDao.listSportsPhotosOnPagePlusPreview(page, (EventAlbum)album, Registry.getItemsOnPage(),startNumber);
        }
        _logger.debug("found : " + photosOnPagePlusPreview.size() + " photos");
        setPhotos(photosOnPagePlusPreview);
    }

    public Photo getLastPhotoInAlbumAndSelection() throws UnartigException
    {
        // todo: shall we have album overridden in sportsalbumbean in order ?
        _logger.debug("getting last photo of album and selection for :"+this);
        PhotoDAO phDao = new PhotoDAO();
        if (album.getGenericLevelId().intValue()>0)
        {
            return phDao.getLastPhotoInAlbumAndSelection(album,startNumber);
        } else
        { // we have an eventalbum
            return phDao.getLastPhotoInAlbumAndSelection((EventAlbum)album,startNumber);
        }

//        album.getLastPhotoInAlbumAndSelection()
    }

    public Photo getFirstPhotoInAlbumAndSelection() throws UnartigException
    {
        _logger.debug("getting first photo of album and selection for :"+this);
        PhotoDAO phDao = new PhotoDAO();
        if (album.getGenericLevelId().intValue()>0)
        {
            return phDao.getFirstPhotoInAlbumAndSelection(album,startNumber);
        } else
        { // we have an eventalbuem
            return phDao.getFirstPhotoInAlbumAndSelection((EventAlbum)album,startNumber);
        }
    }

    protected void setPageFor(Long displayPhotoId) throws UAPersistenceException
    {
        // todo get rid of casts ... create an album interface
        _logger.debug("setting new page number for sportsAlbum");
        PhotoDAO phDao = new PhotoDAO();
        if (album.getGenericLevelId().intValue()>0)
        {
            page = phDao.getAlbumPageNrFor(displayPhotoId,(SportsAlbum)album,getStartNumber());
        } else
        { // we have an eventalbuem
            page = phDao.getAlbumPageNrFor(displayPhotoId,(EventAlbum)album, startNumber);
        }
    }

    public String getStartNumber()
    {
        return startNumber;
    }

    public void setStartNumber(String startNumber)
    {
        this.startNumber = startNumber;
    }

    public Long getEtappe()
    {
        return etappe;
    }

    public void setEtappe(Long etappe)
    {
        this.etappe = etappe;
    }

    public boolean isAlbumLevel()
    {
        return albumLevel;
    }

    public void setAlbumLevel(boolean albumLevel)
    {
        this.albumLevel = albumLevel;
    }

    public String getLevelId()
    {
        return levelId;
    }

    public void setLevelId(String levelId)
    {
        this.levelId = levelId;
    }

    public boolean isEventAlbum()
    {
        return eventAlbum;
    }

    public void setEventAlbum(boolean eventAlbum)
    {
        this.eventAlbum = eventAlbum;
    }
}
