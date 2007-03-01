/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 9, 2005$
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
 * Revision 1.8  2006/11/24 10:21:59  alex
 * download page fixes
 *
 * Revision 1.7  2006/11/08 09:55:03  alex
 * dynamic priceinfo
 *
 * Revision 1.6  2006/11/01 10:14:45  alex
 * cc interface check in, transactions work
 *
 * Revision 1.5  2005/11/25 10:56:58  alex
 * todo liste, admin tool, sonstiges
 *
 * Revision 1.4  2005/11/20 16:42:16  alex
 * sunntig obig
 *
 * Revision 1.3  2005/11/19 22:04:04  alex
 * shopping cart reflects different price segments
 *
 * Revision 1.2  2005/11/12 23:15:27  alex
 * using indexed properties ... first step
 *
 * Revision 1.1  2005/11/09 21:59:36  alex
 * Order process classes and logic,
 * database creation script now inserts start-data, sql scripts
 * build script
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.PriceSegment;
import ch.unartig.studioserver.model.Product;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import java.util.List;

public class ProductDAO
{
    /**
     * @param productId
     * @return
     * @throws UAPersistenceException
     */
    public Product load(Long productId) throws UAPersistenceException
    {
        try
        {
            return (Product) HibernateUtil.currentSession().load(Product.class, productId);
//            return (GenericLevel) HibernateUtil.currentSession().load(levelClass, genericLevelId);
        } catch (HibernateException e)
        {
//            logger.error("Could not load Event, see stack trace", e);
            throw new UAPersistenceException("Could not load Product, see stack trace", e);
        }
    }

    /**
     * list ALL products
     * @return
     * @throws UAPersistenceException
     */
    public List listProducts() throws UAPersistenceException
    {
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(Product.class)
                .addOrder(Order.asc("productId"));
        return c.list();
    }

    /**
     * list ALL products from the passed price segment
     * @return
     * @throws UAPersistenceException
     * @param priceSegment filtered segment
     */
    public List listProducts(PriceSegment priceSegment) throws UAPersistenceException
    {
        // todo exclude products
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(Product.class)
                .add(Expression.eq("priceSegment",priceSegment))
                .addOrder(Order.asc("productId"));
        return c.list();
    }
}
