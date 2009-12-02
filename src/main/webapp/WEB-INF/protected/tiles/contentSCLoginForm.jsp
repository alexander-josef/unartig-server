<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.sc.login.src" altKey="title.sc.login.alt" />
</div>

<html:form action="/checkOutAddress">
    <html:hidden property="page" value="0"/>

    <logic:messagesPresent>
<div class="contentW rightalign padding19top">
            <ul>
                <html:messages id="error">
                    <li><p class="errorstyle"><bean:write name="error"/></p></li>
                </html:messages>
            </ul>
        </div>
    </logic:messagesPresent>

<div class="contentW rightalign padding19both bottomBorder">
        <p><span><bean:message bundle="CONTENT" key="email.your.enter"/></span></p><br/>
        <fieldset class="forms">
            <table id="register_field">
                <tr>
                    <td>
                        <p><bean:message bundle="CONTENT" key="email.your"/></p>
                    </td>
                    <td>
                        <html:text property="email" styleClass="kontaktfeld" errorStyleClass="inputError" size="25"/>
                    </td>
                    <td></td>
                </tr>
                    <%--                <tr class="radiobutton">
                        <td></td>
                        <td>
                            <html:radio property="newCustomer" value="true"/>

                            <p>&nbsp;
                                <span><bean:message bundle="CONTENT" key="account.nohave"/></span>&nbsp;
                                <bean:message bundle="CONTENT" key="account.nohave.extension"/>
                            </p>
                        </td>
                        <td></td>
                    </tr>
                    <tr class="radiobutton">
                        <td></td>
                        <td>
                            <html:radio property="newCustomer" value="false"/>

                            <p>&nbsp;<span><bean:message bundle="CONTENT" key="account.have"/></span>&nbsp;
                                <bean:message bundle="CONTENT" key="account.have.extension"/></p>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p><bean:message bundle="CONTENT" key="password"/></p></td>
                        <td>
                            <html:password styleClass="kontaktfeld" size="25" property="password"/>
                        </td>
                        <td></td>
                    </tr>--%>

            </table>
        </fieldset>

            <%--        <p id="below_notes">
                <a href="ShoppingCartLoginNoLoginPass.html"><bean:message bundle="CONTENT" key="password.forgotten"/></a>
            </p>--%>
        <p id="below_notes">
            <bean:message bundle="CONTENT" key="email.why"/>
        </p>
    </div>

<div class="contentW rightalign padding19top">
        <div class="leftalign">
            <html:link action="/toShoppingCart">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>

        <div class="rightalign ">
            <%--<html:link href="javascript:document.forms[0].submit()">--%>
                <html:image bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>
            <%--</html:link>--%>
        </div>
    </div>
    <div class="contentW rightalign padding19bottom"><p class="rightalign"><bean:message bundle="CONTENT" key="order.conf.later"/></p>
    </div>
</html:form>