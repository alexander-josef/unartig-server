<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<%--check if this used .... currently not--%>

<div class="time">
    <html:form action="/album/**">

        <input name="startNumber" class="startNumber" size="8"/>

        <input name="etappe" class="etappe" size="8"/>
        <html:image styleClass="time_button" src="/images/go_button.gif" alt="submit"/>

    </html:form>
</div>