<%@ page import="ch.unartig.util.DebugUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<%
    DebugUtils.debugActionMessage(request);
%>

<logic:messagesPresent message="true">
    <div class="contentN rightalign padding19top">
        <p class="errorstyle"><span></span></p>
        <ul>
            <html:messages id="msg" message="true" bundle="ERROR">
                <li><p class="errorstyle">
                    <bean:write name="msg"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.confirm.order.src" altKey="title.confirm.order.alt"/>

<%--
    <br>TODO im Falle eines Fehlers den Link zum Shoppingcart anbieten
--%>
    <p id="head_coment">
        <bean:message bundle="CONTENT" key="order.thanks"/>
    </p>
</div>

<div class="contentW rightalign padding19both bottomBorder">
    <h3><bean:message bundle="CONTENT" key="confirmation"/></h3>

    <p><bean:message bundle="CONTENT" key="confirmation.info"/></p>
</div>

<div class="contentW rightalign padding19both">
<html:form action="/accountOptOut">
 <%--   <table class="left_button">
        <tr>
            <td><html:checkbox property="noUnartigAccount" value="true"/></td>
            <td><p class="checkboxtext">&nbsp;<bean:message bundle="CONTENT" key="account.no"/></p></td>
        </tr>
        <tr>
            <td><html:checkbox property="noCoplaEmails" value="true"/></td>
            <td><p class="checkboxtext">&nbsp;<bean:message bundle="CONTENT" key="coplaEmails.no"/></p></td>
        </tr>
    </table>
--%>    </div>
    <div class="contentW rightalign padding19top">

    <div class="rightalign ">
        <html:link href="index.html">
            <html:img bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>
        </html:link>
    </div>
</html:form>
</div>
