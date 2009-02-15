/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since 15.03.2006$
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
 * Revision 1.5  2006/12/05 22:51:57  alex
 * album kann jetzt freigeschaltet werden oder geschlossen sein
 *
 * Revision 1.4  2006/05/01 12:43:48  alex
 * fix for album reload for sports and event album
 *
 * Revision 1.3  2006/04/19 21:31:53  alex
 * session will be restored with album-bean (i.e. for bookmarked urls or so...)
 *
 * Revision 1.2  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.1  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 ****************************************************************/
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.SportsAlbum;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Josef, 2006
 *         Common Class to all Albums.
 *         <br>Rule for handling album access:
 *         <br>page = 0 --> set page by parameters (i.e. time or startnummer etc)
 *         <br>page = 1 --> page = 1 is passed for all 'initial' album access, i.e. when they are accessed from the menues
 */
public abstract class AbstractAlbumBean
{
    private static Logger _logger = Logger.getLogger(AbstractAlbumBean.class);
    // BEAN PROPERTIES:
    protected StudioAlbum album;
    /**
     * photos that are shown on the current page in the album as thumbnails
     */
    protected List photos;
    protected int page;
    /*number of pages in the current album; calculated while populating album*/
    protected int numberOfPages = -1;
    /**
     * total number of photos ? used to calculate page numbers
     */
    protected int size;
    /**
     * ordered photos from shopping cart<br>
     * they are used to make ordered photos visible on the album view with the shopping cart icon
     */
    protected Map orderedPhotos = new HashMap();
    // todo: changed the initial value to new Integer(0)
    // delete this if everthing ok
    //    private Integer hour = null;
    protected Integer hour = new Integer(0);
    protected int minutes;
    protected ShoppingCart shoppingCart;
    /**
     * this constant number tells the navigation if all page number can be shown or if they need to be clipped<br>
     * todo example
     */
//    public static int _maxPageNumbersFitOnNavigation = 20; // 13 ?
    private int _maxPageNumbersFitOnNavigation = 13; // 13 ?
    /**
     * type time or no time
     */
    protected String type;
    private final int middlePartSize = 11;

    public int getNumberOfPages()
    {
        return numberOfPages;
    }


    /**
     * template method
     *
     * @throws UnartigException
     */
    private void checkForValidPageNr() throws UnartigException
    {
        if (page < 1)
        {
            _logger.debug("page number must not be smaller than 1 ... throwing exception");
            throw new UnartigException("Page number < 1 in albumbean ...");
        }
    }


    /**
     * this template method is called by the action<br>
     * set page 1 if page < 1
     * <br>
     * populate the bean<br>
     * set page 1 if page < 1 <br>
     * hour is initialized to 0 (possible null pointer exception if left to null)
     * <p/>
     * todo: discribe which strategy is used to populate the bean
     * todo: define hooks
     *
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     * @throws ch.unartig.exceptions.UnartigException
     *
     */
    public final void populateAlbumBeanTemplate() throws UnartigException
    {
        // only set photos if album has publish = true
        setPageBySearchParameter();
        checkForValidPageNr();
        setPhotosForCurrentSession();
        setTotalNumberOfPhotosForSession();
        setNumberOfPages();
        handleOrderedPhotos();
    }

    private void setNumberOfPages()
    {
        setNumberOfPages(1 + (size - 1) / Registry.itemsOnPage);
    }

    /**
     * 'standard' implementation, that counts all photos that are in an album. uses method of genericlevel
     *
     * @throws ch.unartig.exceptions.UnartigException
     *
     */
    protected void setTotalNumberOfPhotosForSession() throws UnartigException
    {
        setSize(album.getNumberOfPhotos());
    }

    /**
     * template method:
     * <br>default implementation: list according to time
     *
     * @throws UAPersistenceException
     */
    protected void setPhotosForCurrentSession() throws UAPersistenceException
    {
        _logger.debug("populating for page : " + page + " in Album [" + album.getGenericLevelId() + "]," + album.getNavTitle());
        List photosOnPagePlusPreview;
        PhotoDAO photoDao = new PhotoDAO();
        photosOnPagePlusPreview = photoDao.listPhotosOnPagePlusPreview(page, album, Registry.getItemsOnPage());
        _logger.debug("found : " + photosOnPagePlusPreview.size() + " photos");
        setPhotos(photosOnPagePlusPreview);
    }

    /**
     * sets param ordered photos (= photos in shopping cart) to be used in view to read out ordered photos
     */
    private void handleOrderedPhotos()
    {
        if (shoppingCart != null)
        {
            setOrderedPhotos(shoppingCart.getOrderedPhotosMap());
        }
    }

    /**
     * mandatory part of template to implement a strategy to set the page number
     * <br> todo: describe this part of the template
     * @throws ch.unartig.exceptions.UnartigException
     */
    public abstract void setPageBySearchParameter() throws UnartigException;

