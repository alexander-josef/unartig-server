<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <table class=left_button>
        <tr>
            <td><input type="checkbox" name="locations" value="billing"></td>
            <td><p><span><bean:message bundle="CONTENT" key="bill"  /></span></p></td>
        </tr>

    </table>
</div>
<div class="content_left496_middle">
    <table class=left_button>
        <tr>
            <td><input type="checkbox" name="locations" value="billing"></td>
            <td><p><span><bean:message bundle="CONTENT" key="credit.card"  /></span></p></td>
        </tr>
    </table>
    <div id="credit_info">
        <fieldset class="forms">
            <table>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="credit.select"  /></p></td>
                    <td><select id="card_select"><option>Visa</option>
                        <option>Mastercard</option>
                    </select></td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="credit.number"  /></p></td>
                    <td><input type="text" name="creditNumber" class="kontaktfeld" size="25" maxlength="16"
                               value=""/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="credit.safety"  /></p></td>
                    <td><input type="text" name="creditNumber" class="kontaktfeld" size="25" maxlength="3"
                               value=""/>
                    </td>
                    <td><span><a href="">&nbsp;?</a></span></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="credit.validity"  />Gültig bis (M / J)</p></td>
                    <td><select name="creditMonth" id="month_select"><option>01</option>
                        <option>02</option>
                        <option>03</option>
                        <option>04</option>
                        <option>05</option>
                        <option>06</option>
                        <option>07</option>
                        <option>08</option>
                        <option>09</option>
                        <option>10</option>
                        <option>11</option>
                        <option>12</option>
                    </select>
                        <select name="creditYear" id="year_select"><option>2005</option>
                        <option>2006</option>
                        <option>2007</option>
                        <option>2008</option>
                        <option>2009</option>
                        <option>2010</option>
                        <option>2011</option>
                        <option>2012</option>
                    </select>
                    </td>
                    <td></td>
                </tr>

                <tr>
                    <td><p><bean:message bundle="CONTENT" key="credit.owner"  /></p></td>
                    <td><input type="text" name="creditOwner" class="plz_field" size="25"
                               value=""/>

                    </td>
                    <td></td>
                </tr>

            </table>
        </fieldset>
    </div>
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