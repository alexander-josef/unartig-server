/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 12, 2005$
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
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.9  2006/11/17 13:21:41  alex
 * email notifiaction fix
 *
 * Revision 1.8  2006/11/14 13:45:20  alex
 * fixing shopping cart when product is reset to default
 *
 * Revision 1.7  2006/11/14 11:07:46  alex
 * shipping handling
 *
 * Revision 1.6  2006/11/13 22:13:30  alex
 * shoppingcart
 *
 * Revision 1.5  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.4  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.3  2006/02/27 11:41:20  alex
 * shopping cart has now an initial format with quantity = 1
 *
 * Revision 1.2  2006/02/08 15:35:09  alex
 * confirmation message again
 *
 * Revision 1.1  2006/02/07 14:48:53  alex
 * bug 820 and minor refactorings
 *
 * Revision 1.5  2005/11/17 19:41:13  alex
 * new fix sc overview
 *
 * Revision 1.4  2005/11/17 13:36:06  alex
 * check out overview works
 *
 * Revision 1.3  2005/11/14 18:25:01  alex
 * pricing in shopping cart enabled, minor major units stuff works
 *
 * Revision 1.2  2005/11/14 18:15:07  alex
 * pricing in shopping cart enabled
 *
 * Revision 1.1  2005/11/12 23:14:11  alex
 * add ScOrderItem
 *
 ****************************************************************/
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigInvalidArgument;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.Product;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import ch.unartig.studioserver.persistence.DAOs.ProductDAO;
import org.apache.log4j.Logger;

/**
 * bean class to represent a shopping cart order item
 * will be used in sc form to populate the shopping cart<br>
 * an item consits of :
 * - photoId (PK from db)
 * - produdctID (PK from db)
 * - quantity: how many of that product and photo
 * - item price: quantity x productprice
 */
public class ScOrderItem
{
    // todo: the minor units need to  be modula 100 for the jsp???
    // todo: shipping handling for Germany
    // todo: calculations Euros .....

    private Long photoId;
    /* is -1 if no product has been seletec*/
    private Long productId;
    private int quantity;
    /*back reference to shopping cart*/
    private ShoppingCart shoppingCart;
    /*item price, example: 0.60*/
    private int itemPriceMinorUnitsCHF;
    private int itemPriceMinorUnitsEUR;
    private int itemPriceMinorUnitsGBP;
    private int itemPriceMinorUnitsSEK;

    Logger _logger = Logger.getLogger(getClass().getName());

    public ScOrderItem()
    {
    }

    /**
     * constructor
     * @param photoId
     * @param productId id from view page. can be <1 if no product has been selected
     * @param amount
     * @param shoppingCart
     */
    public ScOrderItem(Long photoId, Long productId, int amount, ShoppingCart shoppingCart)
    {
        this.photoId = photoId;
        this.productId = productId;
        this.quantity = amount;

        this.shoppingCart = shoppingCart;
    }

    /**
     * itemPriceMinorUnits: quantity x productprice
     * this needs to be called in order to calculate the correct prices
     * @throws ch.unartig.exceptions.UAPersistenceException
     */
    public void updateItemPrice() throws UAPersistenceException
    {
        ProductDAO pDao = new ProductDAO();
        Product product = pDao.load(productId);

        setItemPriceMinorUnitsCHF(getQuantity()*product.getOipsPriceCHF().intValue());
        setItemPriceMinorUnitsEUR(getQuantity()*product.getOipsPriceEUR().intValue());
        setItemPriceMinorUnitsGBP(getQuantity()*product.getOipsPriceGBP().intValue());
        setItemPriceMinorUnitsSEK(getQuantity()*product.getOipsPriceSEK().intValue());
    }

    public String getPriceMajorUnits() throws UnartigInvalidArgument
    {
        return getPriceMajorUnits(getMinorUnitsCustomerCurrency());
    }

    private String getPriceMajorUnits(int minorUnits)
    {
        return String.valueOf( minorUnits /100);
    }


    /**
     * return the major units in CHF
     * @return
     */
    public String getPriceMajorUnitsCHF()
    {
        return getPriceMajorUnits(itemPriceMinorUnitsCHF);
    }

    /**
     * return the major units in EUR
     * @return 
     */
    public String getPriceMajorUnitsEUR()
    {
        return getPriceMajorUnits(itemPriceMinorUnitsEUR);
    }


    /**
     *
     * @return
     */
    public String getPriceMinorUnitsPartCHF()
    {
        return getPriceMinorUnitsPart(itemPriceMinorUnitsCHF);
    }

    /**
     *
     * @return 
     */
    public String getPriceMinorUnitsPartEUR()
    {
        return getPriceMinorUnitsPart(itemPriceMinorUnitsEUR);
    }

