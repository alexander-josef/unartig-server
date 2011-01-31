<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <fieldset class="forms">
        <table id="register_field">
            <tr>
                <td><p><bean:message bundle="CONTENT" key="email.your"  /></p></td>
                <td><input type="text" name="email" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password"  /></p></td>
                <td><input type="password" name="loginPass" class="kontaktfeld" size="20"
                           value=""/><input type="image" class="submit_button" value="" src="images/enter.gif"/>
                </td>
                <td></td>
            </tr>
        </table>
    </fieldset>
</div>