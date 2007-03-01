<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <table class=left_button>
        <tr>
            <td><input type="checkbox" name="locations" value="sameAddres"></td>
            <td><p><span><bean:message bundle="CONTENT" key="address.equal.bill"  /></span></p></td>
        </tr>
    </table>
</div>
<div class="content_left496_middle">
    <div id="address_bill">
        <fieldset class="forms">
            <h3><bean:message bundle="CONTENT" key="address.mail"  /></h3>
            <table>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="name.last"  /><span class="red">*</span></p></td>
                    <td><input type="text" name="lastNameBill" class="kontaktfeld" size="25"
                               value=""/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="name.first"  /><span class="red">*</span></p></td>
                    <td><input type="text" name="fistNameBill" class="kontaktfeld" size="25"
                               value=""/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="address.first"  /><span class="red">*</span></p></td>
                    <td><input type="text" name="fistAddresBill" class="kontaktfeld" size="25"
                               value=""/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="address.second"  /></p></td>
                    <td><input type="text" name="secondAddresBill" class="kontaktfeld" size="25"
                               value=""/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="cityPLZ"  /><span class="red">*</span></p></td>
                    <td><input type="text" name="plzBill" class="plz_field" size="5" maxlength="5"
                               value=""/>
                        <input type="text" name="cityBill" class="city_field" size="15"
                               value=""/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><p><bean:message bundle="CONTENT" key="country"  /><span class="red">*</span></p></td>
                    <td><select name="countryMail" class="country_select"><option>Schweiz</option><!--plz 4 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.germany"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.france"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.denmark"  /></option><!--plz 4 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.austria"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.italy"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.sweden"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.norway"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.nederlands"  /></option><!--plz 5 stellig-->
                        <option><bean:message bundle="CONTENT" key="country.spain"  /></option><!--plz 4 stellig-->
                    </select>
                    </td>
                    <td></td>
                </tr>
            </table>
        </fieldset>
    </div>

    <p id="below_notes"><span class="red">*</span>&nbsp;<bean:message bundle="CONTENT" key="must.fill"  /></p>
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
            <td class=button_image><input type="image" class="submit_button" value="" src="/images/enter.gif"/></td>
            <!-- link always to href="ShoppingCartConfrimIN.html" -->
        </tr>
    </table>
</div>