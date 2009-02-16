<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumBean" type="ch.unartig.studioserver.beans.AbstractAlbumBean" scope="session"/>

<html:xhtml/>

<div class="contentNplus rightalign padding19both">
    <c:if test="${albumBean.size==0}">
        <bean:message bundle="CONTENT" key="error.album.photos.notavailable" />
    </c:if>
    <ul class="album">
        <!--in each 'li' change the 'div' class to either 'imageholder-landscape' or 'imageholder_portrait' depending on format-->
        <c:forEach var="thumbnail" items="${albumBean.photosOnPage}" >
            <bean:define id="thisPhotoId" value="${thumbnail.photoId}" toScope="page"/>
            <li class="albumslide">
                <div class="imageholder_${thumbnail.orientationSuffix}"><html:link
                        href="/display/${thumbnail.photoId}/polyball/display.html"><html:img
                        src="${thumbnail.thumbnailUrl}" alt="${thumbnail.displayTitle}"
                        title="${thumbnail.displayTitle}"/></html:link>
                </div>
                <%
                    // todo: remove scriplet code
                    //show slide for ordered photo ?
                    if (albumBean.getOrderedPhotos().containsKey(new Long(thisPhotoId)))
                    { %>
                         <div class="album_slide_info ordered">${thumbnail.shortTimeString}</div>
                <% } else
                { %>
                         <div class="album_slide_info">${thumbnail.shortTimeString}</div>
                <%  }

                %>
            </li>
        </c:forEach>
    </ul>
</div>
<!--End Content Album-->