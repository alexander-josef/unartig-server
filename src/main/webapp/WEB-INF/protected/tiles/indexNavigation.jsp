<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="indexNavEntries" type="java.util.List" scope="request"/>
<html:xhtml/>

<ul class="indexnav leftalign">
    <c:forEach var="indexEntry" items="${indexNavEntries}">
        <li class="index">
            <html:link href="${indexEntry[0]}">${indexEntry[1]}</html:link>
        </li>
    </c:forEach>
</ul>