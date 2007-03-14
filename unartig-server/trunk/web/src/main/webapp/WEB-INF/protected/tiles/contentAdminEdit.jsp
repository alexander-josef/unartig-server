<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="parentList" type="java.util.List" scope="request"/>
<jsp:useBean id="productTypeList" type="java.util.List" scope="request"/>
<jsp:useBean id="level" type="ch.unartig.studioserver.model.GenericLevel" scope="request"/>


<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img styleClass="leftalign" bundle="IMAGES" srcKey="title.admin.edit.src" altKey="title.admin.edit.alt"/>
</div>


<div class="contentW rightalign padding19both bottomBorder">
    <fieldset class="forms">
        <h3>Edit ${level.levelType}</h3><br/>
        <table class="form_table">
            <html:form action="/admin/updateLevel" method="POST" enctype="multipart/form-data">
                <html:hidden property="genericLevelId"/>
                <tr>
                    <th><p>Index picture</p></th>

                    <td class="browsButton">
                        <html:file styleClass="navTitle" property="indexPhoto"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th/>
                    <td><br/></td>
                    <th/>
                </tr>
                <tr>
                    <th/>
                    <td>
                        <ul class="album">
                            <!--in each 'li' change class to either 'imageholder-landscape' or 'imageholder_portrait' depending on format-->
                            <li class="albumslide">
                                <div class="imageholder_landscape">
                                    <html:img src="${level.levelOverviewImgUrl}" alt="index" title=""/>
                                </div>

                                <div class="archive_slide_info">
                                    <!--<bean:message bundle="CONTENT" key="cart.incart"/>--></div>
                            </li>
                        </ul>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th/>
                    <td><br/></td>
                    <th/>
                </tr>

                <!--
                no parent level for Category
                -->
                <c:if test="${!level.categoryLevel}">
                    <tr>
                        <th><p>Select Parent Level</p></th>
                        <td><html:select property="parentLevelId">
                            <html:options labelProperty="longTitle" collection="parentList" property="genericLevelId"/>
                        </html:select></td>
                        <td></td>
                    </tr>
                </c:if>
                <tr>
                    <th><p>Navigation Title</p></th>
                    <td>
                        <html:text styleClass="navTitle" maxlength="30" size="40" property="navTitle"/></td>
                    <td></td>
                </tr>
                <tr>
                    <th><p>Long Title</p></th>
                    <td>
                        <html:text styleClass="navTitle" maxlength="50" size="40" property="longTitle"/></td>
                    <td></td>
                </tr>
                <tr>
                    <th><p>Description</p></th>
                    <td>
                        <html:text styleClass="navTitle" maxlength="200" size="40" property="description"/></td>
                    <td></td>
                </tr>
                <!--Event Date only for Event-->
                <c:if test="${level.eventLevel}">
                    <tr>
                        <th><p>Event date</p></th>
                        <td>
                            <html:text styleClass="navTitle" maxlength="200" size="40" property="eventDateDisplay"/></td>
                        <td>( dd.MM.yyyy )</td>
                    </tr>
                </c:if>
                <tr>
                    <th><p>Is Private?</p></th>
                    <td><html:checkbox styleClass="admin_button" property="privateEvent"/></td>
                    <td></td>
                </tr>
                <c:if test="${level.albumLevel}">
                    <tr>
                        <th><p>No-Time Album?</p></th>
                        <td><html:checkbox styleClass="admin_button" property="noTime"/></td>
                        <td></td>
                    </tr>
                </c:if>
                <tr>
                    <th><p>Quick-Access</p></th>
                    <td><html:text styleClass="navTitle" maxlength="50" size="40" property="quickAccess"/></td>
                    <td></td>
                </tr>
                <tr>
                    <th><p>Private Access Code</p></th>
                    <td>
                        <html:text styleClass="navTitle" maxlength="50" size="40" property="privateAccessCode"/></td>
                    <td></td>
                </tr>
                <tr>
                    <th><p>Album Werbung fuer Banner rechts</p></th>
                    <td>
                        <html:textarea styleClass="navTitle" cols="50" rows="20" property="skyBannerRightAd"/></td>
                    <td>(falls nicht definiert wird sie fuer dieses level vom uebergeordneten level genommen)</td>
                </tr>
                <!-- product definitions : -->
                <c:if test="${level.albumLevel}">

                    <tr>
                    <%--iterate over producttypes and show possible prices--%>
                        <th><p>Available Products</p></th>
                        <td>
                            <table class="pricelist_box">
                                <tr>
                                    <th>Product Type</th>
                                    <th>Price</th>
                                </tr>
                                    <logic:iterate id="productType" name="productTypeList" >
                                        <tr>
                                            <td>${productType.name}</td>
                                            <td>
                                                <html:select property="productPrices(${productType.productTypeId})" >
                                                    <html:option value="-1">not available</html:option>
                                                    <html:optionsCollection name="productType" property="prices" value="priceId" label="priceCHF" />
                                                </html:select>
                                            </td>
                                        </tr>
                                    </logic:iterate>
                            </table>
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <th></th>
                    <td>
                        <html:image styleClass="admin_button" styleId="abschicken" src="/images/update_button.gif" alt="" title="und los"/></td>
                    <td></td>
                </tr>

            </html:form>
        </table>
    </fieldset>
