/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Oct 24, 2005$
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
 * Revision 1.24  2006/12/05 22:51:57  alex
 * album kann jetzt freigeschaltet werden oder geschlossen sein
 *
 * Revision 1.23  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.22  2006/05/01 12:43:49  alex
 * fix for album reload for sports and event album
 *
 * Revision 1.21  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.20  2006/04/10 21:07:02  alex
 * finish time mapping works
 *
 * Revision 1.19  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 * Revision 1.18  2006/03/20 17:20:37  alex
 * ui improvements, sportsalbum
 *
 * Revision 1.17  2006/03/20 15:33:33  alex
 * first check in for new sports album logic and db changes
 *
 * Revision 1.16  2006/03/08 17:42:26  alex
 * small fixes
 *
 * Revision 1.15  2006/02/22 16:10:25  alex
 * added back link
 *
 * Revision 1.14  2006/02/22 14:00:51  alex
 * new album nav concept works also in display
 *
 * Revision 1.13  2006/02/20 16:54:49  alex
 * new album nav concept works
 *
 * Revision 1.12  2006/01/27 09:30:36  alex
 * new pager implemenatation
 *
 * Revision 1.11  2005/11/19 16:31:43  alex
 * bookmarks of displays should work now
 *
 * Revision 1.10  2005/11/05 21:41:58  alex
 * overview und links in tree menu
 *
 * Revision 1.9  2005/11/05 10:32:14  alex
 * shopping cart and minor problems, exception handling
 *
 * Revision 1.8  2005/11/04 23:05:00  alex
 * error ...
 *
 * Revision 1.7  2005/11/04 23:02:54  alex
 * shopping cart session
 *
 * Revision 1.6  2005/11/02 09:10:09  alex
 * album view works
 *
 * Revision 1.5  2005/11/01 11:28:39  alex
 * pagination works; put logic in overview bean
 *
 * Revision 1.4  2005/10/26 20:40:12  alex
 * first view impl
 *
 * Revision 1.3  2005/10/26 15:36:44  alex
 * some fixes
 *
 * Revision 1.2  2005/10/26 14:34:32  alex
 * first version of album overview
 * new mappings in struts for the /album/** url
 *
 * Revision 1.1  2005/10/24 13:50:07  alex
 * upload of album
 * import in db 
 * processing of images
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.businesslogic.EventAlbum;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.SportsAlbum;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import ch.unartig.util.DebugUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class PhotoDAO
{

    Logger _logger = Logger.getLogger(getClass().getName());

    public void saveOrUpdate(Photo photo) throws UAPersistenceException
    {
        try
        {
            HibernateUtil.currentSession().saveOrUpdate(photo);
        } catch (HibernateException e)
        {
            _logger.error("Cannot save or update a Photo, see stack trace");
            throw new UAPersistenceException("Cannot save or update a Photo, see stack trace", e);
        }

    }

    /**
     * get first photo after the time the user has entered, or,  if no photo exists for the chosen hour, get first photo of album
     * <br>(helper query to find the correct page)
     *
     * @param hour
     * @param minutes
     * @param album
     * @return Photo
     * @throws UAPersistenceException
     */
    private Photo getFirstPhotoAfterTime(int hour, int minutes, StudioAlbum album) throws UnartigException
    {
//        todo: replace deprecated call from classic session
        _logger.debug("getting first photo for time and album");
        Photo firstPhoto;
        String sql = "SELECT {foto.*} from photos as foto ";
        sql += " WHERE date_part('hour',foto.pictureTakenDate)=" + hour + " AND date_part('minute',foto.pictureTakenDate) >=     " + minutes;
        sql += " AND foto.albumId = '" + album.getGenericLevelId() + "' ";
        sql += " ORDER BY foto.pictureTakenDate ";
        sql += " LIMIT 1";

        try
        {
            org.hibernate.classic.Session session = (org.hibernate.classic.Session) HibernateUtil.currentSession();
            firstPhoto = (Photo) session.createSQLQuery(sql, "foto", Photo.class).uniqueResult();
        } catch (HibernateException e)
        {
            _logger.error("Exception when getting first photo for time and albuem", e);
            throw new UAPersistenceException("trying to find first photo for time and albuem ... exception!!", e);
        }
        _logger.debug("firstPhoto = " + firstPhoto);
        if (firstPhoto == null)
        {
            _logger.debug("no photo found for given time ... return first photo for album");
            firstPhoto = getFirstPhotoFor(album);
        }
        return firstPhoto;
    }


    /**
     * make a query to the db and return a list of photos for the passed album during the passed interval
     *
     * @param hour
     * @param minutes
     * @param interval
     * @param album
     * @return a lost of photos
     * @throws ch.unartig.exceptions.UAPersistenceException
     */
    public List getPhotosForInterval(int hour, int minutes, int interval, StudioAlbum album) throws UAPersistenceException
    {
        List photos;
        String sql = "SELECT {foto.*} from photos as foto ";
        sql += "WHERE date_part('hour',foto.pictureTakenDate)=" + hour + " AND date_part('minute',foto.pictureTakenDate) BETWEEN " + minutes + " and " + (minutes + interval - 1) + " ";
        sql += "AND foto.albumId = '" + album.getGenericLevelId() + "' ";
        sql += "ORDER BY foto.pictureTakenDate";

        try
        {
            org.hibernate.classic.Session session = (org.hibernate.classic.Session) HibernateUtil.currentSession();
            photos = session.createSQLQuery(sql, "foto", Photo.class).list();
            /*
            todo: replace deprecated call
            List cats = sess.createSQLQuery("select {cat.*} from cats cat")
                    .addEntity("cat", Cat.class);
                    .setMaxResults(50);
                    .list();
            */
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("cannot query photos for interval, see stack trace", e);
        }

        return photos;

/*
Check the following part of the manual for replacing the deprecated use of the classic session

17.2. Alias and property references

The {cat.*} notation used above is a shorthand for "all properties". Alternatively, you may list the columns explicity, but even then you must let Hibernate inject the SQL column aliases for each property. The placeholder for a column alias is just the property name qualified by the table alias. In the following example, we retrieve Cats from a different table (cat_log) to the one declared in the mapping metadata. Notice that we may even use the property aliases in the where clause if we like. The {}-syntax is not required for named queries. See more in Section 17.3, “Named SQL queries”

String sql = "select cat.originalId as {cat.id}, " +
"cat.mateid as {cat.mate}, cat.sex as {cat.sex}, " +
"cat.weight*10 as {cat.weight}, cat.name as {cat.name} " +
"from cat_log cat where {cat.mate} = :catId"

List loggedCats = sess.createSQLQuery(sql)
.addEntity("cat", Cat.class)
.setLong("catId", catId)
.list();

Note: if you list each property explicitly, you must include all properties of the class and its subclasses!

*/
    }

    /**
     * list photos for a page ...
     * <br> if page is < 1 set page to 1
     *
     * @param page
     * @param album       the album is used as filter criterium
     * @param itemsOnPage
     * @return
     * @throws UAPersistenceException
     */
    public List getPhotosForPage(int page, StudioAlbum album, int itemsOnPage) throws UAPersistenceException
    {
        List photos;
        if (page < 1)
        {
            page = 1;
        }
        Criteria criteria = HibernateUtil.currentSession().createCriteria(Photo.class)
                .add(Expression.eq("album", album))
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(itemsOnPage)
                .setFirstResult((page - 1) * itemsOnPage);

        try
        {
            photos = criteria.list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Problem while retrieving Photos for Event : " + album.getLongTitle() + " ; see stack trace");
        }
        DebugUtils.debugPhotos(photos);
        return photos;
    }

    /**
     * list photos for a page ... list all photos of page and add one at the end and one at the beginning for the previews<br>
     * if page=1 do not extend selection at the beginning<br>
     * if page=1 extend maxresults only by 1 not by 2
     * todo: wrong result if album has exactly 16 photos
     *
     * @param page
     * @param album       the album is used as filter criterium
     * @param itemsOnPage
     * @return
     * @throws UAPersistenceException
     */
    public List listPhotosOnPagePlusPreview(int page, StudioAlbum album, int itemsOnPage) throws UAPersistenceException
    {
        _logger.debug("extended get photos for page");
        List photos;
        if (page < 1)
        {
            page = 1;
        }
        //if page=1 do not extend selection at the beginning:
        int firstResult = page == 1 ? ((page - 1) * itemsOnPage) : ((page - 1) * itemsOnPage) - 1;
        int maxResults = page == 1 ? itemsOnPage + 1 : itemsOnPage + 2;
        Criteria criteria = HibernateUtil.currentSession().createCriteria(Photo.class)
                .createAlias("album", "album")
                .add(Expression.eq("album.publish",Boolean.TRUE))
                .add(Expression.eq("album", album))
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(maxResults)
                .setFirstResult(firstResult);

        try
        {
            photos = criteria.list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Problem while retrieving Photos for Event : " + album.getLongTitle() + " ; see stack trace",e);
        }
        DebugUtils.debugPhotos(photos);
        return photos;
    }

    /**
     * @param album
     * @return
     * @throws UAPersistenceException
     */
    public int countPhotos(StudioAlbum album) throws UAPersistenceException
    {
        String query = "select count(*) from ch.unartig.studioserver.model.Photo as photo " + "       where photo.album = :album";
        Map map = new HashMap();
        map.put("album", album);
        Object queryObject = HibernateUtil.getUnique(query, map);
        _logger.debug("queryObject.getClass().getName() = " + queryObject.getClass().getName());
        return ((Long) queryObject).intValue();

    }

    /**
     * search first photos for an album an read until end of first interval
     * // todo: delete if not used anymore
     *
     * @param album
     * @param timeInterval
     * @return list of photos
     */
    private List getFirstPhotosFor(StudioAlbum album, int timeInterval) throws UnartigException
    {
        List photos;

        Date firstPhotoTakenDate = getFirstPhotoFor(album).getPictureTakenDate();

        Calendar cal = Calendar.getInstance();
        cal.setTime(firstPhotoTakenDate);
        // addieren: nur die minuten bis zur naechsten sequenz. z.b. von 15:58 auf 16:01
        int minutesToAdd = (timeInterval - (cal.get(Calendar.MINUTE) % timeInterval));
        cal.add(Calendar.MINUTE, minutesToAdd);
        cal.set(Calendar.SECOND, 0);

        _logger.debug("loading photos starting from : [" + firstPhotoTakenDate.toString() + "] To [" + cal.getTime().toString() + "]");
        Criteria c = HibernateUtil.currentSession().createCriteria(Photo.class);
        c.add(Expression.eq("album", album))
                .addOrder(Order.asc("pictureTakenDate"))
                .add(Expression.lt("pictureTakenDate", cal.getTime()));

        photos = c.list();
        return photos;  //To change body of created methods use File | Settings | File Templates.
    }


    /**
     * get the first photo of an album<br>
     * used to be private method
     *
     * @param album
     * @return
     * @throws UAPersistenceException
     */
    public Photo getFirstPhotoFor(StudioAlbum album) throws UnartigException
    {
        Criteria c = HibernateUtil.currentSession().createCriteria(Photo.class);
        c.add(Expression.eq("album", album))
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1);
        Photo firstPhoto = (Photo) c.uniqueResult();
        if (firstPhoto == null)
        {
            _logger.info("no photo found for getFirstPhoto");
            throw new UnartigException("The chosen Album contains no photos");
        }
        return firstPhoto;
    }

    public Photo load(Long photoId) throws UAPersistenceException
    {
        try
        {
            return (Photo) HibernateUtil.currentSession().load(Photo.class, photoId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load Generic Level, see stack trace", e);
        }
    }

    /**
     * user has chosen a time for a certain album. find out page nr where this time starts in the album
     *
     * @param album
     * @param hour
     * @param minutes
     * @return
     * @throws UnartigException
     */
    public int getPageNrFor(StudioAlbum album, int hour, int minutes) throws UnartigException
    {
        Photo firstPhoto = getFirstPhotoAfterTime(hour, minutes, album);
        return (getAlbumPageNrFor(firstPhoto));
    }

    /**
     * user has chosen a time for a certain album. find out page nr where this time starts in the album
     *
     * @param album   the album to chose the photos from
     * @param hour    0 - 23
     * @param minutes 0 - 59
     * @return page number (starting at 1)
     */
/*
    public int getAlbumPageNrFor(StudioAlbum album, int hour, int minutes) throws UAPersistenceException
    {
        int page;
        //count = count how many are BEFORE the chosen time ; position of first photo after chosen time = count + 1
        int count = 0;
        // counting without initializing the collection
//        ( (Integer) session.iterate("select count(*) from ....").next() ).intValue();
        try
        {
            Photo firstPhoto = getFirstPhotoForTime(hour, minutes, album);
            String query = "select count(*) " + "from ch.unartig.studioserver.model.Photo as photo " + "where photo.album = :album " + "and photo.pictureTakenDate < :firstPhotoDate";
            Map map = new HashMap();
            map.put("album", album);
            map.put("firstPhotoDate", firstPhoto.getPictureTakenDate());
            count = ((Integer) HibernateUtil.getUnique(query, map)).intValue();
        } catch (UAPersistenceException e)
        {
            _logger.error("error getting page nr.", e);
            throw new UAPersistenceException("getting page nr error", e);
        } catch (Exception e2)
        {
            _logger.error("unknown exception", e2);
        }
        // count = pos -1
        // page = (count / itemsOnPage) +1;
        page = (count / Registry.getItemsOnPage()) + 1;
        return page;
    }
*/

    /**
     * query for the position of the photo within the album<br>
     * use this result to calculate page<br/>
     * todo: refactor all three getAlbumPageNrFor methods!!!! they're more or less the same!!
     *
     * @param photo
     * @return
     * @throws UAPersistenceException
     */
    private int getAlbumPageNrFor(Photo photo) throws UAPersistenceException
    {
        int page;
        /**
         * position of that photo within the album
         */
        int position;
        Criteria c = HibernateUtil.currentSession().createCriteria(Photo.class);

        Object queryResult = c
                .add(Expression.le("pictureTakenDate", photo.getPictureTakenDate()))
                .add(Expression.eq("album", photo.getAlbum()))
                .setProjection(Projections.rowCount())
                .uniqueResult();

        position = ((Integer) queryResult).intValue();
        _logger.debug("position : " + position);
        page = ((position - 1) / Registry.getItemsOnPage()) + 1;
        _logger.debug("page : " + page);
        return page;
    }


    /**
     * convenience method to call <link>getAlbumPageNrFor(Photo photo)</link>
     *
     * @param displayPhotoId
     * @return
     * @throws UAPersistenceException
     */
    public int getAlbumPageNrFor(Long displayPhotoId) throws UAPersistenceException
    {
        return getAlbumPageNrFor(load(displayPhotoId));
    }

    /**
     * given the photoId , startnumber (if any), calculate the pagenumber for the passed EVENT album (i.e. among all photo in the event of the album)
     *
     * @param displayPhotoId
     * @param eventAlbum
     * @param startNumber
     * @return
     * @throws UAPersistenceException
     */
    public int getAlbumPageNrFor(Long displayPhotoId, EventAlbum eventAlbum, String startNumber) throws UAPersistenceException
    {
        _logger.debug("getting page number for eventAlbum ....");
        Photo photo = load(displayPhotoId);
        int page = 1;
        int position;
        Criteria c = createEventAlbumStartNumberCriteria(eventAlbum, startNumber);

        Object queryResult = c
                .add(Expression.le("pictureTakenDate", photo.getPictureTakenDate()))
                .setProjection(Projections.rowCount())
                .uniqueResult();

        position = ((Integer) queryResult).intValue();
        _logger.debug("position : " + position);
        page = ((position - 1) / Registry.getItemsOnPage()) + 1;
        _logger.debug("page : " + page);
        return page;
    }

    /**
     * given the photoId , startnumber (if any), calculate the pagenumber for the passed album
     *
     * @param displayPhotoId
     * @param sportsAlbum
     * @return
     * @throws UAPersistenceException
     * @param startNumber
     */
    public int getAlbumPageNrFor(Long displayPhotoId, SportsAlbum sportsAlbum, String startNumber) throws UAPersistenceException
    {
        _logger.debug("getting page number for sportsalbum ....");
        Photo photo = load(displayPhotoId);
        int page = 1;
        int position;
        Criteria c = createStartnumberCriteria(sportsAlbum, startNumber);

        Object queryResult = c
                .add(Expression.le("pictureTakenDate", photo.getPictureTakenDate()))
                .setProjection(Projections.rowCount())
                .uniqueResult();

        position = ((Integer) queryResult).intValue();
        _logger.debug("position : " + position);
        page = ((position - 1) / Registry.getItemsOnPage()) + 1;
        _logger.debug("page : " + page);
        return page;
    }


    /**
     * todo: is this used?
     *
     * @param studioAlbum
     * @return
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     */
    private Photo getFirstPhotoInAlbum(StudioAlbum studioAlbum) throws UAPersistenceException
    {
        return (Photo) HibernateUtil.currentSession().createCriteria(Photo.class)
                .add(Expression.eq("album", studioAlbum))
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1)
                .uniqueResult();
    }

    /**
     * dao method to list photos for a given startnumber
     *
     * @param page
     * @param album
     * @param itemsOnPage
     * @param startnumber
     * @return a list of photos
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     */
    public List listSportsPhotosOnPagePlusPreview(int page, StudioAlbum album, int itemsOnPage, String startnumber) throws UAPersistenceException
    {
        _logger.debug("extended get sport photos for page");
        List photos;
        if (page < 1)
        {
            page = 1;
        }
        //if page=1 do not extend selection at the beginning:
        int firstResult = page == 1 ? ((page - 1) * itemsOnPage) : ((page - 1) * itemsOnPage) - 1;
        int maxResults = page == 1 ? itemsOnPage + 1 : itemsOnPage + 2;
        Criteria criteria = createStartnumberCriteria(album, startnumber);
        criteria.setMaxResults(maxResults)
                .setFirstResult(firstResult)
                .addOrder(Order.asc("pictureTakenDate"));
        try
        {
            photos = criteria.list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Problem while retrieving Photos for Event : " + album.getLongTitle() + " ; see stack trace", e);
        }
        if (_logger.isDebugEnabled())
        {
            DebugUtils.debugPhotos(photos);
        }
        return photos;
    }

    /**
     * for EventAlbum
     *
     * @param page
     * @param album
     * @param itemsOnPage
     * @param startnumber
     * @return
     * @throws UAPersistenceException
     */
    public List listSportsPhotosOnPagePlusPreview(int page, EventAlbum album, int itemsOnPage, String startnumber) throws UAPersistenceException
    {
        _logger.debug("sport photos for EVENT ALBUM!!");

        List photos;
        if (page < 1)
        {
            page = 1;
        }
        //if page=1 do not extend selection at the beginning:
        int firstResult = page == 1 ? ((page - 1) * itemsOnPage) : ((page - 1) * itemsOnPage) - 1;
        int maxResults = page == 1 ? itemsOnPage + 1 : itemsOnPage + 2;


        Criteria criteria = createEventAlbumStartNumberCriteria(album, startnumber);


        criteria.setMaxResults(maxResults)
                .setFirstResult(firstResult)
                .addOrder(Order.asc("pictureTakenDate"));
        try
        {
            photos = criteria.list();
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Problem while retrieving Photos for Event : " + album.getLongTitle() + " ; see stack trace", e);
        }
        if (_logger.isDebugEnabled())
        {
            DebugUtils.debugPhotos(photos);
        }
        return photos;
    }


    /**
     * construct a criteria for a passed album and startNumber
     *
     * @param album
     * @param startnumber
     * @return
     * @throws UAPersistenceException
     */
    private Criteria createStartnumberCriteria(StudioAlbum album, String startnumber) throws UAPersistenceException
    {
        Criteria criteria = HibernateUtil.currentSession().createCriteria(Photo.class)
                .add(Restrictions.eq("album", album));

        if (startnumber != null && !"".equals(startnumber))
        {
            criteria.createAlias("photoSubjects", "sub")
                    .createAlias("sub.eventRunners", "eventRunner")
                    .add(Restrictions.eq("eventRunner.startnumber", startnumber));

        }
        return criteria;
    }

    /**
     * count all photos for a passed startNumber and album
     *
     * @param startNumber
     * @param album
     * @return
     * @throws UAPersistenceException
     */
    public int countPhotosFor(String startNumber, StudioAlbum album) throws UAPersistenceException
    {
        Integer count = (Integer) createStartnumberCriteria(album, startNumber)
                .setProjection(Projections.rowCount())
                .uniqueResult();
        _logger.debug("Photo count = " + count);
        return count.intValue();
    }

    /**
     * count all photos (of the given event in EventAlbum) for a passed startNumber and EventAlbum
     *
     * @param startNumber
     * @param album
     * @return
     * @throws UAPersistenceException
     */
    public int countPhotosFor(String startNumber, EventAlbum album) throws UAPersistenceException
    {
        _logger.debug("count for EventAlbum!! ");
        Criteria criteria = createEventAlbumStartNumberCriteria(album, startNumber);
        Integer count = (Integer) criteria
                .setProjection(Projections.rowCount())
                .uniqueResult();
        _logger.debug("Photo count = " + count);
        return count.intValue();
    }

    /**
     * tries to find a photo by its albumid and a given (case-insensitive) filename<br>
     * return null if no photos is found
     *
     * @param album
     * @param filename
     * @return Photo or null
     */
    public Photo findPhoto(StudioAlbum album, String filename) throws UAPersistenceException
    {
        Criteria criteria = HibernateUtil.currentSession().createCriteria(Photo.class)
                .add(Restrictions.eq("album", album))
                .add(Restrictions.ilike("filename", filename));

        return (Photo) criteria.uniqueResult();
    }

    /**
     * find the last photo in an album filtered by start number
     *
     * @param album
     * @param startNumber
     * @return the last photo in an album filtered by startnumber
     */
    public Photo getLastPhotoInAlbumAndSelection(StudioAlbum album, String startNumber) throws UAPersistenceException
    {

        Photo lastPhoto = (Photo) createStartnumberCriteria(album, startNumber)
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1)
                .setFirstResult(lastIndexFor(startNumber, album))
                .uniqueResult();
        _logger.debug("returning last lastPhoto : " + lastPhoto);
        return lastPhoto;
    }

    /**
     * find the last photo in an event-album filtered by start number
     *
     * @param album
     * @param startNumber
     * @return the last photo in an album filtered by startnumber
     */
    public Photo getLastPhotoInAlbumAndSelection(EventAlbum album, String startNumber) throws UAPersistenceException
    {


        Integer count = (Integer) createEventAlbumStartNumberCriteria(album, startNumber)
                .setProjection(Projections.rowCount())
                .uniqueResult();
        _logger.debug("Photo count = " + count);

        Photo lastPhoto = (Photo) createEventAlbumStartNumberCriteria(album, startNumber)
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1)
                .setFirstResult(count.intValue() - 1)
                .uniqueResult();
        _logger.debug("returning last lastPhoto : " + lastPhoto);
        return lastPhoto;
    }

    /**
     * construct a criteria for a passed EventAlbum and startNumber
     *
     * @param album
     * @param startNumber
     * @return criteria
     * @throws UAPersistenceException
     */
    private Criteria createEventAlbumStartNumberCriteria(EventAlbum album, String startNumber) throws UAPersistenceException
    {
        Criteria criteria = HibernateUtil.currentSession().createCriteria(Photo.class)
                .createAlias("album", "etappe")
                .add(Restrictions.eq("etappe.event", album.getEvent()));

        if (startNumber != null && !"".equals(startNumber))
        {
            criteria.createAlias("photoSubjects", "sub")
                    .createAlias("sub.eventRunners", "eventRunner")
                    .add(Restrictions.eq("eventRunner.startnumber", startNumber));
        }
        return criteria;
    }


    /**
     * return the first photo for a given album and selection
     * <br> used to calculate the settings for the preview photo
     *
     * @param album
     * @param startNumber
     * @return first Photo
     */
    public Photo getFirstPhotoInAlbumAndSelection(StudioAlbum album, String startNumber) throws UAPersistenceException
    {
        Photo firstPhoto = (Photo) createStartnumberCriteria(album, startNumber)
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1)
                .uniqueResult();
        _logger.debug("returning  firstPhoto : " + firstPhoto);
        return firstPhoto;
    }

    /**
     * return the first photo for a given event-album and selection
     * <br> used to calculate the settings for the preview photo
     *
     * @param album
     * @param startNumber
     * @return first Photo
     */
    public Photo getFirstPhotoInAlbumAndSelection(EventAlbum album, String startNumber) throws UAPersistenceException
    {
        Criteria criteria = createEventAlbumStartNumberCriteria(album, startNumber);

        Photo firstPhoto = (Photo) criteria
                .addOrder(Order.asc("pictureTakenDate"))
                .setMaxResults(1)
                .uniqueResult();
        _logger.debug("returning  firstPhoto : " + firstPhoto);
        return firstPhoto;
    }

    /**
     * calculates the index of the last photo for the given album and startnumber ordered by phototaken time
     *
     * @param startNumber
     * @param album
     * @return index of the last photo for given album and startnumber
     * @throws UAPersistenceException
     */
    private int lastIndexFor(String startNumber, StudioAlbum album) throws UAPersistenceException
    {
        return countPhotosFor(startNumber, album) - 1;
    }

    /**
     * used for sportsalbum: this method returns all photo within the specifed time in the passed arguments
     * ASSUMPTION: the photos of the etappe have the same day, month and year as the passed date !!
     * Assumption: all etappen or albums of the event have the same date!!!
     * todo: adapt if day or month changes during an event
     * <br>
     *
     * @param album
     * @param minMatchTime
     * @param maxMatchTime
     * @return List of Photo s
     */
    public List findFinishTimePhotos(StudioAlbum album, Date minMatchTime, Date maxMatchTime) throws UAPersistenceException
    {
        List retVal;

        // todo: good strategy to compare the dates.
        // todo: ask peter wegmann to include date information
        // todo : quick and dirty solution ignoring the dates: get first photo of etappe and replace the time information, assuming the date information is invariable
        // todo: replace for any sports event that crosses a date border. I.E. 24h races or so ....

        Criteria crit = HibernateUtil.currentSession().createCriteria(Photo.class)
                .add(Expression.eq("album", album))
                .add(Restrictions.le("pictureTakenDate", maxMatchTime))
                .add(Restrictions.ge("pictureTakenDate", minMatchTime))
                .addOrder(Order.asc("pictureTakenDate"));

        retVal = crit.list();
        return retVal;
    }

}

