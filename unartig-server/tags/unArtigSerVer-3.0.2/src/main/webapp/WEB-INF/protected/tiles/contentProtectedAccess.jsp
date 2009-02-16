<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<div id=protection>
    <div>
        <p><bean:message bundle="CONTENT" key="protection.info"/></p>

        <html:form action="/private/authorize">
            <html:text property="accessKey" size="10" maxlength="8" value=""/>
            <html:image src="/images/enter.gif" alt="go!"/>
        </html:form>
    </div>
</div>
