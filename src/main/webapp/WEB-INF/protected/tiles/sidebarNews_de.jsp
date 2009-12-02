<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img styleClass="leftalign" src="/images/lang_spec_images/sidebar-title-news.gif"/>
<%--<div class="sidebarElement">
    <h3 class="sidebarContent">Polyball Aktion!</h3>
    <p class="sidebarContent"><span>Ab sofort können Sie die Fotos des Polyballs 2003-2005 zu stark reduzierten Preisen beziehen.</span></p>
    <p class="sidebarContent"><html:link href="http://www.unartig.ch/overview/100/Polyball/show.html">
        <img src="/images/button/sales-de.gif" alt="Ausverkauf"/>
    </html:link>
    </p>
</div>--%>
<div class="sidebarElement">
    <h3 class="sidebarContent">Neue Produkte!</h3>
    <p class="sidebarContent"><span>Ab sofort können Sie digitale Bilddaten bestellen.</span></p>
    <p class="sidebarContent"><html:link action="/newProduct-de" onclick="PriceList=window.open('/newProduct-de.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600px,height=600px,left=200px,top=100px'); PriceList.focus(); return false;">
        <img src="/images/button/new-products-de.gif" alt="neue Produkte"/>
    </html:link>
    </p>
</div>

<div class="sidebarBotton">
</div>