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
 * Revision 1.2  2007/03/14 02:41:02  alex
 * initial checkin
 *
 * Revision 1.1  2007/03/13 23:24:06  alex
 * initial checkin
 *
 ****************************************************************/
package ch.unartig.studioserver.colorplaza;

import ch.unartig.studioserver.businesslogic.ProductMapperIF;
import ch.unartig.studioserver.model.Product;

/**
 * Mapper between unartig album products and the colorplaza oips products
 */
public class OipsPidMapper implements ProductMapperIF {

    private static OipsPidMapper instance;
    /**
     * 2-dimensional arrays for producttype-price-oipspid mapping<br/>
     * [ProductTypeId][PriceId]
     */
    String[][] productPricesOipsid;



    private OipsPidMapper() {
    }

    public static OipsPidMapper getInstance() {
        if (instance==null)
        {
            instance = new OipsPidMapper();
            instance.init();
        }
        return instance;
    }

    /**
     * called only once upon creation. set all static data
     */
    private void init() {
        // 10 x 15 print
        productPricesOipsid[1][1]= "6301";
        productPricesOipsid[1][10]= "6501";
        // digifoto 400 x 600 (all digital oipspid)
        productPricesOipsid[2][3]= "998";
        productPricesOipsid[2][19]= "998";
        productPricesOipsid[2][20]= "998";
        // digital negative
        productPricesOipsid[3][2]= "998";
        productPricesOipsid[3][5]= "998";
        // 11 x 17 print
        productPricesOipsid[4][2]= "6304";
        productPricesOipsid[4][11]= "6504";
        // 13 x 19 pring
        productPricesOipsid[5][3]= "6302";
        productPricesOipsid[5][12]= "6502";
    }

    /**
     * @see ch.unartig.studioserver.businesslogic.ProductMapperIF
     */
    public String map(Product product) {

        return productPricesOipsid[product.getProductType().getProductTypeId().intValue()][product.getPrice().getPriceId().intValue()];
    }

}
