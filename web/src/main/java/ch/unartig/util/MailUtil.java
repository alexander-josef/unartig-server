/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 17, 2005$
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
 * Revision 1.2  2007/03/13 16:55:03  alex
 * template for properties
 *
 * Revision 1.1  2007/03/01 18:23:42  alex
 * initial commit maven setup no history
 *
 * Revision 1.7  2006/12/27 13:33:45  alex
 * better email
 *
 * Revision 1.6  2006/11/22 16:29:56  alex
 * small fixes
 *
 * Revision 1.5  2006/11/19 21:48:46  alex
 * google analytics with ssl
 *
 * Revision 1.4  2006/11/17 13:21:41  alex
 * email notifiaction fix
 *
 * Revision 1.3  2006/02/07 14:48:53  alex
 * bug 820 and minor refactorings
 *
 * Revision 1.2  2006/01/12 23:53:42  alex
 * error zeugs
 *
 * Revision 1.1  2005/11/18 11:10:22  alex
 * customer service message
 *
 ****************************************************************/
package ch.unartig.util;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigInvalidArgument;
import ch.unartig.studioserver.Registry;
import ch.unartig.studioserver.beans.ScOrderItem;
import ch.unartig.studioserver.beans.ShoppingCart;
import ch.unartig.studioserver.businesslogic.PhotoOrderIF;
import org.apache.struts.util.MessageResources;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

public class MailUtil
{
    public static final String _KEY_MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String _KEY_MAIL_HOST = "mail.host";
    public static final String _KEY_MAIL_FROM = "mail.from";

    /**
     * @param subject
     * @param body
     * @param mailToAddress
     * @param mailFromAddress
     * @throws MessagingException
     */
    public static void sendMail(String subject, String body, String mailToAddress, String mailFromAddress) throws MessagingException
    {

        Properties javaMailProps = System.getProperties();
        final String mailUser = "user";
        final String mailPassword = "password";
        final Address address = new InternetAddress(mailToAddress);
        Authenticator authenticator = new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(mailUser, mailPassword);
            }
        };
        javaMailProps.put(_KEY_MAIL_SMTP_HOST, Registry.getMailHost());
//            javaMailProps.put("mail.smtp.auth", "true");
//            javaMailProps.put("mail.user", "alex");
//            javaMailProps.put("mail.auth", "true");
        javaMailProps.put(_KEY_MAIL_HOST, Registry._MAIL_HOST);
//            javaMailProps.put("mail.smtp.starttls.enable","true");
        javaMailProps.put(_KEY_MAIL_FROM, mailFromAddress);
//            javaMailProps.put(_KEY_MAIL_FROM, "kundendienst@unartig.ch");
//            javaMailProps.put("mail.smtps.user", "alex");
//            javaMailProps.put("mail.smtps.host", "unartig.ch");
//            javaMailProps.put("mail.smtps.from", "kundendienst@unartig.ch");

        Session mailSession = Session.getDefaultInstance(javaMailProps);

//            Transport transport = mailSession.getTransport("smtps");

        Message message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setText(body);
        message.addRecipient(Message.RecipientType.TO, address);
        Transport.send(message);
