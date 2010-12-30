/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 14, 2005$
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
 * Revision 1.17  2006/12/27 13:33:45  alex
 * better email
 *
 * Revision 1.16  2006/11/17 13:21:41  alex
 * email notifiaction fix
 *
 * Revision 1.15  2006/11/14 16:19:24  alex
 * digital order
 *
 * Revision 1.14  2006/11/05 22:10:02  alex
 * credit card order works
 *
 * Revision 1.13  2006/11/05 16:41:43  alex
 * action messages work for order confirmation
 *
 * Revision 1.12  2006/11/03 13:15:19  alex
 * some changes
 *
 * Revision 1.11  2006/11/01 10:14:45  alex
 * cc interface check in, transactions work
 *
 * Revision 1.10  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.9  2006/10/17 08:07:07  alex
 * creating the order hashes
 *
 * Revision 1.8  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.7  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 * Revision 1.6  2006/04/30 16:21:27  alex
 * removing system.outs
 *
 * Revision 1.5  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.4  2006/03/03 16:54:56  alex
 * minor fixes
 *
 * Revision 1.3  2006/02/28 14:57:46  alex
 * added more resources (email for order confirmation), small fixes
 *
 * Revision 1.2  2006/02/08 15:35:09  alex
 * confirmation message again
 *
 * Revision 1.1  2006/02/07 14:48:53  alex
 * bug 820 and minor refactorings
 *
 ****************************************************************/
package ch.unartig.studioserver.businesslogic;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.beans.CheckOutForm;
import ch.unartig.studioserver.beans.ScOrderItem;
import ch.unartig.studioserver.beans.ShoppingCart;
import ch.unartig.studioserver.model.*;
import ch.unartig.studioserver.persistence.DAOs.OrderDAO;
import ch.unartig.studioserver.persistence.DAOs.OrderHashDAO;
import ch.unartig.studioserver.persistence.DAOs.PhotoDAO;
import ch.unartig.studioserver.persistence.DAOs.ProductDAO;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import ch.unartig.util.CryptoUtil;
import ch.unartig.util.HttpUtil;
import ch.unartig.util.MailUtil;
import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Business-Logic class to store all shopping cart related logic. check-out, storing orders and users etcetc.
 * Every Check-out process has its own instance of this class
 */
public class ShoppingCartLogic
{
    Logger _logger = Logger.getLogger(getClass().getName());

    private Customer customer;
    private String downloadLink;
    private ShoppingCart shoppingCart;
    private CheckOutForm checkOutForm;
    private HttpServletRequest request;
    private PhotoOrderIF photoOrder;
    private Locale locale;


    public ShoppingCartLogic(HttpServletRequest request)
    {
        this.request = request;
    }

    /**
     * go through the shopping cart and create a list of orderItems
     * load photo, load product, get quantity
     * saves the orderitems
     * make sure no quantity = 0 scItems are included!
     *
     * @param consolidatedItems only order items that are relevant for order process, i.e. no zero amount order items etc.
     * @param order             instance of order object
     * @return a Set of OrderItems
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          if order cannot be persisted
     */
    private Set getOrderItems(List consolidatedItems, Order order) throws UAPersistenceException
    {
        PhotoDAO phDao = new PhotoDAO();
        ProductDAO productDao = new ProductDAO();
        Photo photo;
        Product product;
        int quantity;
        Set orderItems = new HashSet();
        for (int i = 0; i < consolidatedItems.size(); i++)
        {
            ScOrderItem scItem = (ScOrderItem) consolidatedItems.get(i);
            photo = phDao.load(scItem.getPhotoId());
            product = productDao.load(scItem.getProductId());
            quantity = scItem.getQuantity();
            OrderItem oi = new OrderItem(photo, product, quantity, order);
            orderItems.add(oi);
        }

        return orderItems;
    }

