<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <fieldset class="forms">
        <table id="register_field">
            <tr>
                <td><p><bean:message bundle="CONTENT" key="email.new"  /></p></td>
                <td><input type="text" name="email" class="kontaktfeld" size="25"
                           value=""/></td>
                <td></td>
            </tr>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="email.new.re"  /></p></td>
                <td><input type="text" name="emailRe" class="kontaktfeld" size="25"
                           value=""/></td>
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
            <td class=button_image><input type="image" class="submit_button" value="" src="/images/enter.gif"/></td> <!-- link always to href="ShoppingCartConfrimIN.do" -->
        </tr>
    </table>
</div>