<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <fieldset class="forms">
        <table id="register_field">
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.new"  /></p></td>
                <td><input type="password" name="loginPass" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.new.re"  /></p></td>
                <td><input type="password" name="loginPassRE" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
        </table>
    </fieldset>

</div>