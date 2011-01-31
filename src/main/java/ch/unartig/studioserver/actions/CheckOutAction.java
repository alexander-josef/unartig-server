/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 8, 2005$
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
 * Revision 1.37  2006/12/20 22:40:28  alex
 * reporting for euros done
 *
 * Revision 1.36  2006/11/21 09:34:06  alex
 * no message
 *
 * Revision 1.35  2006/11/17 13:21:41  alex
 * email notifiaction fix
 *
 * Revision 1.34  2006/11/12 16:43:22  alex
 * small fixes shoppingcart
 *
 * Revision 1.33  2006/11/05 22:10:02  alex
 * credit card order works
 *
 * Revision 1.32  2006/11/05 16:41:43  alex
 * action messages work for order confirmation
 *
 * Revision 1.31  2006/11/03 14:36:43  alex
 * update to struts 1.2.9
 *
 * Revision 1.30  2006/11/03 13:15:19  alex
 * some changes
 *
 * Revision 1.29  2006/11/01 10:14:45  alex
 * cc interface check in, transactions work
 *
 * Revision 1.28  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.27  2006/10/17 08:07:07  alex
 * creating the order hashes
 *
 * Revision 1.26  2006/08/25 23:27:58  alex
 * payment i18n
 *
 * Revision 1.25  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 * Revision 1.24  2006/04/29 23:32:07  alex
 * many sola features, bugs, hibernate config
 *
 * Revision 1.23  2006/02/08 15:35:09  alex
 * confirmation message again
 *
 * Revision 1.22  2006/02/07 14:48:53  alex
 * bug 820 and minor refactorings
 *
 * Revision 1.21  2006/01/12 23:53:42  alex
 * error zeugs
 *
 * Revision 1.20  2006/01/11 15:10:47  alex
 * agbs akzeptieren
 *
 * Revision 1.19  2006/01/11 13:35:37  alex
 * bug 856 backend
 *
 * Revision 1.18  2006/01/06 19:54:22  alex
 * implemented post-redirect-get for shopping-cart and checkout, including validator error messages
 *
 * Revision 1.17  2005/11/27 19:39:10  alex
 * fast upload
 *
 * Revision 1.16  2005/11/25 11:09:09  alex
 * removed system outs
 *
 * Revision 1.15  2005/11/22 21:33:16  alex
 * ordering process
 *
 * Revision 1.14  2005/11/21 17:52:58  alex
 * no account action , photo order
 *
 * Revision 1.13  2005/11/20 21:08:06  alex
 * delete of a photo in sc works correct
 *
 * Revision 1.12  2005/11/20 16:42:15  alex
 * sunntig obig
 *
 * Revision 1.11  2005/11/19 11:08:20  alex
 * index navigation works, (extended GenericLevel functions)
 * wrong calculation of fixed checkout overview eliminated
 *
 * Revision 1.10  2005/11/18 19:15:52  alex
 * stuff ...
 *
 * Revision 1.9  2005/11/17 19:41:13  alex
 * new fix sc overview
 *
 * Revision 1.8  2005/11/17 13:36:06  alex
 * check out overview works
 *
 * Revision 1.7  2005/11/16 17:26:19  alex
 * validator enhanced
 *
 * Revision 1.6  2005/11/14 21:49:28  alex
 * small changes
 *
 * Revision 1.5  2005/11/12 23:15:27  alex
 * using indexed properties ... first step
 *
 * Revision 1.4  2005/11/09 21:59:36  alex
 * Order process classes and logic,
 * database creation script now inserts start-data, sql scripts
 * build script
 *
 * Revision 1.3  2005/11/09 15:48:16  alex
 * check out wizard
 *
 * Revision 1.2  2005/11/09 14:39:18  alex
 * check out form wizard
 *
 * Revision 1.1  2005/11/09 09:01:29  alex
 * check out form wizard
 *
 ****************************************************************/
package ch.unartig.studioserver.actions;

