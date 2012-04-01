<%@ page import="ch.unartig.studioserver.Registry" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="shoppingCart" type="ch.unartig.studioserver.beans.ShoppingCart" scope="session"/>
<%-- don't use this line ... will result in a jsp exception in case the album bean ist not in the session:
<jsp:useBean id="albumBean" type="ch.unartig.studioserver.beans.AbstractAlbumBean" scope="session"/>
--%>


<%--<jsp:useBean id="products3" type="java.util.List" scope="request"/>--%>
<%--<jsp:useBean id="products5" type="java.util.List" scope="request"/>--%>
<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img bundle="IMAGES" srcKey="title.sc.overview.src" altKey="title.sc.overview.alt"/>
</div>

<logic:messagesPresent message="true">
    <div class="contentW rightalign padding19both bottomBorder">
        <ul>
            <html:messages id="msg" message="true" bundle="ERROR">
                <li><p class="errorstyle">
                    <bean:write name="msg"/>
                </p></li>
            </html:messages>
        </ul>
    </div>
</logic:messagesPresent>
<%--

todo: beim aendern eines photos weit unten in der shoppingcartliste wird immer wieder ganz hinauf gesprungen. anchor links (name) benutzen um wieder zum editierten bild zu springen
--%>

<html:form action="/updateShoppingCart">

<div class="contentW rightalign padding19top">
    <c:if test="${shoppingCart.backToAlbumParams!=null}">

        <div class="leftalign">
                <%--<html:link action="${shoppingCart.backToAlbumLink}">--%>
            <html:link action="/album/${albumBean.album.genericLevelId}/${albumBean.album.longTitle}"
                       name="shoppingCart" property="backToAlbumParams">
                <html:img bundle="IMAGES" srcKey="btn.select.more.src" altKey="btn.select.more.alt"/>
            </html:link>
        </div>
    </c:if>

    <div class="rightalign">
            <%-- todo linken reicht nicht !! form daten muessen auch geschickt werden  insert javascript --%>
        <!--<td class="button_text"><span onclick="postForm('checkOut',this)" ><bean:message bundle="BUTTONS" key="to.checkout"/></span></td>-->
        <html:link action="/startCheckOut" onclick="postSimpleForm('checkOut')">
            <html:img bundle="IMAGES" srcKey="btn.checkout.src" altKey="btn.checkout.alt"/>
        </html:link>
    </div>

</div>
<div class="contentW rightalign padding19bottom bottomBorder"><p class="rightalign">
    <bean:message bundle="CONTENT" key="order.ssl.proceed"/>
</p>
</div>
<div class="contentW rightalign padding19bottom bottomBorder">
    <table class="sc_table_description">
        <tr>
            <td class="sc_format_firstcol">
                <p><span><bean:message bundle="CONTENT" key="order.article"/></span></p>
            </td>
            <td class="sc_format_secondcol">
                <p><span><bean:message bundle="CONTENT" key="order.format"/></span></p>
            </td>
            <td class="sc_format_thirdcol">
                <p><span><bean:message bundle="CONTENT" key="order.amount"/></span></p>
            </td>
            <td>
<%--
                <p class="rightalign"><span><bean:message bundle="CONTENT" key="order.price"/></span>&nbsp;(Euro)
--%>
                <p class="rightalign"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                </p>
            </td>
            <td>
                <p class="rightalign"><span><bean:message bundle="CONTENT" key="order.price"/></span>&nbsp;(SFr.)
                </p>
            </td>

        </tr>
    </table>
</div>


