<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="shoppingCart" type="ch.unartig.studioserver.beans.ShoppingCart" scope="session"/>
<jsp:useBean id="checkOutForm" type="ch.unartig.studioserver.beans.CheckOutForm" scope="session"/>
<html:xhtml/>

<script language="Javascript" type="text/javascript">
    <!--
    var isSubmitted = false;
    function confirmOrder()
    {
        if (isSubmitted)
        {
            return false;
        }
        else
        {
            isSubmitted = true;
            return true;
        }
    }
    //-->
</script>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.summary.src" altKey="title.summary.alt"/>
    <p id="head_coment">
        <bean:message bundle="CONTENT" key="order.doublecheck"/>
    </p>

</div>

<logic:messagesPresent message="true">
    <div class="contentW rightalign padding19top">
        <ul>
            <html:messages id="error" bundle="ERROR" message="true">
                <li><p class="errorstyle">
                    <bean:write name="error" bundle="ERROR"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>

<logic:messagesPresent>
    <div class="contentW rightalign padding19both bottomBorder">
        <ul>
            <html:messages id="error" bundle="ERROR">
                <li><p class="errorstyle">
                    <bean:write name="error" bundle="ERROR"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>


<%-- *********************Send Order**********************  --%>

<div class="contentW rightalign padding19both bottomBorder">
    <html:form action="/checkOutConfirm" onsubmit="return(confirmOrder());">
        <html:hidden property="page" value="3"/>

        <table class="rightalign">
            <tr>
                <td>
                    <p>
                        <bean:message bundle="BUTTONS" key="agb.read"/>
                        &nbsp;</p>
                </td>
                <td>
                    <html:checkbox errorStyleClass="inputError" property="acceptTermsCondition" styleClass="admin_button" value="true"/>
                </td>
            </tr>
        </table>
        <br/><br/>

        <div class="leftalign">
            <html:link action="/checkOutStoreAddress">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>

        <div class="rightalign">
                <%--<html:link action="/checkOutConfirm">--%>
            <html:image bundle="IMAGES" srcKey="btn.order.src" altKey="btn.order.alt"/>
                <%--</html:link>--%>

                <%--             href="ShoppingCartConfirmIN.html" if logged in--%>
        </div>


    </html:form>

</div>

<%-- ******************Address*************************  --%>

<div class="contentW rightalign padding19both bottomBorder">
    <div id="address_bill_out">
        <h3>
            <bean:message bundle="CONTENT" key="address"/>
            <html:link action="/checkOutAddress">&nbsp;...
                <bean:message bundle="BUTTONS" key="change"/>
            </html:link>
        </h3>

        <p>${checkOutForm.firstName} ${checkOutForm.lastName}<br/>
            ${checkOutForm.addr1}<br/>
            <c:if test="${ ! empty checkOutForm.addr2}">${checkOutForm.addr2}<br/></c:if>
            ${checkOutForm.zipCode} ${checkOutForm.city}<br/>
            ${checkOutForm.country}</p>
    </div>
    <%--    <div  id="address_mail_out">
   <h3><bean:message bundle="CONTENT" key="address.mail"  /><a href="ShoppingCartAddress.html">&nbsp;...<bean:message bundle="BUTTONS" key="change"  /></a></h3>
        <p>${checkOutForm.shippingFirstName} ${checkOutForm.shippingLastName}<br/>
        ${checkOutForm.shippingAddr1}</p><br/>
        <logic:notEmpty name="checkOutForm" property="shippingAddr2">${checkOutForm.shippingAddr2}<br/></logic:notEmpty>
        ${checkOutForm.shippingZipCode} ${checkOutForm.shippingCity}</p>
    </div>--%>
</div>

<%-- **********************Billing*********************  --%>

<div class="contentW rightalign padding19both bottomBorder">
    <h3>
        <bean:message bundle="CONTENT" key="billing"/>
<%--

        <html:link action="/checkOutStoreAddress">&nbsp;...
            <bean:message bundle="BUTTONS" key="change"/>
        </html:link>
--%>
    </h3>
    <p>
        <bean:message bundle="CONTENT" key="billing.paypal"/>
    </p>

<%--

    <c:if test="${checkOutForm.paymentMethodInvoice}">
        <p>
            <bean:message bundle="CONTENT" key="billing.invoice"/>
        </p>
    </c:if>
    <c:if test="${checkOutForm.paymentMethodCreditCard}">
        <p>
            <bean:message bundle="CONTENT" key="billing.cc"/>
        </p>
        <br>

        <p>Karteninhaber : ${checkOutForm.creditCardHolderName}</p>

        <p>Kartennummer : ${checkOutForm.obfuscatedCreditCardNumber}</p>
    </c:if>
--%>
</div>

<%-- *********************Order list**********************  --%>

<div class="contentW rightalign padding19top">
    <table class="sc_table_description">
        <tr>
            <td class="sc_format_firstcol">
                <p><span><bean:message bundle="CONTENT" key="order.article"/></span></p>
            </td>
            <td class="sc_format_secondcol">
                <p><span><bean:message bundle="CONTENT" key="order.format"/></span></p>
            </td>
            <td class="sc_format_thirdcol">
                <p><span><bean:message bundle="CONTENT" key="order.amount"/></span></p>
            </td>
            <td class="price_group">
                <p class="rightalign"><span><bean:message bundle="CONTENT" key="order.price"/></span>&nbsp;(${shoppingCart.currency})</p>
            </td>
        </tr>
        <tr>
            <td class="spacerCell"></td>
        </tr>
    </table>