    public void setOrderedPhotos(Map orderedPhotos)
    {
        this.orderedPhotos = orderedPhotos;
    }

    /**
     * @param album
     */
    public void setAlbum(StudioAlbum album)
    {
        this.album = album;
    }

    public StudioAlbum getAlbum()
    {
        return this.album;
    }

    public int getPage()
    {
        return this.page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    /**
     * photos that are shown on the current page in the album as thumbnails
     *
     * @return list of Photo-objects
     */
    public List getPhotos()
    {
        return photos;
    }

    public boolean hasMorePages()
    {
        return true;
    }

    public ShoppingCart getShoppingCart()
    {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart)
    {
        this.shoppingCart = shoppingCart;
    }

    public final void setNumberOfPages(int numberOfPages)
    {
        _logger.debug("setting number of pages : " + getNumberOfPages() + "; total photos : " + getSize());
        this.numberOfPages = numberOfPages;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    /**
     * @param photos that are shown on the current page in the album as thumbnails
     */
    public void setPhotos(List photos)
    {
        this.photos = photos;
    }

    public String getType()
    {
        return type;
    }

    /**
     * @return next page
     */
    public int getNextPage()
    {
        if (page < numberOfPages)
        {
            return page + 1;
        } else
        {
            return page;
        }
    }

    public int getPreviousPage()
    {
        return page > 1 ? page - 1 : 1;
    }

    /**
     * calculate Map backed property
     *
     * @return Map backed property 'previousPageParams'
     */
    public Map getNextPageParams()
    {
        Map retVal = new HashMap();

        retVal.put(Registry._NAME_PAGE_PARAM, "" + getNextPage());
        retVal.put(Registry._NAME_ALBUM_TYPE_PARAM, getType());
        return retVal;
    }

    /**
     * Mapped backed property
     *
     * @return Mapped backed property 'previousPageParams'
     */
    public Map getPreviousPageParams()
    {
        Map retVal = new HashMap();

        retVal.put(Registry._NAME_PAGE_PARAM, "" + getPreviousPage());
        retVal.put(Registry._NAME_ALBUM_TYPE_PARAM, getType());
        return retVal;
    }

    /**
     * getter used by album view; jump 10 pages forward if page +10 < total number of pages
     *
     * @return page nr to jump forward to
     */
    public Map getJumpForwardParams()
    {
        Map retVal = new HashMap();
        int jumpForwardPage = page + Registry._JUMP_BACK_FORWARD_PAGE_VALUE;

        retVal.put(Registry._NAME_PAGE_PARAM, "" + (jumpForwardPage > numberOfPages ? numberOfPages : jumpForwardPage));
        retVal.put(Registry._NAME_ALBUM_TYPE_PARAM, getType());
        return retVal;
    }

    /**
     * getter used by album view; jump 10 pages back if page -10 >1
     *
     * @return page nr. to jump back to
     */
    public Map getJumpBackParams()
    {
        Map retVal = new HashMap();
        int jumpBackPage = page - Registry._JUMP_BACK_FORWARD_PAGE_VALUE;

        retVal.put(Registry._NAME_PAGE_PARAM, "" + (jumpBackPage < 1 ? 1 : jumpBackPage));

        retVal.put(Registry._NAME_ALBUM_TYPE_PARAM, getType());
        return retVal;
    }

    /**
     * calculate the first page number in the middle part of the pager for the view<br>
     * see also navPage.jsp
     *
     * @return int middle part start page
     */
    public int getPagerMiddlepartStart()
    {
        /**
         * number of pages that are shown in the middle part of the pager nav<br>
         * this is the actual page, 5 pages more and 5 pages back
         */
        int retVal;
        /* the middle part is shown and both, start .... and  ... end*/
        if (page >= _maxPageNumbersFitOnNavigation - 2 && page + (middlePartSize / 2) < numberOfPages)
        {
            retVal = page - (middlePartSize / 2);
        }
        /*start part ist shown, middle but no end ... middle part inlcudes end*/
        else if (page + (middlePartSize / 2) >= numberOfPages)
        {
            return numberOfPages - (middlePartSize - 1);
        }
        /*no start part, included in middle part, end part is shown*/
        else
        {
            retVal = 1;
        }
        return retVal;
    }

    /**
     * calculate the ending of the middle part in the 'pager' for the view<br>
     * see also navPage.jsp
     *
     * @return int middle part end page
     */
    public int getPagerMiddlepartEnd()
    {
        /**
         * number of pages that are shown in the middle part of the pager nav<br>
         * this is the actual page, 5 pages more and 5 pages back
         */
//        int middlePartSize = 11;
        int retVal;

        /* the middle part is shown and both, start .... and  ... end*/
//        if (page > 19 && page + 9 < numberOfPages)
        if (page >= _maxPageNumbersFitOnNavigation - 2 && page + (middlePartSize / 2) < numberOfPages)
        {
            retVal = page + (middlePartSize / 2);
        }
        /*start part ist shown, middle but no end ... middle part inlcudes end*/
        else if (page + (middlePartSize / 2) >= numberOfPages)
        {
            return numberOfPages;
        }
        /*no start part, included in middle part, end part is shown*/
        else
        {
            retVal = middlePartSize;
        }
        return retVal;
    }

    public Map getOrderedPhotos()
    {
        return orderedPhotos;
    }

    /**
     * special <b>getter-method for view</b> : only return the photos that are shown on the current page<br>
     * special cases are treated:
     * first page :
     * last page:
     * <p/>
     * note: see the subList(From, To) specification and list.size()
     *
     * @return a list of photos that doesn't include the extra photos that are added for the previews
     */
    public final List getPhotosOnPage()
    {

        // page = first AND! last page special case (THIS NEEDS TO BE CHECKED FIRST)
        if (numberOfPages == 1)
        {
            _logger.debug("returning photos for only one page");
            return photos;
        }

        // page = first page special case --> don't cut at the beginning; minus 1 at the end
        // photos list has 16 photos
        // siz
        else if (page == 1)
        {
            _logger.debug("returning photos; first page");
            _logger.debug("returning [" + photos.subList(0, Registry.itemsOnPage).size() + "] Photos");
            return photos.subList(0, Registry.itemsOnPage);
        }

        // page = last page special case --> don't cut at the end; minus one for first photo
        // last index is photos.size(); include last photo
        else if (page == numberOfPages)
        {
            _logger.debug("returning photos; last page");
            _logger.debug("skipping first :" + photos.get(0));
            return photos.subList(1, photos.size());
        }

        // all other pages --> cut one at the beginning, one at the end
        else
        {

            _logger.debug("returning photos; neither first nor last page");
            _logger.debug("skipping first :" + photos.get(0));
            _logger.debug("skipping last :" + photos.get(photos.size() - 1));
            return photos.subList(1, photos.size() - 1);
        }
    }

    public int getMaxPageNumbersFitOnNavigation()
    {
        return _maxPageNumbersFitOnNavigation;
    }

    public void setMaxPageNumbersFitOnNavigation(int maxPageNumbersFitOnNavigation)
    {
        this._maxPageNumbersFitOnNavigation = maxPageNumbersFitOnNavigation;
    }

    /**
     * called from display bean if it can't find the photos to display<br>
     *
     * @param displayPhotoId
     */
    public void reloadPhotosTemplate(Long displayPhotoId) throws UnartigException
    {
        // todo: check if album needs to be loaded
        // todo: new strategy: set what needs to be set but use then the poulateAlbumBeanTemplate, this way all special albums etc are being handled
        _logger.debug("populating for page : " + page + " in Album [" + album.getGenericLevelId() + "]," + album.getNavTitle());
        setPageFor(displayPhotoId);
        populateAlbumBeanTemplate();
    }

    /**
     * this method gets the page number at a specific photo position
     * <br/> CAUTION: this is the base method and works if no filter is applied (i.e. for sportsalbum this method is overridden)
     *
     * @param displayPhotoId
     * @throws UAPersistenceException
     */
    protected void setPageFor(Long displayPhotoId) throws UAPersistenceException
    {
        PhotoDAO phDao = new PhotoDAO();
        page = phDao.getAlbumPageNrFor(displayPhotoId);
    }

    /**
     * return the last Photo in this selected Album;
     * if the album is filtered by startnumbers etc. it's only the last photo of the album AND selection
     * todo: AlbumBean caches the last Album for performance reasons ; how do we make sure last photo information is not stale?
     *
     * @return last Photo in Album
     */
    public Photo getLastPhotoInAlbumAndSelection() throws UnartigException
    {
        _logger.debug("getting last photo of album and selection for :" + this);
        return album.getLastPhotoInAlbumAndSelection();
    }

    /**
     * return the first photo for this album and selection
     *
     * @return first Photo
     * @throws UnartigException
     */
    public Photo getFirstPhotoInAlbumAndSelection() throws UnartigException
    {
        _logger.debug("getting first photo of album and selection for :" + this);
        return album.getFirstPhotoInAlbum();
    }

    public static AbstractAlbumBean getInstance(StudioAlbum album)
    {
        _logger.debug("creating new AlbumBean");
        return new AlbumBean(album);
    }

    public static AbstractAlbumBean getInstance(SportsAlbum album)
    {
        _logger.debug("creating new SportsAlbumBean");
        return new SportsAlbumBean(album);  //To change body of created methods use File | Settings | File Templates.
    }
}
