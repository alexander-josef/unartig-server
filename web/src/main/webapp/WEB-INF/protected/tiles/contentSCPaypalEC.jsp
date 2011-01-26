<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="shoppingCart" type="ch.unartig.studioserver.beans.ShoppingCart" scope="session"/>

<html:xhtml/>

<div class="contentW leftalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.billing.src" altKey="title.billing.alt"/>
    <%--
        <p id="head_coment">
            <bean:message bundle="CONTENT" key="billing.select"/>
        </p>
    --%>
</div>


<html:form action="/checkOutPaypalEC">
    <html:hidden property="page" value="2"/>
    <!-- make sure invoice is selected-->
    <!--todo we still need that?-->
    <html:hidden property="paymentMethodInvoice" value="false"/>


    <logic:messagesPresent message="true">
        <div class="contentN rightalign padding19top">
                <%-- (todo vielleicht kann man dieses Feld noch rot hinterlegen) --%>
            <p class="errorstyle"><span>BITTE BEACHTEN<br> Bei der Abwicklung traten Probleme auf :</span>
            </p>
            <ul>
                <html:messages id="msg" message="true" bundle="ERROR">
                    <li><p class="errorstyle">
                        <bean:write name="msg"/>
                    </p></li>
                </html:messages>
            </ul>
        </div>
    </logic:messagesPresent>


    <logic:messagesPresent>
        <div class="contentW rightalign padding19top">
            <ul>
                <html:messages id="errors" bundle="ERROR">
                    <li><p class="errorstyle">
                        <bean:write name="errors" bundle="ERROR"/>
                    </p></li>
                </html:messages>
            </ul>
        </div>
    </logic:messagesPresent>


    <!--todo: auch nach Laendern checken .... fuer gewisse Laender ist Lieferung nur mit Kreditkartenzahlung moeglich-->
    <c:if test="${!shoppingCart.onlyDigitalProducts}">
        <div class="contentW rightalign padding19both bottomBorder">

            <h3>
                Something went wrong ....
                Ordering of print products not possible
            </h3>
        </div>
    </c:if>

    <div class="contentW rightalign padding19top">
        <c:if test="${shoppingCart.onlyDigitalProducts}">
            <html:hidden property="paymentMethod" value="creditCard"/>
        </c:if>
        <table class="leftalign">
            <tr>
                <td>
                    <bean:message key="payment.paypal.explanation" bundle="CONTENT"/>
                        <%-- TODO Paypal button here?--%>

                </td>
            </tr>

            <tr>
                <td>
                    <img src="https://www.paypal.com/en_US/CH/i/btn/btn_xpressCheckout.gif" align="left"
                         style="margin-right:7px;" alt="Paypal Express Checkout">
                </td>
            </tr>
        </table>
    </div>


    <div class="contentW rightalign padding19both bottomBorder">

            <%--
                    <br/>
                    <label for="creditCardStreet">creditCardStreet</label>
                    <html:text property="creditCardStreet" styleId="creditCardStreet"></html:text>
                    <br/>
                    <label for="creditCardZIP">creditCardZIP/creditCardCity</label>
                    <html:text size="5" property="creditCardZIP" styleId="creditCardZIP"></html:text>&nbsp;&nbsp;
                    <html:text property="creditCardCity"></html:text>
                    <br/>

            --%>
        <br/>

        <h3 class="leftalign leftclear"><bean:message key="creditcard.booking.explanation" bundle="CONTENT"/></h3>
        <br/>

    </div>

    <!--Shopping-Cart Navigation -->

    <div class="contentW rightalign padding19top">

        <div class="leftalign">
            <html:link action="/checkOutAddress">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>

        <div class="rightalign">
            <html:image bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>

        </div>
    </div>

    <div class="contentW rightalign padding19bottom"><p class="rightalign"><bean:message bundle="CONTENT"
                                                                                         key="order.conf.later"/></p>
    </div>


</html:form>