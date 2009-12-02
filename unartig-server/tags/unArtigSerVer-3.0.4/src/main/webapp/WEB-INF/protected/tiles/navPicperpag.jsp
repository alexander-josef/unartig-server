<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<table class="numberofspages"><tr><td><p><bean:message bundle="CONTENT" key="pictures.per.page"  /></p></td>
    <td>
        <select name="numbersofpages" class="pagesno">
            <option value="19">15</option>
            <option value="20">10</option>
            <option value="21">20</option>
            <option value="22">25</option>
        </select>
    </td>
</tr>
</table>