<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img bundle="IMAGES" srcKey="title.menu.account.src" altKey="title.menu.account.alt" />
<ul class="side_menu">
    <li>
        <ul><li><a href="AccountPers.html"><bean:message bundle="CONTENT" key="particulars"  /></a></li>
            <li><a href="AccountSet.html"><bean:message bundle="CONTENT" key="settings"  /></a></li>
            <li><a href="AccountAlbum.html"><bean:message bundle="CONTENT" key="myalbum"  /></a></li>
            <!--<li><a href="AccountHistory.html">Bestellungen</a></li>-->
        </ul>
    </li>

</ul>


