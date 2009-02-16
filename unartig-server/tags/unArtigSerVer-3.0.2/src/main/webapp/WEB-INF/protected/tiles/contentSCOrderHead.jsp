<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>


<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.summary.src" altKey="title.summary.alt"/>
    <p id="head_coment"><bean:message bundle="CONTENT" key="order.doublecheck"/></p>
</div>
<logic:messagesPresent message="true">
<div class="contentW rightalign padding19both bottomBorder">
        <ul>
            <html:messages id="error" bundle="ERROR" message="true">
                <li><p class="errorstyle"><bean:write name="error" bundle="ERROR"/></p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>
<logic:messagesPresent>
<div class="contentW rightalign padding19both bottomBorder">
        <ul>
            <html:messages id="error" bundle="ERROR">
                <li><p class="errorstyle"><bean:write name="error" bundle="ERROR"/></p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>
<div class="contentW rightalign padding19both bottomBorder">
    <html:form action="/checkOutConfirm">
        <html:hidden property="page" value="2"/>
        <table class="right">
            <tr>
                <td>
                    <p><bean:message bundle="BUTTONS" key="agb.read"/> &nbsp;</p>
                </td>
                <td>
                    <html:checkbox errorStyleClass="inputError" property="acceptTermsCondition" styleClass="admin_button" value="true"/>
                </td>
            </tr>
        </table>
        <br/><br/>
        <div class="leftalign">
            <html:link action="/checkOutStoreAddress">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>
        <div class="rightalign">
            <%--<html:link action="/checkOutConfirm">--%>
            <html:image bundle="IMAGES" srcKey="btn.order.src" altKey="btn.order.alt"/>
                <%--</html:link>--%>
                <%--             href="ShoppingCartConfirmIN.html" if logged in--%>
        </div>
    </html:form>
</div>