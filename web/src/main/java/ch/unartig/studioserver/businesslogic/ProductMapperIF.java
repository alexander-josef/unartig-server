/*-*
*
* FILENAME  :
*    $RCSfile$
*
*    @author alex$
*    @since 13.03.2007$
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
* Revision 1.1  2007/03/13 23:22:44  alex
* initial checkin
*
****************************************************************/
package ch.unartig.studioserver.businesslogic;

import ch.unartig.studioserver.model.Product;


/**
 *
 */
public interface ProductMapperIF {

    /**
     * Given a Product, map the product to the customID
     * @param product
     * @return the string that identifies the mapped product
     */
    public String map(Product product);
}
