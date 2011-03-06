<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle" id="content_left496_last">
    <table class="left_button">
        <tr>
            <td><a href="Display.html"><img src="/images/back_button.gif" alt=""/></a></td>
            <td class="button_text"><bean:message bundle="BUTTONS" key="back.to.display"  /></td>
        </tr>
    </table>
    <table class=right_button>
        <tr>
            <td class="button_text"><bean:message bundle="BUTTONS" key="archive.pictures"  /></td>
            <td class=button_image><input type="image" class="submit_button" value="" src="images/enter.gif"/></td>
        </tr>
    </table>
</div>