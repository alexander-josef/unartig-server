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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * this business class represents a product that is sold to a customer<br>
 * a product consists of a format (or 'fun' article like mousemat) and a price<br>
 * there will be more than one product for the same format if this format is sold for different prices<br>
 * todo: add product with a boolean flag for the initial product to be shown in a shoppping cart
 * todo: product should be the combination of a photo and a format.
 */
public class Product extends GeneratedProduct
{

    private NumberFormat numberFormat = DecimalFormat.getInstance();
    // the IDs of digital products. todo find better solution ....
    private static Long[] digitalProducts = new Long[]{(long) 17, (long) 18, (long) 19, (long) 20};
    public static final int _SHIPPING_HANDLING_MINOR_UNITS_CHE_CHF = 490;
    public static final int _SHIPPING_HANDLING_MINOR_UNITS_GER_EUR = 330;

    // todo: not yet an optimal solution:

    private static Product[] initialProducts;
    public static final int _INITIAL_PRODUCT_ID_SEG1 = 3;
    public static final int _INITIAL_PRODUCT_ID_SEG2 = 4;

    static
    {

    }


    public Product()
    {
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
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
     * @param priceSegment price segment for the product
     * @return product ID
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public static Long getInitialProductIdFor(PriceSegment priceSegment) throws UnartigInvalidArgument
    {
        if (priceSegment.equals(PriceSegment.get_PS3()))
        { // '10 x 15 cm for CHF 5' - Segment
            return (long) _INITIAL_PRODUCT_ID_SEG1;
        }
        else if (priceSegment.equals(PriceSegment.get_PS5()))
        { // '10 x 15 cm for CHF 10' - Segment
            return (long) _INITIAL_PRODUCT_ID_SEG2;
        }
        else
        {
            throw new UnartigInvalidArgument("can not find an initial product for the given price segment :" + priceSegment);
        }

    }

    public boolean isDigitalProduct()
    {
//        System.out.println("this.getProductId() = " + this.getProductId());
//        System.out.println("digitalProducts = " + digitalProducts);
        return Arrays.asList(digitalProducts).contains(this.getProductId());
    }

    public String getFormattedPriceCHF()
    {
        return numberFormat.format(getOipsPriceCHF().floatValue() / 100f);
    }
    public String getFormattedPriceEUR()
    {
        return numberFormat.format(getOipsPriceEUR().floatValue() / 100f);
    }
    public String getFormattedPriceSEK()
    {
        return numberFormat.format(getOipsPriceSEK().floatValue() / 100f);
    }
    public String getFormattedPriceGBP()
    {
        return numberFormat.format(getOipsPriceGBP().floatValue() / 100f);
    }
}
