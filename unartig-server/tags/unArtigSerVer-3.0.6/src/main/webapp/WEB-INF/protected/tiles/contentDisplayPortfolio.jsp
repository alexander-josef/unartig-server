<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="display" type="ch.unartig.studioserver.beans.DisplayBean" scope="request"/>
<html:xhtml/>

<!--<div class="content_wide" id="content_wide_display">-->

<div id="displaytopspacer"></div>
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

                    </th>
                    <th id="framecorner_topright"></th>
                </tr>
                <tr>
                    <td id="frame_left"></td>
                    <td id="display_imageholder">
                        <img src="${display.displayPhoto.displayUrl}" alt=""></td>
                    <td id="frame_right"></td>
                </tr>
                <tr>
                    <th id="framecorner_bottomleft"></th>
                    <th id="frame_bottom"></th>
                    <th id="framecorner_bottomright"></th>
                </tr>
                <tr>
                    <td colspan="3" id="filename">${display.displayPhoto.filename}
                        &nbsp;--&nbsp; ${display.displayPhoto.shortTimeString}</td>
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
        </td>
    </tr>
    <tr id="display_photographer">
        <td>

            <ul class="aktuell">
                <li>
                    <div>
                        <p class="aktuell_spacer"></p>
                        <html:link action="">
                            <img src="/images/photographers/urban.gif" alt=""/>
                        </html:link>
                        <p class="aktuell_name">Urban</p>
                    </div>
                </li>
            </ul>


        </td>
        <td id="portfolioComment">
            <p><span>Komentar: </span>Dieses fetten Löwen haben wir drei Tage lang durch dir Prärie verfolg, und
                schlussendlich ist aus Versehen ein Elefant auf ihn getreten... das war kein gutes Abenteuer. </p><br/>
            <p><span>Ort: </span>Afrika</p>
            <p><span>Datum: </span>Dzember 1999</p>
            <p><span>Fotograf: </span>Urban Willi</p>
        </td>
        <td></td>
    </tr>
</table>
<%--  change 'landscape' to 'portrait' depending on format  --%>


<!--</div>-->