<logic:iterate name="shoppingCart" property="scItems" id="scItem" indexId="scItemIndex">
    <%-- IF index 0 oder teilbar durch 4: create index photo --%>
    <c:if test="${scItemIndex % 4 == 0}">
        <%-- create page variable 'photo' --%>
        <bean:define id="photo" name="shoppingCart" property="photoMapped(${scItem.photoId})" toScope="page"/>
        <bean:define id="album" name="photo" property="album" toScope="page"/>


        <div class="contentWplus rightalign padding19both bottomBorder">

        <ul class="album">
            <li class="albumslide">
                <!--<div class="imageholder_landscape">-->
                <div class="imageholder_${photo.orientationSuffix}">
                    <!--photoid: ${scItem.photoId}-->
                    <html:img src="${photo.thumbnailUrl}" title="${photo.displayTitle}" alt="${photo.displayTitle}"/>
                </div>

                <div class="album_slide_info_sc">
                        <%--<html:form action="removeFromCart" method="put">--%>
                        <%-- Achtung! sprachen wechsel .... wird bild nochmals geloescht ?? --%>
                        <%--<html:link property="<%=Registry._NAME_ORDERED_PHOTO_ID_PARAM%>" value="${photo.photoId}">--%>
                    <html:link action="/removeFromCart" paramId="<%=Registry._NAME_ORDERED_PHOTO_ID_PARAM%>"
                               paramName="scItem" paramProperty="photoId">
                        <bean:message bundle="BUTTONS" key="delete"/>
                    </html:link>
                </div>
            </li>
        </ul>

    </c:if>
    <%--END IF--%>

    <%--
    loop over sc order items
    --%>
    <!--<div class="sc_order_list">-->
    <fieldset class="forms">
        <table class="sc_order_list rightalign">
            <tr>
                <td class="sc_format_secondcol">
                    <!-- pre-set standard product -->
                    <!-- pre-set scItem.productId to the pre-set product -->
                        <%--
                            todo : currently the product (which includes the price !) gets selected here!! move this logic to business class!!
                        --%>
                    <html:select name="scItem" property="productId" indexed="true" styleClass="sc_format_select"
                                 onchange="postSimpleForm('update')">
                        <%--<c:if test="${photo.album.priceSegment.priceSegmentId == 1}">--%>
                        <%-- 3-er preisliste --%>
                        <html:option value="-1">
                            <bean:message bundle="BUTTONS" key="product.choose"/>
                        </html:option>
                        <html:optionsCollection name="album" property="activeProducts" label="productType.name"
                                                value="productId"/>
                        <%--</c:if>--%>
                        <%--<c:if test="${photo.album.priceSegment.priceSegmentId == 2}">--%>
                        <%-- 5-er preisliste --%>
                        <%--<html:option value="-1">--%>
                        <%--<bean:message bundle="BUTTONS" key="product.choose"/>--%>
                        <%--</html:option>--%>
                        <%--<html:options collection="products5" labelProperty="productName" property="productId"/>--%>
                        <%--</c:if>--%>
                    </html:select>
                    <!--question mark for digital products-->
                    <c:if test="${scItem.product.digitalProduct}">
                        <html:link action="/explainDigital">
                            <html:img src="/images/questionmark.gif" alt="help"
                                      onclick="PriceList=window.open('/explainDigital.html','Preisliste','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400px,height=450px,left=200px,top=100px'); PriceList.focus(); return false;"/>
                        </html:link>
                    </c:if>
                </td>
                <td class="sc_format_thirdcol">
                    <!-- pre-set quantity to one for first item is done in shopping cart business logic-->
                    <c:if test="${scItem.digitalOrderItem}">
                        <html:text name="scItem" property="quantity" indexed="true" size="3" styleClass="txt"
                                   disabled="true" value="1" titleKey="shoppingcart.amount.digital.title"
                                   bundle="CONTENT"/>
                    </c:if>
                    <c:if test="${!scItem.digitalOrderItem}">
                        <html:text name="scItem" property="quantity" indexed="true" size="3" styleClass="txt"/>
                    </c:if>
                </td>
                <td class="sc_format_fourthcol">
                        <%--<p class="rightalign">${scItem.priceMajorUnitsCHF}.${scItem.priceMinorUnitsPartCHF}</p>--%>
                    <p class="rightalign">${scItem.formattedItemPriceCHF}</p>
                </td>
                <td>
<%--
                    <p class="rightalign">${scItem.formattedItemPriceEUR}</p>
