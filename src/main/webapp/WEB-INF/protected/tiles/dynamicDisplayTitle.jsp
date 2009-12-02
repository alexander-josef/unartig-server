<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<jsp:useBean id="display" type="ch.unartig.studioserver.beans.DisplayBean" scope="request"/>
unArtig &nbsp; <bean:message bundle="CONTENT" key="title.photo"/>:&nbsp; ${display.albumBean.album.longTitle} &nbsp; ${display.displayPhoto.displayTitle}