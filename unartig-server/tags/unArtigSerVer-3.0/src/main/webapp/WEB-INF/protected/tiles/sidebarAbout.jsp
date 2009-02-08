<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img bundle="IMAGES" srcKey="title.menu.about.src" altKey="title.menu.about.alt" />
<ul class="side_menu" id="side_menu_about">
    <li>
        <ul><li><a href="/Services.html"><bean:message bundle="CONTENT" key="services"  /></a></li>
            <li><bean:message bundle="CONTENT" key="photographers"  /></li>
            <li><a href="/Firm.html"><bean:message bundle="CONTENT" key="company"  /></a></li>
            <li><a href="/Partners.html"><bean:message bundle="CONTENT" key="partner"  /></a></li>
        </ul>
    </li>

</ul>
