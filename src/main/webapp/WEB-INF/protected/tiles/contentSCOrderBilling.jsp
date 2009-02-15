<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="checkOutForm" type="ch.unartig.studioserver.beans.CheckOutForm" scope="session"/>

<html:xhtml/>

<div class="contentW rightalign padding19both bottomBorder">
    <h3><bean:message bundle="CONTENT" key="billing"/>
       <%-- <a href="ShoppingCartBilling.html">&nbsp;...<bean:message bundle="BUTTONS" key="change"/></a>--%></h3>
    <c:if test="${checkOutForm.paymentMethodInvoice}">
        <p><bean:message bundle="CONTENT" key="billing.bill"/></p>
    </c:if>
    <c:if test="${!checkOutForm.paymentMethodInvoice}">
        <p><bean:message bundle="CONTENT" key="billing.cc"/></p>
    </c:if>
</div>