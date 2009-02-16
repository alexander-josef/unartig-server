<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <p><bean:message bundle="CONTENT" key="account.settings.info"  /></p>
</div>

<div class="content_left496_middle">
    <h3><bean:message bundle="CONTENT" key="language.select"  /></h3>
    <table class=left_button>
        <tr>
            <td><input type="radio" name="languange" value="sameAddres"></td>
            <td><p><span class="space"><bean:message bundle="CONTENT" key="language.german"  /></span></p></td>
        </tr>
    </table>
    <table class=left_button>
        <tr>
            <td><input type="radio" name="languange" value="sameAddres"></td>
            <td><p><span class="space"><bean:message bundle="CONTENT" key="langauge.english"  /></span></p></td>
        </tr>
    </table>
    <table class=left_button>
        <tr>
            <td><input type="radio" name="languange" value="sameAddres"></td>
            <td><p><span class="space"><bean:message bundle="CONTENT" key="language.french"  /></span></p></td>
        </tr>
    </table>
</div>

<div class="content_left496_middle">
    <h3><bean:message bundle="CONTENT" key="pictures.per.page"  /></h3>

    <p><bean:message bundle="CONTENT" key="pictures.per.page.info"  /></p><br/>
    <ul id="settingPPP">
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP10_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP15_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP20_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP25_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP30_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP35_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP40_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP45_passive.gif"/></li>
        <li><img src="/images/SettingsPPP_spacer.gif" alt=""/></li>
        <li><input type="image" name="PPP" value="" src="images/SettingsPPP50_passive.gif"/></li>
    </ul>
</div>
<div class="content_left496_middle">
    <table class=right_button>
        <tr>
            <td><p><bean:message bundle="CONTENT" key="settings.safe"  /></p></td>
            <td class=button_image><input type="image" class="submit_button" value="" src="images/enter.gif"/></td> <!-- link always to href="ShoppingCartConfrimIN.html" -->
        </tr>
    </table>
</div>