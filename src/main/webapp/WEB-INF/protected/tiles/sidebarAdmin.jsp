<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<img src="/images/lang_spec_images/title_menu_admin.gif" alt="admininstration"/>
<ul class="side_menu" id="side_menu_about">
    <li>
        <ul>
            <li><html:link action="/admin/over"><bean:message bundle="CONTENT" key="database"/></html:link></li>
            <li><html:link action="/admin/levels"><bean:message bundle="CONTENT" key="newlevel"/></html:link></li>
            <li><html:link action="/admin/uploadAlbum"><bean:message bundle="CONTENT" key="upload"/></html:link></li>
            <li><html:link action="/admin/orderAdmin"><bean:message bundle="CONTENT" key="orderAdmin"/></html:link></li>
            <li><html:link action="/admin/testTest">TEST TEST TEST</html:link></li>
            <li>&nbsp;</li>
            <li>&nbsp;</li>
            <li>&nbsp;</li>
            <li><html:link action="/admin/reportProductSales">Report Product Sales</html:link></li>
        </ul>
    </li>
</ul>