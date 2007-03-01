/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Nov 4, 2005$
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
 * Revision 1.10  2006/11/14 17:14:26  alex
 * shopping cart fix
 *
 * Revision 1.9  2006/11/14 13:45:20  alex
 * fixing shopping cart when product is reset to default
 *
 * Revision 1.8  2006/11/14 11:07:46  alex
 * shipping handling
 *
 * Revision 1.7  2006/11/13 22:13:30  alex
 * shoppingcart
 *
 * Revision 1.6  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.5  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.4  2006/02/27 11:41:20  alex
 * shopping cart has now an initial format with quantity = 1
 *
 * Revision 1.3  2006/02/20 16:54:49  alex
 * new album nav concept works
 *
 * Revision 1.2  2006/02/08 15:35:09  alex
 * confirmation message again
 *
 * Revision 1.1  2006/02/07 14:48:53  alex
 * bug 820 and minor refactorings
 *
 * Revision 1.24  2005/12/11 21:49:08  alex
 * unartiginfo weg aus text
 *
 * Revision 1.23  2005/11/27 19:39:10  alex
 * fast upload
 *
 * Revision 1.22  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.21  2005/11/25 10:56:58  alex
 *
 * Revision 1.20  2005/11/20 21:08:06  alex
 * delete of a photo in sc works correct
 *
 * Revision 1.19  2005/11/20 16:42:15  alex
 * sunntig obig
 *
 * Revision 1.18  2005/11/20 10:10:50  alex
 * bug 740 758
 *
 * Revision 1.17  2005/11/19 22:04:04  alex
 * shopping cart reflects different price segments
 *
 * Revision 1.16  2005/11/19 11:08:20  alex
 * index navigation works, (extended GenericLevel functions)
 * wrong calculation of fixed checkout overview eliminated
 *
 * Revision 1.15  2005/11/18 19:15:52  alex
 * stuff ...
 *
 * Revision 1.14  2005/11/18 17:28:36  alex
 * back link, incl. new interface for naviable objects
 *
 * Revision 1.13  2005/11/17 19:41:13  alex
 * new fix sc overview
 *
 * Revision 1.12  2005/11/17 13:36:06  alex
 * check out overview works
 *
 * Revision 1.11  2005/11/14 18:25:01  alex
 * pricing in shopping cart enabled, minor major units stuff works
 *
 * Revision 1.10  2005/11/14 18:15:07  alex
 * pricing in shopping cart enabled
 *
 * Revision 1.9  2005/11/14 11:52:22  alex
 * delete photo in cart works again
 *
 * Revision 1.8  2005/11/14 11:23:25  alex
 * using mapped property ordered photos in shopping cart overview ! works!
 *
 * Revision 1.7  2005/11/14 10:43:34  alex
 * shopping cart basic functions work. photo list needs a bit more work yet
 *
 * Revision 1.6  2005/11/12 23:15:27  alex
 * using indexed properties ... first step
 *
 * Revision 1.5  2005/11/09 21:59:36  alex
 * Order process classes and logic,
 * database creation script now inserts start-data, sql scripts
 * build script
 *
 * Revision 1.4  2005/11/05 16:00:41  alex
 * tiles error, more sc stuff
 *
 * Revision 1.3  2005/11/05 14:57:08  alex
 * images small correction
 *
 * Revision 1.2  2005/11/05 10:32:14  alex
 * shopping cart and minor problems, exception handling
 *
 * Revision 1.1  2005/11/04 23:02:54  alex
 * shopping cart session
 *
 ****************************************************************/
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigInvalidArgument;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.businesslogic.NavigableObject;
import ch.unartig.studioserver.model.Photo;
import ch.unartig.studioserver.model.Product;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import ch.unartig.studioserver.persistence.DAOs.ProductDAO;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import java.io.Serializable;
import java.util.*;

/**
 * session object to store
 */
public class ShoppingCart extends ActionForm implements Serializable, NavigableObject
{
    Logger _logger = Logger.getLogger(getClass().getName());
    public static final int _INDEX_PHOTO = 0;
    public static final int _INDEX_PRODUCT = 1;
    public static final int _INDEX_QUANTITY = 2;


