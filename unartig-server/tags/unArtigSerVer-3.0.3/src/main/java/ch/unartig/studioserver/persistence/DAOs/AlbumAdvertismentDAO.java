/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 10.11.2006$
 *
 * Copyright (c) 2006 Alexander Josef,unartig AG; All rights reserved
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
 * Revision 1.2  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.1  2006/11/10 15:55:30  alex
 * dynamic album ads
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.AlbumAdvertisment;
import ch.unartig.studioserver.model.GenericLevel;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;

public class AlbumAdvertismentDAO
{
    Logger _logger = Logger.getLogger(getClass().getName());

    public void saveOrUpdate(AlbumAdvertisment albumAdvertisment) throws UAPersistenceException
    {
        try
        {
            AlbumAdvertisment existingAlbumAd = getAdvertismentFor(albumAdvertisment.getAlbum(), albumAdvertisment.getPosition());
            if (existingAlbumAd!=null && !existingAlbumAd.getAlbumAdvertismentId().equals(albumAdvertisment.getAlbumAdvertismentId()))
            {
                throw new UAPersistenceException("no double entries for AlbumAdds ... Ad for this level and position exists []");
            }
            HibernateUtil.currentSession().saveOrUpdate(albumAdvertisment);
        } catch (HibernateException e)
        {
            _logger.error("Cannot save or update a Category, see stack trace");
            throw new UAPersistenceException("Cannot save or update a generic Level, see stack trace", e);
        }
    }

    /**
     * query the generic levels for the advertisement insert for the passed position
     * <p> list of positions:
     * <ul>
     * <li>right    the skyscraper on the right side
     * </ul>
     *
     * @param position of the ad
     * @param level    generic level
     * @return rendered ad
     * @throws ch.unartig.exceptions.UAPersistenceException db problem
     */
    public AlbumAdvertisment getAdvertismentFor(GenericLevel level, String position) throws UAPersistenceException
    {
        try
        {
            return (AlbumAdvertisment) HibernateUtil.currentSession()
                    .createCriteria(AlbumAdvertisment.class)
                    .add(Expression.eq("position", position))
                    .add(Expression.eq("album", level))
                    .uniqueResult();
        } catch (UAPersistenceException e)
        {
            _logger.error("More than 1 add for level and position?",e);
            throw new UAPersistenceException(e);
        }

    }

}
