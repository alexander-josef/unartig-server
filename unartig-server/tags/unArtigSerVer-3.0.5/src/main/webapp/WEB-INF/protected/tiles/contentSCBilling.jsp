<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="shoppingCart" type="ch.unartig.studioserver.beans.ShoppingCart" scope="session"/>

<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.billing.src" altKey="title.billing.alt"/>
    <p id="head_coment">
        <bean:message bundle="CONTENT" key="billing.select"/>
    </p>
</div>


<html:form action="/checkOutOverview">
    <html:hidden property="page" value="2"/>
    <!-- make sure invoice is selected-->
    <!--todo we still need that?-->
    <html:hidden property="paymentMethodInvoice" value="false"/>


    <logic:messagesPresent message="true">
        <div class="contentN rightalign padding19top">
            <p class="errorstyle"><span>BITTE BEACHTEN<br> Bei der Abwicklung traten Probleme auf (todo vielleicht kann man dieses Feld noch rot hinterlegen):</span>
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

    <div class="contentW rightalign padding19both bottomBorder">

        <c:if test="${shoppingCart.onlyDigitalProducts}">
            <p><br><bean:message key="message.payment.onlyDigitalProducts" bundle="CONTENT"/><br/><br/>
            </p>
        </c:if>

        <!--todo: auch nach Laendern checken .... fuer gewisse Laender ist Lieferung nur mit Kreditkartenzahlung moeglich-->
        <c:if test="${!shoppingCart.onlyDigitalProducts}">

            <!--<h3>-->
            <!--Bitte wählen Sie ihre Zahlungsmethode-->
            <!--</h3>-->
            <table class="leftalign">
                <tr>
                    <td><html:radio property="paymentMethod" value="invoice"/></td>
                    <td class="checkboxtext"><p>&nbsp;<bean:message bundle="CONTENT" key="billing.invoice"/></p>
                    </td>
                </tr>
                <tr>
                    <td><html:radio property="paymentMethod" value="creditCard"/></td>
                    <td class="checkboxtext"><p>&nbsp;<bean:message bundle="CONTENT" key="billing.cc"/></p>
                    </td>
                </tr>

            </table>
        </c:if>
    </div>

    <div class="contentW rightalign padding19top">
        <c:if test="${shoppingCart.onlyDigitalProducts}">
            <html:hidden property="paymentMethod" value="creditCard"/>
        </c:if>
        <table class="leftalign">
            <tr>
                <td>
                    <html:radio property="creditCardType" value="masterCard"/>
                </td>
                <td>
                    <p>&nbsp;Master Card</p>
                </td>
                <!--<td><img src="/images/master_card.gif" alt="master" /></td>-->
            </tr>
            <tr>
                <td>
                    <html:radio property="creditCardType" value="visa"/>
                </td>
                <td>
                    <p>&nbsp;Visa</p>
                </td>
                <!--<td><img src="/images/visa_card.gif" alt="master" /></td>-->
            </tr>
        </table>
    </div>

    <div class="contentW rightalign padding19top">
        <fieldset class="forms">
            <table>
                <tr>
                    <td>
                        <label for="creditCardNumber"><p><bean:message bundle="CONTENT" key="billing.cc.number" />&nbsp;</p></label>
                    </td>
                    <td colspan="2">
                        <html:text property="creditCardNumber" styleClass="kontaktfeld" errorStyleClass="inputError" styleId="creditCardNumber" size="25"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <p class=""><bean:message bundle="CONTENT" key="billing.cc.exp" />&nbsp;</p>
                    </td>

                    <td>
                        <html:select property="creditCardExpiryMonth" styleId="creditCardExpiryMonth" errorStyleClass="inputError">
                            <html:option value="**"/>
                            <html:option value="01"/>
                            <html:option value="02"/>
                            <html:option value="03"/>
                            <html:option value="04"/>
                            <html:option value="05"/>
                            <html:option value="06"/>
                            <html:option value="07"/>
                            <html:option value="09"/>
                            <html:option value="08"/>
                            <html:option value="09"/>
                            <html:option value="10"/>
                            <html:option value="11"/>
                            <html:option value="12"/>
                        </html:select>
                    </td>

                    <td>
                        <html:select property="creditCardExpiryYear" styleId="creditCardExpiryYear" errorStyleClass="inputError">
                            <html:option value="****"/>
                            <html:option value="2010"/>
                            <html:option value="2011"/>
                            <html:option value="2012"/>
                            <html:option value="2013"/>
                            <html:option value="2014"/>
                            <html:option value="2015"/>
                            <html:option value="2016"/>
                            <html:option value="2017"/>
                            <html:option value="2018"/>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="creditCardHolderName"><p class="nobreak"><bean:message bundle="CONTENT" key="billing.cc.owner"/>&nbsp;</p></label>
                    </td>
                    <td colspan="2">
                        <html:text styleId="creditCardHolderName" property="creditCardHolderName" size="25"/>
                    </td>
                </tr>
            </table>
        </fieldset>

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
            <%--<html:link href="javascript:document.forms[0].submit()">--%>
                <html:image bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>
            <%--</html:link>--%>
        </div>
    </div>

    <div class="contentW rightalign padding19bottom"><p class="rightalign"><bean:message bundle="CONTENT" key="order.conf.later"/></p>
    </div>


</html:form>