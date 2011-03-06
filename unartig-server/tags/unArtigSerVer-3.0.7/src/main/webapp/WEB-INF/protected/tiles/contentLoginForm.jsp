<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="contentN rightalign padding19bottom bottomBorder">
    <img src="/images/title_login.gif" alt="login"/>

    <p id="head_coment">
        <bean:message bundle="CONTENT" key="login.coment.head"/>
    </p>
</div>

<div class="contentN rightalign padding19both bottomBorder">

    <form action="/j_security_check" method="POST">

        <fieldset class="forms">
            <table>
                <tr>
                    <td><p>
                        <bean:message bundle="CONTENT" key="email.your"/>
                    </p></td>
                    <td>
                        <input type="text" name="j_username" value="<%=request.getRemoteUser()%>"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <p>
                            <bean:message bundle="CONTENT" key="password"/>
                        </p>
                    </td>
                    <td>
                        <input type="password" name="j_password">
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>
                            <bean:message bundle="CONTENT" key="rememberme"/>
                        </p>
                    </td>
                    <td>
                        <input type="checkbox" name="j_rememberme" value="true">
                    </td>
                    <td>
                        <input type="image" class="leftalign" src="/images/button/login.gif" alt="login"/>
                    </td>
                </tr>

            </table>
        </fieldset>
    </form>


    <p id="below_notes">
        <a href="/Login.html">
            <bean:message bundle="CONTENT" key="password.forgotten"/>
        </a>

    </p>
</div>

<div class="contentN rightalign padding19both bottomBorder">

    <p class="pad15Right"><span>Registrieren Sie sich jetzt</span><br/><br/></p>
    <fieldset class="forms">
        <table>
            <tr>
                <td><p>
                    <bean:message bundle="CONTENT" key="email.your"/>
                </p></td>
                <td><input type="text" name="email" class="txt" size="25"
                           value=""/></td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <p>
                        <bean:message bundle="CONTENT" key="password"/>
                    </p>
                </td>
                <td>
                    <input type="password" name="loginPass" class="txt" size="25" value=""/>

                </td>
                <td></td>
            </tr>
            <tr>
                <td>
                    <p>
                        <bean:message bundle="CONTENT" key="password.re"/>
                    </p>
                </td>
                <td>
                    <input type="password" name="loginPassRepeat" class="txt" size="25" value=""/>
                </td>
                <td>
                    <a href="/admin/over.html">
                        <html:image bundle="IMAGES" srcKey="btn.register.src" altKey="btn.register.alt"/>
                    </a>

                </td>
            </tr>

        </table>
    </fieldset>

    <p class="pad15Right"><br/>
        <bean:message bundle="CONTENT" key="register.comment"/>
        <br/></p>
</div>
