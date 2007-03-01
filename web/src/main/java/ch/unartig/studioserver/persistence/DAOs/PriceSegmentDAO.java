/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 19, 2005$
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
 * Revision 1.1  2005/11/19 20:32:32  alex
 * price segments inserted
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.PriceSegment;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import java.util.List;

public class PriceSegmentDAO
{
    /**
     * list ALL products
     *
     * @return
     * @throws ch.unartig.exceptions.UAPersistenceException
     *
     */
    public List listPriceSegments() throws UAPersistenceException
    {
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(PriceSegment.class)
                .addOrder(Order.asc("priceSegmentId"));
        return c.list();
    }

    public PriceSegment load(Long priceSegmentId) throws UAPersistenceException
    {
        try
        {
            return (PriceSegment) HibernateUtil.currentSession().load(PriceSegment.class, priceSegmentId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load Prodcut, see stack trace", e);
        }
    }

}