import ch.unartig.exceptions.CreditCardException;
import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.exceptions.UnartigSessionExpiredException;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.beans.CheckOutForm;
import ch.unartig.studioserver.beans.ShoppingCart;
import ch.unartig.studioserver.businesslogic.PhotoOrderIF;
import ch.unartig.studioserver.businesslogic.SessionHelper;
import ch.unartig.studioserver.businesslogic.ShoppingCartLogic;
import ch.unartig.studioserver.model.Customer;
import ch.unartig.studioserver.ordermodules.PaypalPaymentOrder;
import ch.unartig.util.DebugUtils;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.*;
import org.apache.struts.actions.MappingDispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;


/**
 * todo insert client object that contains all client relevant information like locale, customer, shoppingcart etc etc
 */
public class CheckOutAction extends MappingDispatchAction {
    Logger _logger = Logger.getLogger(getClass().getName());


    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws UnartigSessionExpiredException
     */
    public ActionForward setMessages(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException {
        System.out.println("CheckOutAction.setMessages start");
        DebugUtils.debugActionMessage(request);
        request.getSession().setAttribute(Globals.ERROR_KEY, request.getAttribute(Globals.ERROR_KEY));
        request.getSession().setAttribute(Globals.MESSAGE_KEY, request.getAttribute(Globals.MESSAGE_KEY));
        System.out.println("CheckOutAction.setMessages end");
        DebugUtils.debugActionMessage(request);
        return mapping.findForward("success");
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward page
     * @throws ch.unartig.exceptions.UnartigSessionExpiredException
     *
     */
    public ActionForward checkSession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException {
        try {
            getExistingScFromSession(request);
            saveToken(request);

        } catch (UnartigSessionExpiredException e) {
            _logger.debug("throwing no shopping cart exception");
            throw new UnartigSessionExpiredException(e);
        }
        return mapping.findForward("success");
    }

    /**
     * place validator messages in session in order to survive redirect
     * forward to errorpage
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return a forward depending on logged in page
     */
    public ActionForward checkOutError(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(Globals.ERROR_KEY, request.getAttribute(Globals.ERROR_KEY));
        request.getAttribute(Globals.MESSAGES_KEY);
//        System.out.println("************* checkOutError  **********************");
//        System.out.println("request.getAttribute(Globals.ERROR_KEY) = " + request.getAttribute(Globals.ERROR_KEY));
//        System.out.println("request.getSession().getAttribute(Globals.ERROR_KEY) = " + request.getSession().getAttribute(Globals.ERROR_KEY));
//        System.out.println("************* checkOutError  **********************");
        return mapping.findForward("errorpage");
    }

    /**
     * update shopping cart, checks if user is logged in and forwards accordingly<br/>
     * checks for non-empty shopping cart, adds error message and goes back to shopping cart
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return a forward depending on logged in page
     */
    public ActionForward startCheckOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionForward retVal = mapping.findForward("toLogin");
        ActionMessages msgs;
        msgs = new ActionMessages();

        try {
            ShoppingCart shoppingCart = getExistingScFromSession(request);
//            shoppingCart.updateCart();
            if (shoppingCart.getScItemsForConfirmation().size() == 0) {
                // todo: jedes photo einzeln checken. kein photo ohne ein produkt mit mindestens menge 1
                _logger.info("check-out attempt with no items in cart");
                msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("shoppingcart.empty.error"));
                retVal = mapping.findForward("backToCart");
            }
        } catch (UnartigSessionExpiredException e) {
            msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("session.expired"));
            _logger.info("session has expired, check out not possible");
            retVal = mapping.findForward("sessionExpired");
        } catch (UAPersistenceException e) {
            _logger.error("persistence exception");
            retVal = mapping.findForward("error");
        }
//        catch (UnartigInvalidArgument unartigInvalidArgument)
//        {
//            msgs.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("shoppingcart.wrongproduct.error"));
//            retVal =  mapping.findForward("backToCart");
//        }
        // todo: in case user is logged in map to address page
        saveMessages(request, msgs);
        return retVal;
    }

    /**
     * forward user to address form
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward to address
     */
    public ActionForward checkOutAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // todo: in case user is logged in map to address page
        _logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        _logger.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        CheckOutForm coForm = (CheckOutForm) form;
        _logger.debug("coForm.isNewCustomer() = " + coForm.isNewCustomer());

        return mapping.findForward("toEnterAddress");
    }

    /**
     * forward user to address form
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward to address
     */
    public ActionForward checkOutStoreAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException {
        // NOT USED CURRENTLY
        checkSessionExpired(request);
        return mapping.findForward("toBilling");
    }


    /**
     * make sure session is alive and shopping cart available!<br>
     * clean out the
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward to overview or session expired page
     * @throws ch.unartig.exceptions.UnartigSessionExpiredException
     *
     */
    public ActionForward checkOutOverview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException {
        checkSessionExpired(request);
        CheckOutForm coForm = (CheckOutForm) form;
        // todo: country in shopping cart, ok?
        // todo: does the scitem know its shopping cart?
        // todo: store a reference to the shopping cart in the sc item?
        SessionHelper.getShoppingCartFromSession(request).setCustomerCountry(coForm.getCountry());
        return mapping.findForward("checkOutOverviewSuccess");
    }

    /**
     * Prepare Express Checkout Session with Paybal<br>
     * clean out the
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward to overview or session expired page
     * @throws ch.unartig.exceptions.UnartigSessionExpiredException
     *
     */
    public ActionForward checkOutPaypalEC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException {
        checkSessionExpired(request);

        CheckOutForm coForm = (CheckOutForm) form;
        // todo: country in shopping cart, ok?
        // todo: does the scitem know its shopping cart?
        // todo: store a reference to the shopping cart in the sc item?
        ShoppingCart shoppingCart = SessionHelper.getShoppingCartFromSession(request);
        shoppingCart.setCustomerCountry(coForm.getCountry());
        String token = PaypalPaymentOrder.callSetupPaypalExpressCheckout(request, coForm, shoppingCart);


        shoppingCart.setPaypalToken(token);


        /* redirect ot paypal with the token received from setting up express checkout*/

        ActionRedirect redirect;
        if (Registry.isDemoOrderMode()) {
            redirect = new ActionRedirect(mapping.findForward("checkOutSuccessPaypalSandbox"));
        } else {
            redirect = new ActionRedirect(mapping.findForward("checkOutSuccessPaypal"));
        }
        redirect.addParameter("token",token);

        return redirect;
    }

    /**
     * helper method to check for an experied session during the check out process
     *
     * @param request Http Request
     * @throws ch.unartig.exceptions.UnartigSessionExpiredException
     *          If Session has expired
     */
    private void checkSessionExpired(HttpServletRequest request) throws UnartigSessionExpiredException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(Registry._NAME_SHOPPING_CART_SESSION) == null) {
            _logger.warn("Session has expired during check-out process! throwing session-expired exception");
            throw new UnartigSessionExpiredException("Session expired during check-out");
        }
    }


    /**
     * user has hit the confirm button ; <br>
     * first check for double-submission using the token</br>
     * make sure AGBs have been accepted and write AGBs to the log file <br>
     * call the shopping cart logic class and store user and decide which page to forward to<br>
     * handle credit card payment details if necessary<br>
     * on successful completion of the checkout-session delete the shopping cart<br>
     * <p> IMPORTANT: delete credit card information
     *
     * @param mapping  struts action mapping
     * @param form     the action form
     * @param request  http request
     * @param response http response
     * @return forward to next page
     * @throws ch.unartig.exceptions.UAPersistenceException
     *          database problem
     * @throws ch.unartig.exceptions.UnartigSessionExpiredException
     *          if the current shopping sesssion has expired
     */
    public ActionForward checkOutConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnartigSessionExpiredException, UAPersistenceException {
        ActionMessages msgs;
        ShoppingCartLogic shoppingCartLogic = new ShoppingCartLogic(request);
        String customerIpAddress;
        msgs = new ActionMessages();
        checkSessionExpired(request);
        HttpSession session = request.getSession(true);
//        Long accountId = (Long) session.getAttribute(Registry._NAME_USER_ACCOUNT_ID_SESSION);
        ShoppingCart sc = (ShoppingCart) session.getAttribute(Registry._NAME_SHOPPING_CART_SESSION);
        CheckOutForm coForm = (CheckOutForm) form;

        if (!isTokenValid(request)) {
            // unexpected error; show order-not-ok message as confirmation message
            _logger.error("wrong token! probably caused by a double submission");
            msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("warnings.invalid.token"));
            // stop here, no processing of the order
        } else {
            saveToken(request);

            _logger.debug("coForm.isAcceptTermsCondition() = " + coForm.isAcceptTermsCondition());

            if (!coForm.isAcceptTermsCondition()) { // terms & conditions not accepted ... return, show error message
                _logger.debug("coForm.isAcceptTermsCondition() = " + coForm.isAcceptTermsCondition());
                _logger.debug("going back");
                msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.acceptTermsCondition.required"));
                saveMessages(request.getSession(), msgs);
                return mapping.findForward("coError");
            }
            shoppingCartLogic.setShoppingCart(sc);
            shoppingCartLogic.setCheckOutForm(coForm);
            shoppingCartLogic.setLocale((Locale) request.getSession().getAttribute(Globals.LOCALE_KEY));
            // retrieve the remote ip-address that will be stored for the records.
            customerIpAddress = request.getRemoteAddr();
            // use the shopping cart logic to store the order definitively
            try {

                shoppingCartLogic.storeAndExecuteOrder(customerIpAddress);
                Customer customer = shoppingCartLogic.getCustomer();
                // todo later: if payment is credit card, add credit card payment page here ....
                // {
                //  mapping.findForward("creditCardPayment")
                // };

                // keep the customer-userid in the session
                session.setAttribute(Registry._NAME_CUSTOMER_ID_SESSION_ATTR, customer.getCustomerId());
                SessionHelper.remove(request, Registry._NAME_SHOPPING_CART_SESSION);
            } catch (CreditCardException ccExp) {

                _logger.info("Creditcard Validation error");
                switch (shoppingCartLogic.getPhotoOrder().getErrorCode()) {
                    case PhotoOrderIF._CREDIT_CARD_REJECTED:
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.creditcard.rejected"));
                        break;
                    case PhotoOrderIF._CREDIT_CARD_TRANSACTION_FAILED:
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.creditcard.transactionfailed"));
                        break;
                    case PhotoOrderIF._CREDIT_CARD_EXPIRY_DATE_ERROR:
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.creditcard.expiryYear"));
                        break;
                    case PhotoOrderIF._UNKNOWN_ERROR:
                        msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.creditcard.unknownerror"));
                        break;
                }
                saveMessages(request.getSession(), msgs);
                return mapping.findForward("checkOutCcException");
            } catch (UnartigException e) {
                // unexpected error; show order-not-ok message as confirmation message
                msgs.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.creditcard.unknownerror"));
                _logger.error("unexpected error; preparing error massage for customer and sending confirmation page");
            }
        }
        saveMessages(request.getSession(), msgs);
        // is this enough for keeping the error-messages alive ???
        DebugUtils.debugActionMessage(request);
        // reset cc infos
        coForm.setCreditCardNumber("");
        coForm.setCreditCardExpiryMonth("");
        coForm.setCreditCardExpiryYear("");
        coForm.setCreditCardHolderName("");
        return mapping.findForward("checkOutConfirmOut");
    }

    /**
     * return a shopping cart or an exception if none exists in session
     *
     * @param request
     * @return
     * @throws UnartigSessionExpiredException
     */
    private ShoppingCart getExistingScFromSession(HttpServletRequest request) throws UnartigSessionExpiredException {
        HttpSession s = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) s.getAttribute(Registry._NAME_SHOPPING_CART_SESSION);
        if (shoppingCart == null) {
            _logger.debug("no shopping cart found in session. throwing exception");
            throw new UnartigSessionExpiredException("Session expired, no Shopping cart available");
        }
        return shoppingCart;
    }

    private void clear(ActionForm form) {
        CheckOutForm coForm = (CheckOutForm) form;
        coForm.clear();
    }

}