    /*A map of photo-ids and Photos that have been put to the cart */
    private Map orderedPhotos = new HashMap();
    /*A map of products that have been put to the shopping cart*/
    private Map orderedProducts = new HashMap();
    private Map backToAlbumParams;
    /*all items in the cart as a list*/
    private List scItems = new ArrayList();
     /**/
    private int totalPhotosMinorUnitsCHF;
    private int totalPhotosMinorUnitsEUR;
    private int totalPhotosMinorUnitsGBP;
    private int totalPhotosMinorUnitsSEK;
//    private int shippingHandlingMinorUnitsCHE = Product._SHIPPING_HANDLING_MINOR_UNITS_CHE_CHF;
//    private int shippingHandlingMinorUnitsGER = Product._SHIPPING_HANDLING_MINOR_UNITS_GER_EUR;
    /*action after the form has been posted*/
    private String actionParam;
    //todo make generic:
    private String currency="undefined";
    private String customerCountry;


    /**
     * bean spec need empty constructor
     */
    public ShoppingCart()
    {
        _logger.debug("ShoppingCart.ShoppingCart: Empty Constructor");
    }

    /**
     *
     * @return true if the shopping cart contains only digital products.
     * <p/> --> this is used to select credit card as only valid payment in check out
     * @throws ch.unartig.exceptions.UAPersistenceException
     */
    public boolean isOnlyDigitalProducts()
    {
        try
        {
            for (Iterator iterator = getScItemsForConfirmation().iterator(); iterator.hasNext();)
            {
                ScOrderItem scItem = (ScOrderItem) iterator.next();
                if (!scItem.getProduct().isDigitalProduct())
                {
                    _logger.debug("found non-digital product, returning");
                    return false;
                }
            }
            _logger.debug("all products digital, returning true");
        } catch (UAPersistenceException e)
        {
            _logger.info("isOnlyDigitalProducts; Cannot query for shopping cart items, returning false",e);
            return false;
        }
        return true;
    }

    /**
     * load and add a photo to the list of ordered photos in the shopping cart
     *
     * @param orderedPhotoId
     * @throws UAPersistenceException
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public void addPhoto(Long orderedPhotoId) throws UAPersistenceException, UnartigInvalidArgument
    {
        PhotoDAO phDao = new PhotoDAO();
        Photo photo = phDao.load(orderedPhotoId);
        addPhoto(photo);
    }

    /**
     * this method is called from the removeFromCart action
     *
     * @param orderedPhotoId
     */
    public void remove(Long orderedPhotoId)
    {
        orderedPhotos.remove(orderedPhotoId);
        removePhotoFromScItems(orderedPhotoId);
    }

    /**
     * go through list of ordered items and remove all items that are for the orderedPhotoId param
     *
     * @param orderedPhotoId
     */
    private void removePhotoFromScItems(Long orderedPhotoId)
    {
        Collection toBeRemoved = new ArrayList();
        for (int i = 0; i < scItems.size(); i++)
        {
            ScOrderItem scOrderItem = (ScOrderItem) scItems.get(i);
            if (orderedPhotoId.equals(scOrderItem.getPhotoId()))
            {
                toBeRemoved.add(scOrderItem);
            }
        }
        scItems.removeAll(toBeRemoved);
    }

    public Collection getOrderedPhotos()
    {
        return orderedPhotos.values();
    }

    public Map getOrderedPhotosMap()
    {
        return orderedPhotos;
    }

    /**
     * accessor for map-backed property orderedPhoto
     *
     * @param key
     * @return a photo object
     */
    public Object getPhotoMapped(String key)
    {
        return orderedPhotos.get(new Long(key));
    }

    /**
     * accessor for map-backed property mappedProduct
     *
     * @param key
     * @return a product object
     */
    public Object getProductMapped(String key)
    {
        return orderedProducts.get(new Long(key));
    }

