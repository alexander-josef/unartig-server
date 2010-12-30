<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img styleClass="leftalign" src="/images/lang_spec_images/sidebar-title-news.gif"/>
<%--<div class="sidebarElement">
    <h3 class="sidebarContent">Polyball on sale!</h3>
    <p class="sidebarContent"><span>We reduced the prices of the Polyball pictures from the years 2003-2005.</span></p>
    <p class="sidebarContent"><html:link href="http://www.unartig.ch/overview/100/Polyball/show.html">
        <img src="/images/button/sales-en.gif" alt="Ausverkauf"/>
    </html:link>
    </p>
</div>--%>
<div class="sidebarElement">
    <h3 class="sidebarContent">New products!</h3>
    <p class="sidebarContent"><span>You can also download your pictures as digital files.</span></p>
    <p class="sidebarContent"><html:link action="/newProduct-en" onclick="PriceList=window.open('/newProduct-en.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600px,height=600px,left=200px,top=100px'); PriceList.focus(); return false;">
        <img src="/images/button/new-products-en.gif" alt="neue Produkte"/>
    </html:link>
    </p>
</div>

<div class="sidebarBotton">
</div>