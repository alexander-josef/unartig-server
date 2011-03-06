<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumBean" type="ch.unartig.studioserver.beans.AbstractAlbumBean" scope="session"/>

<html:xhtml/>

<html:img styleClass="leftalign" bundle="IMAGES" srcKey="sidebar.title.prices.src" altKey="sidebar.title.prices.alt"/>
<div class="sidebarElement">
    <table class="sidebarContent">
        <tr>
            <td><h3>digital&nbsp;
                <html:link action="/explainDigital"
                           onclick="PriceList=window.open('/explainDigital.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400px,height=450px,left=200px,top=100px'); PriceList.focus(); return false;">
                    <img src="/images/questionmark.gif" alt="help"/>
                </html:link>
                &nbsp;</h3></td>
            <td><h3>SFr.</h3></td>
            <%--<td><h3>Euro</h3></td>--%>
        </tr>

        <c:forEach items="${albumBean.album.activeProducts}" var="product" varStatus="forEachStatus">
            <c:if test="${product.digitalProduct}">
                <tr class="evenOddRow${forEachStatus.index%2}">
                    <td class="priceListFirstRow"><p>${product.productType.name}</p></td>
                    <td class="priceListSecondRow" ><p>${product.formattedPriceCHF}</p></td>
                    <%--<td><p>${product.formattedPriceEUR}</p></td>--%>
                </tr>
            </c:if>
        </c:forEach>

<%-- no print products currently
        <tr>
            <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
            <td>
                <h3>Abzug&nbsp;
                    <html:link action="/explainPrint"
                               onclick="PriceList=window.open('/explainPrint.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400px,height=400px,left=200px,top=100px'); PriceList.focus(); return false;">
                        <img src="/images/questionmark.gif" alt="help"/></html:link>
                    &nbsp;
                </h3>

            </td>
            <td><h3>SFr.</h3></td>
            <td><h3>Euro</h3></td>
        </tr>
--%>

<%--
        todo : refactor the getAlbumProducts in many methods: one for each product category
--%>

        <c:forEach items="${albumBean.album.activeProducts}" var="product" varStatus="forEachStatus">
            <c:if test="${!product.digitalProduct}">
                <tr class="evenOddRow${forEachStatus.index%2}">
                    <td><p>${product.productType.name}</p></td>
                    <td><p>${product.formattedPriceCHF}</p></td>
                    <td><p>${product.formattedPriceEUR}</p></td>
                </tr>
            </c:if>
        </c:forEach>



    </table>

</div>

<div class="sidebarBotton">
</div>




