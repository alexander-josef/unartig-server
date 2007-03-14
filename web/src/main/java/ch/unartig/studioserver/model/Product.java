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
 * Revision 1.4  2007/03/14 02:41:01  alex
 * initial checkin
 *
 * Revision 1.3  2007/03/13 16:55:03  alex
 * template for properties
 *
 * Revision 1.2  2007/03/09 23:44:24  alex
 * no message
 *
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.13  2006/11/14 17:55:50  alex
 * clean up system out
 *
 * Revision 1.12  2006/11/12 15:54:07  alex
 * fix for static price segment block
 *
 * Revision 1.11  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.10  2006/11/08 09:55:03  alex
 * dynamic priceinfo
 *
 * Revision 1.9  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.8  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.7  2006/02/27 15:35:29  alex
 * shopping cart has now an initial format with quantity = 1
 *
 * Revision 1.6  2006/02/27 11:41:20  alex
 * shopping cart has now an initial format with quantity = 1
 *
 * Revision 1.5  2005/11/23 20:52:10  alex
 * bug-fixes
 *
 * Revision 1.4  2005/11/19 22:04:04  alex
 * shopping cart reflects different price segments
 *
 * Revision 1.3  2005/11/14 18:15:07  alex
 * pricing in shopping cart enabled
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
package ch.unartig.studioserver.model;

import ch.unartig.exceptions.UnartigInvalidArgument;
import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.persistence.DAOs.ProductTypeDAO;
import ch.unartig.studioserver.persistence.DAOs.PriceDAO;

import java.util.Map;

/**
 * this business class represents a product that is sold to a customer<br>
 * a product consists of a format (or 'fun' article like mousemat) and a price<br>
 * there will be more than one product for the same format if this format is sold for different prices<br>
 * todo: add product with a boolean flag for the initial product to be shown in a shoppping cart
 */
public class Product extends GeneratedProduct
{

    public static final double _SHIPPING_HANDLING_CHE_CHF = 4.90;
    public static final double _SHIPPING_HANDLING_GER_EUR = 3.30;

    // todo array of initial producttypes from highest to lowest priority, first one that exists gets chosen
    static Long[] preselectedProductTypeIds = {(long) 4, (long) 1, (long) 5};

    public Product()
    {
    }

    /**
     * 
     * @param productTypeId
     * @param priceId
     * @throws UAPersistenceException
     */
    public Product(Long productTypeId, Long priceId, StudioAlbum album) throws UAPersistenceException
    {
        ProductTypeDAO ptDao = new ProductTypeDAO();
        setProductType(ptDao.load(productTypeId));
        PriceDAO priceDao = new PriceDAO();
        setPrice(priceDao.load(priceId));
    }



    /**
     * static method to return the product id for the product that should be shown as a default in the shopping cart for the first product
     * @return product ID
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     * @param photo
     */
    public static Long getInitialProductIdFor(Photo photo) throws UnartigInvalidArgument
    {
        for (Long preselectedProductTypeId : preselectedProductTypeIds) {
            Map availableProductTypes = photo.getAlbum().getAvailableProductTypes();
            if (availableProductTypes.containsKey(preselectedProductTypeId)) {
                return photo.getAlbum().getProductFor(preselectedProductTypeId).getProductId();
            }
        }

        return (long)-1;
    }

    /**
     * transitory function to not break the old code
     * @return true if product is digital product, false otherwise
     */
    public boolean isDigitalProduct()
    {
        return getProductType().getDigitalProduct();
    }

    public String getFormattedPriceCHF()
    {
        return Price.monetaryAmountFormat.format(getPrice().getPriceCHF());
    }
    public String getFormattedPriceEUR()
    {
        return Price.monetaryAmountFormat.format(getPrice().getPriceEUR());
    }
    public String getFormattedPriceSEK()
    {
        return Price.monetaryAmountFormat.format(getPrice().getPriceSEK());
    }
    public String getFormattedPriceGBP()
    {
        return Price.monetaryAmountFormat.format(getPrice().getPriceGBP());
    }
}