    /**
     * <p>Store the order, log the ip-address, send confirmation email to customer</p>
     * <p>Orders are collected in the unartig database. A service that runs every night collects the open orders and sends them to the lab.</p>
     * <p>NEW: for digital-only orders, no image files have to be sent with the nightly service, but the order will take place right away using the credit card interface</p>
     * <p>todo: use a separate thread for the cc booking? no!</p>
     * <p></p>
     *
     * @param ipAddress retrieved from the request;
     * @return the customer associated with the stored order
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          if a problem with the database occurs
     */
    public Customer storeAndExecuteOrder(String ipAddress) throws UnartigException
    {
        OrderDAO orderDao = new OrderDAO();
        // store the checked out order
        customer = new Customer(checkOutForm);
        Set orderItems;
        Order order = new Order(new Date());
        // transaction for writing order to the database:
        try
        {
            // Think about exception handlind: what happens if credit card payment does not work?
            // what if we can not create an order hash? etc.etc
            // transaction also includes credit card processing if payment method id creditcard
            HibernateUtil.beginTransaction();
            orderItems = getOrderItems(shoppingCart.getScItemsForConfirmation(), order);
            order.setOrderItems(orderItems);
            order.setCustomer(customer);
            orderDao.save(order);
            prepareDownloadLink(order);
            _logger.info("Order with orderid [" + order.getOrderId().toString() + "] commited from IP-Address [" + ipAddress + "]");
            _logger.info("successfully stored new order; oipsorderid = " + order.getOipsOrderId());
            // reload order
//            order = orderDao.reLoad(order.getOrderId());
            if (checkOutForm.isPaymentMethodCreditCard())
            {
                // send order now! use credit card payment
                _logger.debug("payment method: credit card");
                photoOrder = new CoplaPhotoOrder(order, Registry.isDemoOrderMode(), Registry.isSimulateOrderOnly());
                String cardHolderName = getCardHolderName();

                CreditCardDetails ccDetail = new CreditCardDetails(checkOutForm.getCreditCardTypeCode(), checkOutForm.getCreditCardNumber(), null, cardHolderName, new Integer(checkOutForm.getCreditCardExpiryYear()), new Integer(checkOutForm.getCreditCardExpiryMonth()));
                photoOrder.setCreditCardDetails(ccDetail);
                photoOrder.processOrder();
            }
            HibernateUtil.commitTransaction();
            if (photoOrder!=null)
            {
                System.out.println("photoOrder.getOrder() = " + photoOrder.getOrder());
            }
            System.out.println("order = " + order);
            // if everything goes well, send confirmation
            sendCustomerNotification();
        } catch (UnartigException e)
        {
            _logger.error("Caught exception for execute-order, rolling back");
            try
            {
                HibernateUtil.rollbackTransaction();
            } catch (UAPersistenceException e1)
            {
                _logger.error("error occured during rollback", e1);
                _logger.error(" .... continuing .....");
            }
            _logger.error("Order could not be taken, database error : ");
            // todo check unartig exception ... message???
            throw e;
        }
        catch (Exception e2)
        {
            _logger.error("unexpected Exception .... Rolling back - check stacktrace", e2);
            HibernateUtil.rollbackTransaction();
            throw new UnartigException("Unexpected exception : " + e2.getMessage());
        }
        finally
        {
            _logger.info("trying to finish execute-order transaction");
            HibernateUtil.finishTransaction();
        }
        return customer;
    }

    private String getCardHolderName()
    {
        String cardHolderName = checkOutForm.getCreditCardHolderName();
        if (cardHolderName == null || "".equals(cardHolderName))
        {
            cardHolderName = checkOutForm.getFirstName()+' '+checkOutForm.getLastName();
        }
        return cardHolderName;
    }

    /**
     * <ul>
     * <li>prepare the digital photos for download
     * <li>create the link to the photos for download
     * </ul>
     *
     * @param order the persistent order object instance
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          if orderHash can not be persisted
     */
    private void prepareDownloadLink(Order order) throws UnartigException
    {
        Date expiryDate;
        Calendar expiryDateCal = Calendar.getInstance();
        expiryDateCal.setTime(new Date());
        expiryDateCal.add(Calendar.DAY_OF_YEAR, Registry._ORDER_DOWNLOADS_VALID_DAYS);
        expiryDate = expiryDateCal.getTime();
        _logger.debug("setting expiry date to :" + expiryDate);
        String orderHash = createOrderHash(order, expiryDate);
        // todo probably not what we want ...
        downloadLink = HttpUtil.getDownloadUrl(orderHash, request);

        _logger.info("created download link  : " + downloadLink);
    }

    /**
     * @param order      the persistend order object
     * @param expiryDate a date
     * @return the one time hash
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          if orderHash can not be saved
     */
    private String createOrderHash(Order order, Date expiryDate) throws UnartigException
    {
        String orderHashString;
        orderHashString = CryptoUtil.createHash();
        _logger.debug("Order Hash : " + orderHashString);
        OrderHash orderHash = new OrderHash(order, expiryDate, orderHashString);

        OrderHashDAO ohDao = new OrderHashDAO();
        ohDao.save(orderHash);

        return orderHashString;
    }

    /**
     * send email message to customer after order has been confirmed
     * todo: this service can time out ....
     *
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          database exception
     */
    private void sendCustomerNotification() throws UAPersistenceException
    {
        MessageResources contentResource = MessageResources.getMessageResources("ch.unartig.studioserver.resources.contentText");

        StringBuffer localizedBody = MailUtil.generateBody(photoOrder,downloadLink, shoppingCart, contentResource, locale);
        String localizedSubject = contentResource.getMessage(locale,"email.orderAccepted.subject");
        try
        {
            _logger.debug("trying to send email message:\n");
            _logger.debug("localizedSubject \n\n" + localizedSubject);
            _logger.debug("localizedBody = \n\n" + localizedBody);
            MailUtil.sendMail(localizedSubject, localizedBody.toString(), customer.getEmail(), Registry.getOrderConfirmationFromAddress());
            _logger.debug("Confirmation email-message sent to customer");
        } catch (MessagingException e)
        {
            _logger.error("Error sending confirmation message", e);
            e.printStackTrace();
        }

    }

    public void setShoppingCart(ShoppingCart shoppingCart)
    {
        this.shoppingCart = shoppingCart;
    }

    public void setCheckOutForm(CheckOutForm checkOutForm)
    {
        this.checkOutForm = checkOutForm;
    }


    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }


    public PhotoOrderIF getPhotoOrder()
    {
        return photoOrder;
    }

    public void setPhotoOrder(PhotoOrderIF photoOrder)
    {
        this.photoOrder = photoOrder;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
    }
}
