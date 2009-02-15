<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <fieldset class="forms">
        <table id="register_field">
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.new"  /></p></td>
                <td><input type="password" name="loginPass" class="kontaktfeld" size="25"
                           value="" maxlength="12"/></td>
                <td><p>&nbsp;<bean:message bundle="CONTENT" key="minimum6digits"  /></p></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="password.new.re"  /></p></td>
                <td><input type="password" name="loginPassRe" class="kontaktfeld" size="25"
                           value=""  maxlength="12"/></td>
                <td></td>
            </tr>
        </table>
    </fieldset>
</div>
<div class="content_left496_middle" id="content_left496_last">
    <table class="left_button">
        <tr>
            <td><a href="AccountPers.html"><img src="/images/back_button.gif" alt=""/></a></td>
            <td><p><bean:message bundle="BUTTONS" key="back"  /></p></td>
        </tr>
    </table>
    <table class=right_button>
        <tr>
            <td><p><bean:message bundle="BUTTONS" key="save.changes"  /></p></td>
            <td class=button_image><input type="image" class="submit_button" value="" src="/images/enter.gif"/></td> <!-- link always to href="ShoppingCartConfrimIN.html" -->
        </tr>
    </table>
</div>