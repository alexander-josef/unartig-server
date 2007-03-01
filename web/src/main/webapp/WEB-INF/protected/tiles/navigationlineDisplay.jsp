<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<jsp:useBean id="display" type="ch.unartig.studioserver.beans.DisplayBean" scope="request"/>
<jsp:useBean id="albumBean" type="ch.unartig.studioserver.beans.AbstractAlbumBean" scope="session"/>

<html:xhtml/>

<div class="navigationline_wide">

    <div class="left">
                <%--<html:link action="/album/${albumBean.album.genericLevelId}/${albumBean.album.longTitle}" name="display" property="backToAlbumParams">--%>
                <html:link action="${albumBean.album.actionString}" name="display" property="backToAlbumParams">
                    <html:img bundle="IMAGES" srcKey="btn.to.album.src" altKey="btn.to.album.alt"/>
                </html:link>
        </div>

</div>