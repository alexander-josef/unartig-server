<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content" id="content612">
    <div id="content_left496_head">
        <img class="content_title" src="/images/lang_spec_images/title_register_de.gif" alt="">
    </div>
    <div class="content_left496_middle">
        <p class="error_message"><span><bean:message bundle="CONTENT" key="error.message"  /></span><br/><span>"<!-- insert occupied username -->"&nbsp;</span><bean:message bundle="CONTENT" key="error.username.used"  /></p>
    </div>
    <div class="content_left496_middle">
        <fieldset class="forms">
            <table id="register_field">
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="username.your"  /></p></td>
                    <td><input type="text" name="username" class="kontaktfeld_error" size="25"
                                                       value="kleber"/></td> <!--chenge class to "kontaktfeld_error" bei Fehlermeldung -->
                    <td> <p>&nbsp;<bean:message bundle="CONTENT" key="username.onewordonly"  /></p></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="email.your"  /></p></td>
                    <td><input type="text" name="email" class="kontaktfeld" size="25"
                                                       value="urabn@unartig.ch"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="password"  /></p></td>
                    <td><input type="hidden" name="loginPass" class="kontaktfeld" size="25"
                                                       value="******"/></td>
                    <td><p>&nbsp;<bean:message bundle="CONTENT" key="minimum6digits"  /></p></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="password.re"  /></p></td>
                    <td><input type="hidden" name="loginPassRe" class="kontaktfeld" size="25"
                                                       value="******"/></td>
                    <td></td>
                </tr>
            </table>



        </fieldset>

    </div>

    <div class="content_left496_middle" id="content_left496_last">
        <table class=right_button>
            <tr>
                <td><p><bean:message bundle="CONTENT" key="register"  /></p></td>
                <td class=button_image><input type="image" class="submit_button" value="" src="images/enter.gif"/></td>
            </tr>
        </table>
    </div>
</div>
