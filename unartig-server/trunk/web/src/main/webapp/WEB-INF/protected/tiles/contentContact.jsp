<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>

<div class="contentN rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.contact.src" altKey="title.contact.alt"/>
</div>

<logic:messagesPresent message="true">
    <div class="contentN rightalign padding19top">
        <p class="errorstyle"><span></span></p>
        <ul>
            <html:messages id="msg" message="true" bundle="ERROR">
                <li><p class="errorstyle"><bean:write name="msg"/></p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>

<logic:messagesPresent>
    <div class="contentN rightalign padding19">
        <p class="errorstyle"><span></span></p>
        <ul>
            <html:messages id="validationError" bundle="ERROR">
                <li><p class="errorstyle"><bean:write name="validationError"/></p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>

<div class="contentN rightalign padding19both bottomBorder">
    <p><span>Studio unArtig</span><br/>
        unartig AG<br/>

        Clausiusstrasse 34, 8006 Zürich<br/>
        Tel +41 44 251 0009, Fax +41 44 251 05 02<br/>
        <img src="/images/Email_unartig.gif" alt="E-Mail unartig Kundendienst"/>
    </p>

</div>

<html:form action="/sendCustomerServiceMessage" method="post">

    <div class="contentN rightalign padding19both bottomBorder">
        <p>
                <bean:message bundle="CONTENT" key="contact.intro"/>

        <fieldset class="forms">
            <table id="register_field">
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="name.contact"/></p></td>
                    <td><html:text property="sender" styleClass="kontaktfeld" size="28"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="email.your"/></p></td>
                    <td>
                        <html:text property="fromAddress" styleClass="kontaktfeld" size="28"/>
                            <%--<html:text property="fromAddress" styleClass="kontaktfeld" size="30"/>--%>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="telNo.your"/></p></td>
                    <td>
                        <html:text property="contactPhone" styleClass="kontaktfeld" size="28"/>
                            <%--<html:text property="fromAddress" styleClass="kontaktfeld" size="30"/>--%>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <p><bean:message bundle="CONTENT" key="concerning"/></p>
                    </td>
                    <td>
                        <html:select property="subject">
                            <html:option styleClass="kontaktfeld" value="allgemein">
                                <bean:message bundle="CONTENT" key="contact.issue.general"/>
                            </html:option>
                            <html:option styleClass="kontaktfeld" value="bestellung">
                                <bean:message bundle="CONTENT" key="contact.issue.search"/>
                            </html:option>
                            <html:option styleClass="kontaktfeld" value="anfrage">
                                <bean:message bundle="CONTENT" key="contact.issue.book.unartig"/>
                            </html:option>
                        </html:select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td class="textarea_label"><p><bean:message bundle="CONTENT" key="contact.message"/></p></td>
                    <td>
                        <html:textarea property="message" styleClass="kontaktfeld" rows="8" cols="50"></html:textarea>
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>

    </div>

    <div class="contentN rightalign padding19both">
        <div class="rightalign">
                <%--<html:link href="javascript:document.forms[1].submit()">--%>
                <%--<html:image bundle="IMAGES" srcKey="contact.message.send.src" altKey="contact.message.send.alt"/>--%>
                <%--</html:link>--%>

            <html:image bundle="IMAGES" srcKey="contact.message.send.src" altKey="contact.message.send.alt" value=""/>
        </div>
    </div>
</html:form>

