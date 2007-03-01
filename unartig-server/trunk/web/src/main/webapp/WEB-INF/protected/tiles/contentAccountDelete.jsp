<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <p><bean:message bundle="CONTENT" key="account.delete.message"  /></p>
</div>
<div class="content_left496_middle">
    <table class="left_button">
        <tr>
            <td><a href="AccountPers.html"><img src="/images/back_button.gif" alt=""/></a></td>
            <td><p><bean:message bundle="BUTTONS" key="back"  /></p></td>
        </tr>
    </table>
    <table class=right_button>
        <tr>
            <td><p><bean:message bundle="BUTTONS" key="account.delete"  /></p></td>
            <td class=button_image><input type="image" class="submit_button" value="" src="images/enter.gif"/></td>
        </tr>
    </table>
</div>