//            transport.sendMessage(message,message.getAllRecipients());


    }

    /**
     * create the email body with correct formating
     * 1. column: quantity (characters 1-9)
     * 2. column: Format (Characters 11 - 28 )
     * 3. column: Photo (30 - 49)
     * 4. column: price?
     * todo remove shoppingCart, all information should be available through photoorder interface
     *
     * @param downloadLink
     * @param shoppingCart
     * @param content
     * @param locale
     * @param photoOrder   Interface for the photo Order
     * @return
     * @throws UAPersistenceException
     */
    public static StringBuffer generateBody(PhotoOrderIF photoOrder, String downloadLink, ShoppingCart shoppingCart, MessageResources content, Locale locale)
            throws UAPersistenceException
    {
        try
        {
            String quantity = content.getMessage(locale, "email.orderAccepted.body.table.quantity");
            String format = content.getMessage(locale, "email.orderAccepted.body.table.format");
            String photoName = content.getMessage(locale, "email.orderAccepted.body.table.photoName");
            String price = content.getMessage(locale, "email.orderAccepted.body.table.price");

            StringBuffer localizedBody = new StringBuffer(content.getMessage(locale, "email.orderAccepted.body.greeting"));
            localizedBody.append(content.getMessage(locale, "email.orderAccepted.body.part1"));

            if (photoOrder != null)
            {
                // These variables are only set if the order has been processed already ....
                String orderId = photoOrder.getOrder().getOrderId().toString();
                String oipsOrderId = photoOrder.getOrder().getOipsOrderId() != null ? photoOrder.getOrder().getOipsOrderId() : "00000";
                localizedBody.append('(').append(content.getMessage(locale, "email.orderAccepted.body.ordernumber"));
                localizedBody.append(orderId).append("-").append(oipsOrderId).append(')').append('\n');
            }

            localizedBody.append("=======================================================================\n");
            localizedBody
                    .append(createTableDataString(quantity, 9))
                    .append("\t")
                    .append(createTableDataString(format, 17))
                    .append("\t")
                    .append(createTableDataString(photoName, 19))
                    .append("\t")
                    .append(price)
                    .append("\n\n");
            for (Iterator iterator = shoppingCart.getScItemsForConfirmation().iterator(); iterator.hasNext();)
            {
                ScOrderItem orderItem = (ScOrderItem) iterator.next();
                localizedBody
                        .append(createTableDataString(String.valueOf(orderItem.getQuantity()), 9))
                        .append("\t")
                        .append(createTableDataString(orderItem.getProduct().getProductName(), 17))
                        .append("\t")
                        .append(createTableDataString(orderItem.getPhoto().getDisplayTitle(), 19))
                        .append("\t")
                        .append(orderItem.getFormattedPrice())
                        .append(" ")
                        .append(shoppingCart.getCurrency())
                        .append("\n");
            }

            // if order has been processed already (credit card orders, photoOrderIf not null) :
            localizedBody.append("=======================================================================\n\n");
            if (photoOrder != null)
            {
                // todo this only includes check for germany and switzerland!!!!!!
                if (shoppingCart.getShippingHandlingCHE() > 0 || shoppingCart.getShippingHandlingGER() > 0)
                {
                    localizedBody.append(content.getMessage(locale, "shipping") + ": " + shoppingCart.getFormattedShippingMajorUnits() +  " " + shoppingCart.getCurrency() + "\n");
                }
                // todo shipping und handling
                localizedBody.append(content.getMessage(locale, "email.orderAccepted.body.ccBooked"));
                localizedBody.append(" " + shoppingCart.getFormattedTotal()  + " " + shoppingCart.getCurrency() + "\n");
                localizedBody.append(content.getMessage(locale, "email.orderAccepted.body.ccNoteColorplaza"))
                        .append("\n\n");
            }
            localizedBody.append("Link zum Fotodownload : \n");
            localizedBody.append(downloadLink).append("\n");
            localizedBody.append("=======================================================================\n\n")
                    .append(content.getMessage(locale, "email.orderAccepted.body.part2"))
                    .append("--")
                    .append(content.getMessage(locale, "email.orderAccepted.body.end"));
            return localizedBody;
        } catch (UnartigInvalidArgument unartigInvalidArgument)
        {
            unartigInvalidArgument.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return new StringBuffer("INVALID ARGUMENT");
        }

    }

    /**
     * this helper methods returns a Strinbuffer containing the passed content-parameter with a total length of totallength
     * <p> - the Stringbuffer will be ammended with white space to the total length if necessary
     * <p> - the passed content will be cut at the totallength if necessary
     *
     * @param content     the table data entry content
     * @param totalLength in number of chars for the newly created string
     * @return formatted table data entry
     */
    private static StringBuffer createTableDataString(String content, int totalLength)
    {
        StringBuffer retVal;
        if (content.length() < totalLength)
        {
            retVal = new StringBuffer(content);
            while (retVal.length() < totalLength)
            { // add one space
                retVal.append(' ');
            }
        } else
        {
            retVal = new StringBuffer(content.substring(0, totalLength));
        }
        return retVal;
    }
}