</div>

<div class="contentW rightalign padding19both bottomBorder">
<fieldset class="forms">
<table>
<!--
sportsalbum stuff
-->
<c:if test="${level.sportsAlbumLevel}">
    <html:form action="/admin/startnumberMapping" method="POST" enctype="multipart/form-data">
        <html:hidden property="sportsAlbumId" value="${level.genericLevelId}"/>
        <tr>
            <th><p>Startnummer Mapping:</p></th>
            <td/>
            <td/>
        </tr>

        <tr>
            <th><p>File</p></th>
            <td class="browsButton">
                <html:file styleClass="navTitle" property="mappingFile"/>
            </td>
            <td><p>(Manual mapping File aus Tool: [Filename]TAB[Startnumber])</p></td>
        </tr>
        <tr>
            <th></th>
            <td>
                <html:image styleClass="admin_button" styleId="abschicken" src="/images/update_button.gif" alt="" title="und los"/></td>
            <td></td>
        </tr>
    </html:form>
    </table>
    </fieldset>
    </div>

    <div class="contentW rightalign padding19both bottomBorder">
        <fieldset class="forms">
            <table>


                <html:form action="/admin/finishtimeMapping" method="POST" enctype="multipart/form-data">
                    <html:hidden property="sportsAlbumId" value="${level.genericLevelId}"/>
                    <tr>
                        <th><p>Laufzeiten Mapping:</p></th>
                        <td/>
                        <td/>
                    </tr>
                    <tr>
                        <th><p>Photo-point Difference?</p></th>
                        <td><html:text styleClass="navTitle" property="photopointFinishDifference"/></td>
                        <td><p>in seconds</p></td>
                    </tr>

                    <tr>
                        <th><p>Photo-point Toleranz?</p></th>
                        <td><html:text styleClass="navTitle" property="photopointTolerance"/></td>
                        <td><p>in seconds (defaults to 5 sec.)</p></td>
                    </tr>

                    <tr>
                        <th><p>File</p></th>
                        <td class="browsButton">
                            <html:file styleClass="navTitle" property="mappingFile"/>
                        </td>
                        <td><p>(Laufzeiten File von SOLA: [etappe]TAB[Startnumber]TAB[zeit]TAB[Mannschaft]) IDEE:
                            file-struktur hier eingeben, dann koennen alle arten von Files geparsed werden</p></td>
                    </tr>
                    <tr>
                        <td><p>Photopoint:</p></td>
                        <td><p><html:radio property="photopointLocation" value="beforeFinishTiming"/>(vor
                            Zeit-Messung)</p>

                            <p><html:radio property="photopointLocation" value="afterStartTiming"/>(nach
                                Zeit-Messung)</p>
                        </td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <html:image styleClass="admin_button" styleId="abschicken" src="/images/update_button.gif" alt="" title="und los" value=""/></td>
                        <td></td>
                    </tr>


                </html:form>
            </table>
        </fieldset>
    </div>

    <div class="contentW rightalign padding19both bottomBorder">
    <fieldset class="forms">
    <table>


    <html:form action="/admin/deleteFinishtimeMappings" method="POST">
        <html:hidden property="sportsAlbumId" value="${level.genericLevelId}"/>
        <tr>
            <th><p>Alle Startnummern-mappins loeschen! (TODO)</p></th>
            <td>
                <html:submit onclick="if (confirm('Alle Startnummern-Mappings fuer dieses Album loeschen?')) return true; else return false;"/></td>
            <td></td>
        </tr>
    </html:form>

</c:if>
</table>
</fieldset>
</div>

