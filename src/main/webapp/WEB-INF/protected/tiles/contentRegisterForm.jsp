<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <fieldset class="forms">
        <table id="register_field">
            <tr>
                <td><p><bean:message bundle="CONTENT" key="username.your"  /></p></td>
                <td><input type="text" name="username" class="kontaktfeld" size="25"
                           value=""/></td>
                <td><p>&nbsp;<bean:message bundle="CONTENT" key="username.onewordonly"  /></p></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="email.your"  /></p></td>
                <td><input type="text" name="email" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.your"  /></p></td>
                <td><input type="password" name="loginPass" class="kontaktfeld" size="25"
                           value=""/></td>
                <td><p>&nbsp;<bean:message bundle="CONTENT" key="minimum6digits"  /></p></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.re"  /></p></td>
                <td><input type="password" name="loginPassRe" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
        </table>


    </fieldset>

</div>