    /**
     * this is called for every item (photo) that is put to the shopping cart<br>
     * - add to orderedPhotos<br>
     * - add new batch (currently 4) of Shoppingcart order items to scitems list
     *
     * @param photo
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public void addPhoto(Photo photo) throws UnartigInvalidArgument
    {
        orderedPhotos.put(photo.getPhotoId(), photo);
        for (int i = 0; i < Registry._NUMBER_OF_SC_ITEMS_PER_PHOTO; i++)
        {
            // todo: consider price segments ....getInitialScItems(i,segment)
            // give all items an inital product of -1  ?
            // first item will get quantity of one!  -->  i==0?1:0
//            ScOrderItem oi = new ScOrderItem(photo.getPhotoId(), new Long(-1), i==0?1:0);
            // first itme will have an standard initial product assigned : (or -1 for the remaining scItems)
            ScOrderItem oi = new ScOrderItem(photo.getPhotoId(), i==0?Product.getInitialProductIdFor(photo.getAlbum().getPriceSegment()):new Long(-1), i==0?1:0, this);
            addScOrderItem(oi);
        }

    }

    /**
     * this is called for every Product that is added to the shopping cart
     * @param product product object as defined in the product table
     */
    public void addProduct(Product product)
    {
        orderedProducts.put(product.getProductId(), product);
    }

    /**
     * <p>called every time the shopping cart is updated
     * <p>iterate over all scitems and calculate total
     */
    public void updatePrices()
    {
        // all minor units
        int totalItemPricesMinorUnitsCHF = 0;
        int totalItemPricesMinorUnitsEUR = 0;
        int totalItemPricesMinorUnitsGBP = 0;
        int totalItemPricesMinorUnitsSEK = 0;
        for (int i = 0; i < scItems.size(); i++)
        {
            ScOrderItem scOrderItem = (ScOrderItem) scItems.get(i);
            totalItemPricesMinorUnitsCHF += scOrderItem.getItemPriceMinorUnitsCHF();
            totalItemPricesMinorUnitsEUR += scOrderItem.getItemPriceMinorUnitsEUR();
            totalItemPricesMinorUnitsGBP += scOrderItem.getItemPriceMinorUnitsGBP();
            totalItemPricesMinorUnitsSEK += scOrderItem.getItemPriceMinorUnitsSEK();
        }
        setTotalPhotosMinorUnitsCHF(totalItemPricesMinorUnitsCHF);
        setTotalPhotosMinorUnitsEUR(totalItemPricesMinorUnitsEUR);
        setTotalPhotosMinorUnitsGBP(totalItemPricesMinorUnitsGBP);
        setTotalPhotosMinorUnitsSEK(totalItemPricesMinorUnitsSEK);

    }

    private String getSubtotalPhotosMajorUnits(int totalPhotosMinorUnits)
    {
        return String.valueOf(totalPhotosMinorUnits / 100);
    }

    public String getSubtotalPhotosMajorUnitsCHF()
    {
        return getSubtotalPhotosMajorUnits(totalPhotosMinorUnitsCHF);
    }

    public String getSubtotalPhotosMajorUnitsEUR()
    {
        return getSubtotalPhotosMajorUnits(totalPhotosMinorUnitsEUR);
    }

    public String getSubtotalPhotosMajorUnitsGBP()
    {
        return getSubtotalPhotosMajorUnits(totalPhotosMinorUnitsGBP);
    }

    public String getSubtotalPhotosMajorUnitsSEK()
    {
        return getSubtotalPhotosMajorUnits(totalPhotosMinorUnitsSEK);
    }