    /**
     * this is called from the view after the the address has been entered. it shows the relevent amount in the correct currency
     * @return
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public String getPriceMinorUnitsPart() throws UnartigInvalidArgument
    {
        return getPriceMinorUnitsPart(getMinorUnitsCustomerCurrency());
    }

    /**
     * check the shopping cart to return the correct currency for the customer
     * (customer already filled out form)
     * @return amount in correct currency
     * @throws ch.unartig.exceptions.UnartigInvalidArgument if custumer country not available
     */
    private int getMinorUnitsCustomerCurrency() throws UnartigInvalidArgument
    {
        // check that the country is available
        if (shoppingCart==null || shoppingCart.getCustomerCountry()==null)
        {
            throw new UnartigInvalidArgument("Customer Country not available") ;
        }

        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(shoppingCart.getCustomerCountry()))
        {
            return itemPriceMinorUnitsCHF;
        } else if (Registry._GERMANY_COUNTRY_CODE.equals(shoppingCart.getCustomerCountry()))
        {
            return itemPriceMinorUnitsEUR;
        }
        throw new UnartigInvalidArgument("Unknown Country") ;
    }


    /**
     * calculate the minor units
     * @param itemPriceMinorUnits the price in minor units for this item
     * @return
     */
    private String getPriceMinorUnitsPart(int itemPriceMinorUnits)
    {
        int mup = itemPriceMinorUnits % 100;
        if (mup ==0)
        {
            return "-";
        }
        else
        {
            return String.valueOf(mup);
        }
    }


    public Long getPhotoId()
    {
        return photoId;
    }

    public void setPhotoId(Long photoId)
    {
        this.photoId = photoId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    /**
     *
     * @return the order item quantity or 1 if the product is digital
     */
    public int getQuantity()
    {
        // todo set to 1 if product is digital
        return isDigitalOrderItem()?1:quantity;
//        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getItemPriceMinorUnitsCHF()
    {
        return itemPriceMinorUnitsCHF;
    }

    public void setItemPriceMinorUnitsCHF(int itemPriceMinorUnitsCHF)
    {
        this.itemPriceMinorUnitsCHF = itemPriceMinorUnitsCHF;
    }


    public int getItemPriceMinorUnitsEUR()
    {
        return itemPriceMinorUnitsEUR;
    }

    public void setItemPriceMinorUnitsEUR(int itemPriceMinorUnitsEUR)
    {
        this.itemPriceMinorUnitsEUR = itemPriceMinorUnitsEUR;
    }

    public int getItemPriceMinorUnitsGBP()
    {
        return itemPriceMinorUnitsGBP;
    }

    public void setItemPriceMinorUnitsGBP(int itemPriceMinorUnitsGBP)
    {
        this.itemPriceMinorUnitsGBP = itemPriceMinorUnitsGBP;
    }

    public int getItemPriceMinorUnitsSEK()
    {
        return itemPriceMinorUnitsSEK;
    }

    public void setItemPriceMinorUnitsSEK(int itemPriceMinorUnitsSEK)
    {
        this.itemPriceMinorUnitsSEK = itemPriceMinorUnitsSEK;
    }

    /**
     * convenience getter to get a photo object from the photoId
     * @return either the Photo object or null if nothing was found
     */
    public Photo getPhoto()
    {
        PhotoDAO phDao = new PhotoDAO();
        try
        {
            return phDao.load(photoId);  //To change body of created methods use File | Settings | File Templates.
        } catch (UAPersistenceException e)
        {
            _logger.error("Cannot load Photo from given photoId : " + photoId + ";returning null");
            return null;
        }
    }

    /**
     * convenience getter to get a Product object from the productId
     * <p> order item can have productID of -1 ...
     * @return either the Photo object or null if no product exists for this item
     */
    public Product getProduct()
    {
        Product retVal = null;
        ProductDAO pDao = new ProductDAO();
        if (productId.intValue()>0)
        {
            try
        {
            return pDao.load(productId);
            } catch (UAPersistenceException e)
            {
                _logger.error("Cannot load Product from given productId : " + productId + ";returning null");
            }
        }
        return retVal;
    }

    /**
     * convenience getter; returns true if the shopping cart item is a digital product
     * @return
     */
    public boolean isDigitalOrderItem()
    {
        return getProduct() != null && getProduct().isDigitalProduct();
    }


    public String toString()
    {
        return "ScOrderItem{" + "photoId=" + photoId + ", productId=" + productId + ", quantity=" + quantity + ", itemPriceMinorUnitsCHF=" + itemPriceMinorUnitsCHF + '}';
    }

    /**
     *
     * @return true if the product of this item is a digital product
     */
    public boolean isDigital()
    {
        return getProduct().isDigitalProduct();
    }

    /**
     * set prices for all currencies to 0
     */
    public void initPrice()
    {
        setItemPriceMinorUnitsCHF(0);
        setItemPriceMinorUnitsEUR(0);
        setItemPriceMinorUnitsGBP(0);
        setItemPriceMinorUnitsSEK(0);

    }
}
