<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="display" type="ch.unartig.studioserver.beans.DisplayBean" scope="request"/>
<html:xhtml/>

<!--<div class="content_wide" id="content_wide_display">-->

<table id="displaycontainer">
    <tr>
        <td id="display_preview_left">
            <c:if test="${ ! empty display.previousPhoto}">
                <ul id="preview_${display.previousPhoto.orientationSuffix}" class="preview">
                    <li class="preview_imageholder">
                        <html:link href="/display/${display.previousPhoto.photoId}/display.html" name="display" property="previousPhotoLinkParams" titleKey="photo.previous">
                            <html:img src="${display.previousPhoto.thumbnailUrl}" altKey="photo.previous"/>
                        </html:link>
                    </li>
                    <li>
                        <html:link href="/display/${display.previousPhoto.photoId}/display.html" name="display" property="previousPhotoLinkParams" titleKey="photo.previous">
                            <p class="next_photo"><bean:message bundle="CONTENT" key="photo.previous"/></p>
                        </html:link>
                    </li>
                </ul>
            </c:if>
        </td>

        <td id="display_center">
            <table>
                <tr>
                    <th id="framecorner_topleft"></th>
                    <th id="frame_top">
                        <div id="order_button">
                            <html:link action="/placeInCart" paramId="orderedPhotoId" paramName="display" paramProperty="displayPhoto.photoId">
                                <bean:message bundle="BUTTONS" key="order.this.picture"/>
                            </html:link>
                        </div>
                    </th>
                    <th id="framecorner_topright"></th>
                </tr>
                <tr>
                    <td id="frame_left"></td>
                    <td id="display_imageholder">
                        <table id="imagetable_${display.displayPhoto.orientationSuffix}" background="${display.displayPhoto.displayUrl}">
                            <!--change 'landscape' to 'portrait' depending on format, also the 'id' !!!-->
                            <tr>
                                <td id="lefthover">
                                    <c:if test="${display.previousPhoto !=null}">
                                        <html:link href="/display/${display.previousPhoto.photoId}/display.html" name="display" property="previousPhotoLinkParams" title="vorheriges Photo">
                                            <img src="/images/display_hoverarea_${display.displayPhoto.orientationSuffix}.gif" alt=""/>
                                        </html:link>
                                    </c:if>

                                    <c:if test="${display.previousPhoto ==null}">
                                        <img src="/images/display_hoverarea_${display.displayPhoto.orientationSuffix}.gif" alt=""/></c:if>
                                </td>
                                <!--change 'landscape' to 'portrait' depending on format-->
                                <td id="centerarea"></td>
                                <td id="righthover">
                                    <c:if test="${display.nextPhoto !=null}">
                                        <html:link href="/display/${display.nextPhoto.photoId}/display.html" name="display" property="nextPhotoLinkParams" title="naechstes Photo">
                                            <img src="/images/display_hoverarea_${display.displayPhoto.orientationSuffix}.gif" alt=""/>
                                        </html:link>
                                    </c:if>
                                    <c:if test="${display.nextPhoto ==null}">
                                        <img src="/images/display_hoverarea_${display.displayPhoto.orientationSuffix}.gif" alt=""/>
                                    </c:if>
                                </td>
                                <!--change 'landscape' to 'portrait' depending on format-->
                            </tr>
                        </table>
                    </td>
                    <td id="frame_right"></td>
                </tr>
                <tr>
                    <th id="framecorner_bottomleft"></th>
                    <th id="frame_bottom"></th>
                    <th id="framecorner_bottomright"></th>
                </tr>
                <tr>
                    <td colspan="3" id="filename">${display.displayPhoto.filename} &nbsp;--&nbsp; ${display.displayPhoto.shortTimeString}</td>
                </tr>
            </table>
        </td>
        <td id="display_preview_right">
            <c:if test="${ ! empty display.nextPhoto}">
                <ul id="preview_${display.nextPhoto.orientationSuffix}" class="preview">
                    <!--change 'landscape' to 'portrait' depending on format-->
                    <li class="preview_imageholder">

                        <html:link href="/display/${display.nextPhoto.photoId}/display.html" name="display" property="nextPhotoLinkParams" titleKey="photo.next">
                            <html:img src="${display.nextPhoto.thumbnailUrl}" altKey="photo.next"/>
                        </html:link>

                    </li>
                    <li>
                        <html:link href="/display/${display.nextPhoto.photoId}/display.html" name="display" property="nextPhotoLinkParams" titleKey="photo.next">
                            <p class="next_photo"><bean:message bundle="CONTENT" key="photo.next"/></p>
                        </html:link>
                    </li>
                </ul>
            </c:if>
            <ul>
                <html:form action="/">
                <li>Startnummer</li>
                <li><input/></li>
                </html:form>
            </ul>
        </td>
    </tr>
</table>
<%--  change 'landscape' to 'portrait' depending on format  --%>


<!--</div>-->
