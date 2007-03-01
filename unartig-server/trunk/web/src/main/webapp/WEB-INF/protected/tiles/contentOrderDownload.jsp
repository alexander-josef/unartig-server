<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="downloadBean" type="ch.unartig.studioserver.beans.DownloadImageBean" scope="request"/>
<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img styleClass="leftalign" bundle="IMAGES" srcKey="title.sc.download.src" altKey="title.sc.download.alt"/>
</div>

<c:forEach var="downloadableItem" items="${downloadBean.downloadableOrderItems}">
    <div class="contentW rightalign padding19both">
        <table>
            <tr>
                <td>
                    <ul class="album">

                        <li class="albumslide">
                            <div class="imageholder_${downloadableItem.photo.orientationSuffix}">
                                <p class="aktuell_date">${downloadableItem.photo.displayTitle}</p>
                                <html:img src="${downloadableItem.photo.thumbnailUrl}"/>
                            </div>
                        </li>
                    </ul>
                </td>
                <td class="downloadCol">
                    <p>
                        <c:if test="${downloadableItem.product.digitalProduct}">
                            ${downloadableItem.product.productName}
                        </c:if>
                        <c:if test="${!downloadableItem.product.digitalProduct}">
                            Bonus Digi Foto 400x600 zu Ihrer Print-Bestellung
                        </c:if>
                    </p>
                    <br/>
                    <html:link href="${downloadBean.downloadUrl}?phId=${downloadableItem.photo.photoId}">
                        <img src="/images/button/download.gif" alt=""/>
                    </html:link>
                    <p><br/>
                        <bean:message bundle="CONTENT" key="instructions.download"/>
                    </p>
                </td>
                    <%--
                                    <td class="EcardCol">
                                        <img src="/images/button/ecard.gif" alt=""/>

                                        <p><br/>
                                            <bean:message bundle="CONTENT" key="instructions.ecard"/>
                                        </p>
                                    </td>
                    --%>
            </tr>
        </table>
    </div>
</c:forEach>

