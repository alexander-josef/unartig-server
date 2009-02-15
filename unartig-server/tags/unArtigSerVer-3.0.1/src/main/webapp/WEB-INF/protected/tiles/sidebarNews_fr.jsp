<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img styleClass="leftalign" src="/images/lang_spec_images/sidebar-title-news.gif"/>
<%--<div class="sidebarElement">
    <h3 class="sidebarContent">Photos de Polyball en solde!</h3>
    <p class="sidebarContent"><span>Les prix des photo de Polyball des années 2003-2005 se sont effondrés.</span></p>
    <p class="sidebarContent"><html:link href="http://www.unartig.ch/overview/100/Polyball/show.html">
        <img src="/images/button/sales-fr.gif" alt="Ausverkauf"/>
    </html:link>
    </p>
</div>--%>
<div class="sidebarElement">
    <h3 class="sidebarContent">Produits nouveaux!</h3>
    <p class="sidebarContent"><span>Dès maintenant vous pouvez télécharcher les photos comme fichier.</span></p>
    <p class="sidebarContent"><html:link action="/newProduct-fr" onclick="PriceList=window.open('/newProduct-fr.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=600px,height=600px,left=200px,top=100px'); PriceList.focus(); return false;">
        <img src="/images/button/new-products-fr.gif" alt="neue Produkte"/>
    </html:link>
    </p>
</div>

<div class="sidebarBotton">
</div>