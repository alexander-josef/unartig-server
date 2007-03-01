<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img bundle="IMAGES" srcKey="title.menu.infoLogon.src" altKey="title.menu.infoLogon.alt" />
<ul class="side_menu" id="side_menu_about">
    <li>
        <ul>
            <li><a href="/Services.html"><bean:message bundle="CONTENT" key="menu.personalAccount"  /></a></li>
            <li><a href="/Photographers.html"><bean:message bundle="CONTENT" key="menu.photographersAccount"  /></a></li>
            <li><a href="/Services.html"><bean:message bundle="CONTENT" key="menu.privacy"  /></a></li>
        </ul>
    </li>

</ul>