    /**
     * called by view for confirming order in the customer's currency
     * @return
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public String getSubtotalPhotosMajorUnits() throws UnartigInvalidArgument
    {
        // Switzerland:
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getSubtotalPhotosMajorUnitsCHF();
        }
        // Germany
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getSubtotalPhotosMajorUnitsEUR();
        }
        throw new UnartigInvalidArgument("Country invalid");
    }



    private String getSubtotalPhotosMinorUnitsPart(int totalPhotosMinorUnits)
    {
        int mup = totalPhotosMinorUnits % 100;
        if (mup == 0)
        {
            return "-";
        } else
        {
            return String.valueOf(mup);
        }
    }


    public String getSubtotalPhotosMinorUnitsPartCHF()
    {
        return getSubtotalPhotosMinorUnitsPart(totalPhotosMinorUnitsCHF);
    }
    
    public String getSubtotalPhotosMinorUnitsPartEUR()
    {
        return getSubtotalPhotosMinorUnitsPart(totalPhotosMinorUnitsEUR);

    }

    public String getSubtotalPhotosMinorUnitsPartGBP()
    {
        return getSubtotalPhotosMinorUnitsPart(totalPhotosMinorUnitsGBP);
    }

    public String getSubtotalPhotosMinorUnitsPartSEK()
    {
        return getSubtotalPhotosMinorUnitsPart(totalPhotosMinorUnitsSEK);
    }


    /**
     * called by view to present correct currency for customer
     * @return
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public String getSubtotalPhotosMinorUnitsPart() throws UnartigInvalidArgument
    {
        // Switzerland:
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getSubtotalPhotosMinorUnitsPartCHF();
        }
        // Germany
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getSubtotalPhotosMinorUnitsPartEUR();
        }
        throw new UnartigInvalidArgument("Country invalid");
    }


// shipping handling: CHE for shipping to Switzerland in CHF and GER for shipping to Germany in EURO

    /**
     *
     * @param shippingHandlingMinorUnits the country dependant minor units for shipping and handling
     * @return only the major units or '-' if no shipping handling applies
     */
    private String getShippingMajorUnits(int shippingHandlingMinorUnits)
    {
        return isOnlyDigitalProducts()?"-":String.valueOf(shippingHandlingMinorUnits / 100);
    }

    /**
     * called by view to show correct currency for customer
     * @return
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public String getShippingMajorUnits() throws UnartigInvalidArgument
    {
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getShippingMajorUnitsCHE();
        }
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getShippingMajorUnitsGER();
        }
        throw new UnartigInvalidArgument("Country invalid");
    }

    public String getShippingMajorUnitsCHE()
    {
        return getShippingMajorUnits(getShippingHandlingMinorUnitsCHE());
    }
    public String getShippingMajorUnitsGER()
    {
        return getShippingMajorUnits(getShippingHandlingMinorUnitsGER());
    }

    /**
     * return the major units for shipping handling
     * @param shippingHandlingMinorUnits the country dependant shipping handling minor units
     * @return the major units or '-' if no shipping handling applies, digital products
     */
    private String getShippingMinorUnitsPart(int shippingHandlingMinorUnits)
    {
        int mup = shippingHandlingMinorUnits % 100;
        if (mup == 0 || isOnlyDigitalProducts())
        {
            return "-";
        } else
        {
            return String.valueOf(mup);
        }
    }


