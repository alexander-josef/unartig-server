<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.address.src" altKey="title.address.alt"/>
    <p id="head_coment">
        <bean:message bundle="CONTENT" key="fill.complete"/>
    </p>
</div>

<html:form action="/checkOutStoreAddress" acceptCharset="utf-8">
    <html:hidden property="page" value="1"/>

    <logic:messagesPresent>
        <div class="contentW rightalign padding19top">
            <ul>
                <html:messages id="error" bundle="ERROR">
                    <li><p class="errorstyle">
                        <bean:write name="error" bundle="ERROR"/>
                    </p></li>
                </html:messages>
            </ul>
        </div>
    </logic:messagesPresent>

    <div class="contentW rightalign padding19both bottomBorder">
        <div id="address_bill">
            <fieldset class="forms">
                <h3>
                    <bean:message bundle="CONTENT" key="address"/>
                </h3>
                <table>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="gender"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:select property="gender" styleClass="country_select">
                                <html:option value="f">
                                    <bean:message bundle="CONTENT" key="gender.female"/>
                                </html:option>
                                <html:option value="m">
                                    <bean:message bundle="CONTENT" key="gender.male"/>
                                </html:option>
                            </html:select>
                        </td>
                        <td></td>
                    </tr>


                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="name.first"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:text property="firstName" styleClass="kontaktfeld" errorStyleClass="inputError" size="25"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="name.last"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:text property="lastName" styleClass="kontaktfeld" errorStyleClass="inputError" size="25"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="address.first"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:text property="addr1" styleClass="kontaktfeld" errorStyleClass="inputError" size="25"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="address.second"/>
                        </p></td>
                        <td>
                            <html:text property="addr2" styleClass="kontaktfeld" errorStyleClass="inputError" size="25"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="cityPLZ"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:text property="zipCode" styleClass="plz_field" errorStyleClass="inputError" size="5" maxlength="5"/>
                            <html:text property="city" styleClass="city_field" errorStyleClass="inputError" size="15"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><p>
                            <bean:message bundle="CONTENT" key="country"/>
                            <span class="red">*</span></p></td>
                        <td>
                            <html:select property="country" styleClass="country_select">
                                <html:option value="CHE">
                                    <bean:message bundle="CONTENT" key="country.switzerland"/>
                                </html:option>
                                <!--plz 4 stellig-->
                                <html:option value="DEU">
                                    <bean:message bundle="CONTENT" key="country.germany"/>
                                </html:option>
                                <!--plz 5 stellig-->
                                <%--
                                                                <html:option value="FRA"><bean:message bundle="CONTENT" key="country.france"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="AUT"><bean:message bundle="CONTENT" key="country.austria"/>
                                                                </html:option>
                                                                <!--plz 4 stellig-->
                                                                <html:option value="ITA"><bean:message bundle="CONTENT" key="country.italy"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="ESP"><bean:message bundle="CONTENT" key="country.spain"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="NLD"><bean:message bundle="CONTENT" key="country.nederlands"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="NOR"><bean:message bundle="CONTENT" key="country.norway"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="SWE"><bean:message bundle="CONTENT" key="country.sweden"/>
                                                                </html:option>
                                                                <!--plz 5 stellig-->
                                                                <html:option value="DNK"><bean:message bundle="CONTENT" key="country.denmark"/>
                                                                </html:option>
                                --%>
                                <!--plz 4 stellig-->
                            </html:select>
                        </td>
                        <td></td>
                    </tr>

                </table>
            </fieldset>

        </div>

<%--        <p id="below_notes">
            <bean:message bundle="CONTENT" key="shipping.restrictions"/>
        </p>--%>
    </div>
    <!--address last-->
    <div class="contentW rightalign padding19top">
        <div class="leftalign">
            <html:link action="/startCheckOut">
                <html:img bundle="IMAGES" srcKey="btn.back.src" altKey="btn.back.alt"/>
            </html:link>
        </div>


        <div class="rightalign">
            <%--<html:link href="javascript:document.forms[0].submit()">--%>
                <html:image bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>
            <%--</html:link>--%>
        </div>
    </div>
    <div class="contentW rightalign padding19bottom"><p class="rightalign"><bean:message bundle="CONTENT" key="order.conf.later"/></p>
    </div>
</html:form>
