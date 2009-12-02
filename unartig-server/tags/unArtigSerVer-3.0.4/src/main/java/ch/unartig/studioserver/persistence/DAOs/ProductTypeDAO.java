/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 28.02.2007$
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
 * Revision 1.2  2007/03/09 23:44:24  alex
 * no message
 *
 * Revision 1.1  2007/03/01 18:23:42  alex
 * initial commit maven setup no history
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import java.util.List;

import ch.unartig.studioserver.persistence.util.HibernateUtil;
import ch.unartig.studioserver.model.ProductType;
import ch.unartig.exceptions.UAPersistenceException;

public class ProductTypeDAO {


    public ProductType load(Long productTypeId) throws UAPersistenceException
    {
        try
        {
            return (ProductType) HibernateUtil.currentSession().load(ProductType.class, productTypeId);
        } catch (HibernateException e)
        {
            throw new UAPersistenceException("Could not load ProductType, see stack trace", e);
        }

    }


    public List listProductTypes() throws UAPersistenceException
    {
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(ProductType.class)
                .addOrder(Order.asc("productTypeId"));
        return c.list();

    }

}