    public String getShippingMinorUnitsPart() throws UnartigInvalidArgument
    {
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getShippingMinorUnitsPartCHE();
        }
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getShippingMinorUnitsPartGER();
        }
        throw new UnartigInvalidArgument("Country invalid");
    }

    public String getShippingMinorUnitsPartCHE()
    {
        return getShippingMinorUnitsPart(getShippingHandlingMinorUnitsCHE());
    }
    
    public String getShippingMinorUnitsPartGER()
    {
        return getShippingMinorUnitsPart(getShippingHandlingMinorUnitsGER());
    }





    private String getTotalMajorUnits(int total)
    {
//        int total = totalPhotosMinorUnits+shippingHandlingMinorUnits;
        if (total!=0)
        {
            return String.valueOf(total / 100);
        }
        else
        {
            return "0";
        }

    }

    /**
     * called by confirmation view to present amount in customer's currency
     * @return
     * @throws UnartigInvalidArgument
     */
    public String getTotalMajorUnits() throws UnartigInvalidArgument
    {
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getTotalMajorUnitsCHE();
        }
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getTotalMajorUnitsGER();
        }
        throw new UnartigInvalidArgument("Country invaled");
    }

    /**
     * total for orders from Switzerland (shipping handling part is ignored for digital-only orders)
     * @return
     */
    public String getTotalMajorUnitsCHE()
    {
        int total = totalPhotosMinorUnitsCHF+getShippingHandlingMinorUnitsCHE();
        return getTotalMajorUnits(total);
    }

    /**
     * total for orders from germany (shipping handling part is ignored for digital-only orders)
     * @return
     */
    public String getTotalMajorUnitsGER()
    {
        int total = totalPhotosMinorUnitsEUR+getShippingHandlingMinorUnitsGER();
        return getTotalMajorUnits(total);
    }



    private String getTotalMinorUnitsPart(int total)
    {
//        int total = totalPhotosMinorUnits+shippingHandlingMinorUnits;
        int mup = total % 100;
        if (mup == 0)
        {
            return "-";
        } else
        {
            return String.valueOf(mup);
        }
    }

    /**
     * called from confirmation view to show correct amount in customer's currency
     * @return
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public String getTotalMinorUnitsPart() throws UnartigInvalidArgument
    {
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            return getTotalMinorUnitsPartCHE();
        }
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            return getTotalMinorUnitsPartGER();
        }
        throw new UnartigInvalidArgument("Country invaled");
    }

    /**
     * shippings to Switzerland
     * @return string
     */
    public String getTotalMinorUnitsPartCHE()
    {
        // todo: code duplication ...
        int total = totalPhotosMinorUnitsCHF+getShippingHandlingMinorUnitsCHE();
        return getTotalMinorUnitsPart(total);
    }

    /**
     * Orders from Germany
     * @return
     */
    public String getTotalMinorUnitsPartGER()
    {
        int total = totalPhotosMinorUnitsEUR+getShippingHandlingMinorUnitsGER();
        return getTotalMinorUnitsPart(total);
    }


    private void addScOrderItem(ScOrderItem scOrderItem)
    {
        if (scItems == null)
        {
            scItems = new ArrayList();
        }
        scItems.add(scOrderItem);
        _logger.debug("added new item to shopping cart order list : ");
        _logger.debug(scOrderItem);
    }

    public void setBackToAlbumParams(Map backToAlbumParams)
    {
        this.backToAlbumParams = backToAlbumParams;
    }

    public Map getBackToAlbumParams()
    {
        return backToAlbumParams;
    }

    /**
     * return all scItems, 4 for every photo
     * @return list of all scitmes
     */
    public List getScItems()
    {
        return scItems;
    }

    /**
     * return consolidated itmes for order confirmation
     * @return the sc items without empty scOrderItem entries, consolidated
     * @throws ch.unartig.exceptions.UAPersistenceException
     */
    public List getScItemsForConfirmation() throws UAPersistenceException
    {// todo: rename: consolidatedScItems
        List consolidatedItems = new ArrayList();
        for (int i = 0; i < scItems.size(); i++)
        {
            ScOrderItem scOrderItem = (ScOrderItem) scItems.get(i);

            if (scOrderItem.getQuantity()!=0) // for every scOrder item that has amount > 0:
            {
                boolean consolidatedItemExists =false; // no existing consolidated item

                for (int j = 0; j < consolidatedItems.size(); j++) // loop through existing confirmItems:
                {
                    ScOrderItem eachConfItem = (ScOrderItem) consolidatedItems.get(j);
                    if (scOrderItem.getPhotoId().equals(eachConfItem.getPhotoId()) && scOrderItem.getProductId().equals(eachConfItem.getProductId()))
                    { // we have an existing item, add quantities, update price
                        eachConfItem.setQuantity(eachConfItem.getQuantity() + scOrderItem.getQuantity());
                        eachConfItem.updateItemPrice();
                        consolidatedItemExists =true;
                        break;
                    }
                }
                if (!consolidatedItemExists)
                {
                    ScOrderItem newConsolidatedItem = new ScOrderItem(scOrderItem.getPhotoId(),scOrderItem.getProductId(),scOrderItem.getQuantity(), this);
                    newConsolidatedItem.updateItemPrice();
                    consolidatedItems.add(newConsolidatedItem);
                }
            }
        }

        //debug
        if (_logger.isDebugEnabled())
        {
            for (int i = 0; i < consolidatedItems.size(); i++)
            {
                ScOrderItem scOrderItem = (ScOrderItem) consolidatedItems.get(i);
                _logger.debug("scOrderItem.getPhotoId() = " + scOrderItem.getPhotoId());
                _logger.debug("scOrderItem.getProductId() = " + scOrderItem.getProductId());
                _logger.debug("scOrderItem.getQuantity() = " + scOrderItem.getQuantity());
            }
        }
        return consolidatedItems;
    }

    public void setScItems(List scItems)
    {
        this.scItems = scItems;
    }

    /**
     * 'normal' scitem getter
     * @param index
     * @return scItem
     */
    public ScOrderItem getScItem(int index)
    {
        return (ScOrderItem) scItems.get(index);
    }