--%>
                </td>
            </tr>
        </table>
    </fieldset>

    <!--</div>-->
    <%-- END Loop order items --%>

    <%--IF INDEX modulo 4 = 3   (end of sc-items loop for one photo reached)  --%>
    <c:if test="${scItemIndex % 4 == 3}">


        <!--insert recalculate button (only for js-enabled browsers)-->
        <div class="rightalign padding19top">
                <%--<html:link href="javascript:document.forms[0].submit()" styleId="recalculate">--%>
            <html:img bundle="IMAGES" srcKey="btn.recalculate.src" altKey="btn.recalculate.alt"
                      onclick="postSimpleForm('update')"/>
                <%--</html:link>--%>
        </div>

        </div>
    </c:if>
    <%-- END IF --%>
</logic:iterate>
<%--
Total and subtotal
--%>

<div class="contentW rightalign padding19bottom bottomBorder">

    <table class="sc_table_description">
        <tr>
            <td class="sc_table_description_head">
                <p><span><bean:message bundle="CONTENT" key="price.subtotal"/></span></p>
            </td>
            <td></td>
            <td></td>
            <td><p class="rightalign"><span>&nbsp;</span></p></td>
            <td class="sc_format_fourthcol"><p class="rightalign"><span>SFr.</span></p></td>
<%--
            <td><p class="rightalign"><span>Euro</span></p></td>
--%>
        </tr>
        <tr>
            <td class="sc_format_firstcol">
                <p>
                    <bean:message bundle="CONTENT" key="pictures"/>
                </p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td>
<%--
                <p class="rightalign">${shoppingCart.formattedSubtotalPhotosEUR}</p>
--%>
            </td>
            <td class="sc_format_fourthcol">
                <p class="rightalign">${shoppingCart.formattedSubtotalPhotosCHF}</p>
            </td>



        </tr>
        <tr>
            <td class="sc_format_firstcol">
                <p>
                    <bean:message bundle="CONTENT" key="shipping"/>
                </p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td>
<%--
                <p class="rightalign">${shoppingCart.formattedShippingGER}</p>
--%>
            </td>
            <td class="sc_format_fourthcol">
                <p class="rightalign">${shoppingCart.formattedShippingCHE}</p>
            </td>

        </tr>
        <tr>
            <td class="spacerCell"></td>
        </tr>
    </table>

    <table class="sc_table_description">
        <tr>
            <td class="sc_format_firstcol">
                <p><span><bean:message bundle="CONTENT" key="price.total"/></span>&nbsp;(inkl. MWST)</p>
            </td>
            <td class="sc_format_secondcol"></td>
            <td class="sc_format_thirdcol"></td>
            <td class="sc_format_fourthcol">
                <p class="rightalign">
                    <span>${shoppingCart.formattedTotalCHE}</span></p>
            </td>
            <td>
<%--
                <p class="rightalign">
                    <span>${shoppingCart.formattedTotalGER}</span></p>
--%>
            </td>
        </tr>
    </table>

</div>

<!-- todo form end should be here. change links to submit-->
<div class="contentW rightalign padding19top">
    <c:if test="${shoppingCart.backToAlbumParams!=null}">

        <div class="leftalign">
                <%--<html:link action="${shoppingCart.backToAlbumLink}">--%>
            <html:link action="/album/${albumBean.album.genericLevelId}/${albumBean.album.longTitle}"
                       name="shoppingCart" property="backToAlbumParams">
                <html:img bundle="IMAGES" srcKey="btn.select.more.src" altKey="btn.select.more.alt"/>
            </html:link>
        </div>
    </c:if>

    <div class="rightalign">
        <!--
                    set default actionParam to checkOut to support non js browser
        -->
        <html:hidden property="actionParam" value="checkOut"/>


        <!--
        if js is enabled, this form will be submitted with the actionParam 'checkOut'; otherwise, the form won't be submitted but checkout started
        updateShoppingCart??
        -->
        <html:image bundle="IMAGES" srcKey="btn.checkout.src" altKey="btn.checkout.alt"
                    onclick="postSimpleForm('checkOut')"/>
    </div>
</div>
<div class="contentW rightalign padding19bottom"><p class="rightalign">
    <bean:message bundle="CONTENT" key="order.ssl.proceed"/>
</p>
</div>

</html:form>