</div>

<logic:iterate name="shoppingCart" property="scItemsForConfirmation" id="scItem" indexId="scItemIndex">
    <bean:define id="product" name="shoppingCart" property="productMapped(${scItem.productId})" toScope="page"/>
    <%-- IF photo changed:  --%>
    <c:if test="${scItem.photoId != lastPhotoId}">
        <%--check for last shopping cart order item (new photo next) --%>

        <c:if test="${scItemIndex != 0}">
            <!--end of photo if-->
            <p class="sc_orderlist_filename">${photo.displayTitle}</p>
            </div> <%-- closing if statment from line 42 --%>
        </c:if>

        <%-- create page variable 'photo' --%>
        <bean:define id="photo" name="shoppingCart" property="photoMapped(${scItem.photoId})" toScope="page"/>

        <div class="contentWplus rightalign padding19both bottomBorder">
        <%--NOTE : this div will be closed during the loop in the if statement just above this line --%>

        <ul class="album">
            <li class="albumslide">
                <div class="imageholder_${photo.orientationSuffix}">
                    <html:img src="${photo.thumbnailUrl}" title="${photo.displayTitle}"/>
                </div>
            </li>
        </ul>


    </c:if>
    <%--END IF--%>

    <%--
    loop over sc order items
    --%>

    <table class="sc_order_list rightalign">
        <tr>
            <td class="sc_format_secondcol">
                <p>${product.productType.name}</p><!-- selected format/product Type-->
            </td>
            <td class="sc_format_thirdcol">
                <p>${scItem.quantity}</p><!-- how many times the format -->
            </td>
            <td class="price_group">
                <p class="rightalign">${scItem.price} &nbsp;${shoppingCart.currency}</p>
            </td>
        </tr>
    </table>

    <%-- END Loop order items --%>

    <%--IF photo changed --%>
    <%-- END IF --%>
    <bean:define id="lastPhotoId" value="${scItem.photoId}" toScope="page"/>

    <!--end iterator-->

</logic:iterate>
<%-- closing div for the last element in the loop --%>
<p class="sc_orderlist_filename">${photo.displayTitle}</p>
</div>

<%-- ********************Sutotal Total***********************  --%>

<div class="contentW rightalign padding19top ">
    <table class="sc_table_description">
        <tr>
            <td class="sc_table_description_head" colspan="4">
                <p><span><bean:message bundle="CONTENT" key="price.subtotal"/></span></p>
            </td>
        </tr>
        <tr>
            <td class="sc_format_firstcol">
                <p>
                    <bean:message bundle="CONTENT" key="pictures"/>
                </p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td class="price_group">
                <p class="rightalign">${shoppingCart.formattedSubtotalPhotos} &nbsp;${shoppingCart.currency}</p></td>
        </tr>
        <tr>
            <td class="sc_format_firstcol">
                <p>
                    <bean:message bundle="CONTENT" key="shipping"/>
                </p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td class="price_group"><p class="rightalign">${shoppingCart.formattedShippingPrice} &nbsp;${shoppingCart.currency}</p>
            </td>
        </tr>
    </table>
</div>

<div class="contentW rightalign padding19both bottomBorder">

    <table class="sc_table_description">
        <tr>
            <td class="sc_format_firstcol">
                <p><span><bean:message bundle="CONTENT" key="price.total"/></span>&nbsp;
                    <bean:message bundle="CONTENT" key="price.inclVAT"/>
                </p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td class="price_group"><p class="rightalign">
                <span>${shoppingCart.formattedTotal} &nbsp;${shoppingCart.currency}</span></p></td>
        </tr>
    </table>
</div>

<div class="contentW rightalign padding19both bottomBorder">

    <div class="rightalign">
        <html:link href="javascript:window.print()">
            <html:img bundle="IMAGES" srcKey="btn.printorder.src" altKey="btn.printorder.alt"/>
        </html:link>
    </div>
</div>

<%-- **********************Send Order*********************  --%>

<logic:messagesPresent message="true">
    <div class="contentW rightalign padding19top">
        <ul>
            <html:messages id="error" bundle="ERROR" message="true">
                <li><p class="errorstyle">
                    <bean:write name="error" bundle="ERROR"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>

<logic:messagesPresent>
    <div class="contentW rightalign padding19top">
        <ul>
            <html:messages id="error" bundle="ERROR">
                <li><p class="errorstyle">
                    <bean:write name="error" bundle="ERROR"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>


<div class="contentW rightalign padding19both">
    <html:form action="/checkOutConfirm" onsubmit="return(confirmOrder());">
        <html:hidden property="page" value="3"/>

        <table class="rightalign">
            <tr>
                <td>
                    <p>
                        <bean:message bundle="BUTTONS" key="agb.read"/>
                        &nbsp;</p>
                </td>
                <td>
                    <html:checkbox errorStyleClass="inputError" property="acceptTermsCondition" styleClass="admin_button" value="true"/>
                </td>
            </tr>
        </table>
        <br/><br/>

        <div class="leftalign">
            <html:link action="/checkOutStoreAddress">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>

        <div class="rightalign">
                <%--<html:link action="/checkOutConfirm">--%>
            <html:image bundle="IMAGES" srcKey="btn.order.src" altKey="btn.order.alt"/>
                <%--</html:link>--%>

                <%--             href="ShoppingCartConfirmIN.html" if logged in--%>
        </div>


    </html:form>

</div>