//    public ScOrderItem getScItemForConfirmation(int index)
//    {
//        System.out.println("ShoppingCart.getScItemForConfirmation ********************************");
//        return (ScOrderItem) scItems.get(index);
//    }


    public int getTotalPhotosMinorUnitsCHF()
    {
        return totalPhotosMinorUnitsCHF;
    }

    public void setTotalPhotosMinorUnitsCHF(int totalPhotosMinorUnitsCHF)
    {
        this.totalPhotosMinorUnitsCHF = totalPhotosMinorUnitsCHF;
    }

    public int getTotalPhotosMinorUnitsEUR()
    {
        return totalPhotosMinorUnitsEUR;
    }

    public void setTotalPhotosMinorUnitsEUR(int totalPhotosMinorUnitsEUR)
    {
        this.totalPhotosMinorUnitsEUR = totalPhotosMinorUnitsEUR;
    }

    public int getTotalPhotosMinorUnitsGBP()
    {
        return totalPhotosMinorUnitsGBP;
    }

    public void setTotalPhotosMinorUnitsGBP(int totalPhotosMinorUnitsGBP)
    {
        this.totalPhotosMinorUnitsGBP = totalPhotosMinorUnitsGBP;
    }

    public int getTotalPhotosMinorUnitsSEK()
    {
        return totalPhotosMinorUnitsSEK;
    }

    public void setTotalPhotosMinorUnitsSEK(int totalPhotosMinorUnitsSEK)
    {
        this.totalPhotosMinorUnitsSEK = totalPhotosMinorUnitsSEK;
    }


    /**
     * does the necessary BL to update the shopping cart
     *
     * @throws ch.unartig.exceptions.UAPersistenceException
     * @throws ch.unartig.exceptions.UnartigInvalidArgument
     */
    public void updateCart() throws UAPersistenceException, UnartigInvalidArgument
    {
        ProductDAO pDao = new ProductDAO();
        boolean updated=false;
        for (int i = 0; i < getScItems().size(); i++)
        {
            ScOrderItem scOrderItem = (ScOrderItem) getScItems().get(i);
            if (scOrderItem.getProductId().intValue() > 0)
            {
                scOrderItem.updateItemPrice();
                // todo update only at the end ??
//                updatePrices();
                addProduct(pDao.load(scOrderItem.getProductId()));
                updated = true;
            } else
            { // no product chosen; make sure the item price is 0
                scOrderItem.setQuantity(0);
                scOrderItem.initPrice();
//                scOrderItem.setProductId(new Long(-1));
                // todo update only at the end ??
//                updatePrices();
            }
        }
        updatePrices();
        if (!updated)
        {
            throw new UnartigInvalidArgument("no vaild product to update");
        }
    }

    public String getActionParam()
    {
        return actionParam;
    }

    public void setActionParam(String actionParam)
    {
        this.actionParam = actionParam;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    /**
     * set shopping cart field for the customers country AND set the appropriate currency
     * whenever the country changes, this method should be called
     * @param customerCountry
     */
    public void setCustomerCountry(String customerCountry)
    {
        if (Registry._SWITZERLAND_COUNTRY_CODE.equals(customerCountry))
        {
            // set to chf
            currency=Registry._CURRENCY_SWISS_FRANCS;
        }
        else if (Registry._GERMANY_COUNTRY_CODE.equals(customerCountry))
        {
            currency=Registry._CURRENCY_EURO;
        }
        this.customerCountry = customerCountry;
    }


    public String getCustomerCountry()
    {
        return customerCountry;
    }

    /**
     * return the shipping handling costs minor units for CHE or 0 if only digital products are chosen
     * @return minor units
     */
    public int getShippingHandlingMinorUnitsCHE()
    {
        return isOnlyDigitalProducts()?0:Product._SHIPPING_HANDLING_MINOR_UNITS_CHE_CHF;
    }

    /**
     * return the shipping handling costs minor units for Germany or 0 if only digital products are chosen
     * @return minor units
     */
    public int getShippingHandlingMinorUnitsGER()
    {
        return isOnlyDigitalProducts()?0:Product._SHIPPING_HANDLING_MINOR_UNITS_GER_EUR;
    }
}
