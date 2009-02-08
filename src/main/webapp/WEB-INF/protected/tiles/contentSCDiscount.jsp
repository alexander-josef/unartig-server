<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <bean:message bundle="CONTENT" key="discount.code"  /><br/>


<table class="left_button">
        <tr>
            <td><input id="discount_pass_input" type="password" name="discountPass" size="25" maxlength="12"></td>

            </tr>
    </table>
<table class=right_button>
            <tr>
                <td><p><bean:message bundle="BUTTONS" key="recalculate"  /></p></td>
                <td class=button_image><input type="image" class="submit_button" value="" src="images/reload_button.gif"/></td>
            </tr>
        </table>
</div>