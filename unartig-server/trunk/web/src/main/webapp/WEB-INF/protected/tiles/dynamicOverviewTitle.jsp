<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<jsp:useBean id="level" type="ch.unartig.studioserver.model.GenericLevel" scope="request"/>
<bean:message bundle="CONTENT" key="title.photos"/>: &nbsp; ${level.longTitle}