<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="checkOutForm" type="ch.unartig.studioserver.beans.CheckOutForm" scope="session"/>


<html:xhtml/>

<div class="contentW rightalign padding19both bottomBorder">
    <div  id="address_bill_out">
   <h3><bean:message bundle="CONTENT" key="address"  /><html:link action="/checkOutAddress">&nbsp;...<bean:message bundle="BUTTONS" key="change"  /></html:link></h3>
    <p>${checkOutForm.firstName} ${checkOutForm.lastName}<br/>
    ${checkOutForm.addr1}<br/>
     <c:if test="${ ! empty checkOutForm.addr2}">${checkOutForm.addr2}<br/></c:if>
    ${checkOutForm.zipCode} ${checkOutForm.city}</p>
    </div>
<%--    <div  id="address_mail_out">
   <h3><bean:message bundle="CONTENT" key="address.mail"  /><a href="ShoppingCartAddress.html">&nbsp;...<bean:message bundle="BUTTONS" key="change"  /></a></h3>
        <p>${checkOutForm.shippingFirstName} ${checkOutForm.shippingLastName}<br/>
        ${checkOutForm.shippingAddr1}</p><br/>
        <logic:notEmpty name="checkOutForm" property="shippingAddr2">${checkOutForm.shippingAddr2}<br/></logic:notEmpty>
        ${checkOutForm.shippingZipCode} ${checkOutForm.shippingCity}</p>
    </div>--%>
</div>