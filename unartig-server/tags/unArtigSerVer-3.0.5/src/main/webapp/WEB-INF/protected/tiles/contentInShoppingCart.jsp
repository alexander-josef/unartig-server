<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<jsp:useBean id="photo" type="ch.unartig.studioserver.model.Photo" scope="request"/>
<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.sc.in.src" altKey="title.sc.in.alt" />
    <p id="head_coment"><bean:message bundle="CONTENT" key="in.shoppingcart"  /></p>
</div>

<div class="contentWplus rightalign padding19both bottomBorder">
    <ul class="album">
        <!--in each 'li' change class to either 'imageholder-landscape' or 'imageholder_portrait' depending on format-->
        <li class="albumslide">
            <div class="imageholder_${photo.orientationSuffix}">
                <html:img src="${photo.thumbnailUrl}" alt="${photo.displayTitle}" title="${photo.displayTitle}"/>
            </div>

            <div class="archive_slide_info"><!--<bean:message bundle="CONTENT" key="cart.incart"/>--></div>
        </li>
    </ul>
</div>

<div class="contentW rightalign padding19both bottomBorder">
    <h3><bean:message bundle="CONTENT" key="order"/></h3>

    <p><bean:message bundle="CONTENT" key="order.choose.more"/></p>
</div>

<div class="contentW rightalign padding19both">
    <div class="leftalign">
        <html:link action="${albumBean.album.actionString}">
            <html:img bundle="IMAGES" srcKey="btn.to.fotoselection.src" altKey="btn.to.fotoselection.alt"/>
        </html:link>
    </div>

    <div class="rightalign">
        <html:link action="/toShoppingCart">
            <html:img bundle="IMAGES" srcKey="btn.to.shoppingcart.src" altKey="btn.to.shoppingcart.alt"/>
        </html:link>
    </div>
</div>