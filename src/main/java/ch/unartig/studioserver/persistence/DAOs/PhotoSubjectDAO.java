/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since 29.03.2006$
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
 * Revision 1.1  2006/04/06 18:31:22  alex
 * display fixed for sports album
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.PhotoSubject;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class PhotoSubjectDAO
{
    /**
     * returns a photosubject with matching startnumber
     * todo: one or  more photosubjects can be returned? if there could be more, a different import routine that has more information shall be used
     * @param startNumber
     * @param album
     * @return
     * @throws UAPersistenceException
     */
    public PhotoSubject findByStartNumberAndAlbum(String startNumber, StudioAlbum album) throws UAPersistenceException
    {
        Criteria criteria = HibernateUtil.currentSession().createCriteria(PhotoSubject.class)
                .createAlias("eventRunners", "runner")
                .add(Restrictions.eq("runner.startnumber", startNumber))
                .add(Restrictions.eq("runner.event", album.getEvent()));


        return (PhotoSubject) criteria.uniqueResult();
    }

    /**
     * @param photoSubject
     * @return
     * @throws UAPersistenceException
     */
    public Long saveOrUpdate(PhotoSubject photoSubject) throws UAPersistenceException
    {
        return (Long)HibernateUtil.currentSession().save(photoSubject);
    }
}
