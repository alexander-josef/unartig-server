<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img bundle="IMAGES" srcKey="title.menu.sc.src" altKey="title.menu.sc.alt" />
<ul class="side_menu" id="shopping_cart_menu">
    <li>
        <ul>
            <li><html:link styleClass="link_visited" action="/startCheckOut"><bean:message bundle="CONTENT" key="sidebar.sc.register"  /></html:link></li>
            <li><a class="link_active"><bean:message bundle="CONTENT" key="sidebar.sc.address"  /></a></li>
            <li><a class="link_unvisited"><bean:message bundle="CONTENT" key="sidebar.sc.billing"  /></a></li>
            <li><a class="link_unvisited"><bean:message bundle="CONTENT" key="sidebar.sc.confirm"  /></a></li>
        </ul>
    </li>

</ul>