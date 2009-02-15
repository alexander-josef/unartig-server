<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<html:img styleClass="leftalign" bundle="IMAGES" srcKey="sidebar.title.search.src" altKey="sidebar.title.search.alt"/>
<div class="sidebarElement">
    <!--todo: use current level to set the action to the action link of the current level-->
    <!--if the etappe is <0 then the current level shall simply be reloaded-->
    <html:form action="/sportsAlbum/query.html" method="get">
        <%--<html:form action="${level.indexNavLink}">--%>
        <%-- page = 0 indicates the parameter-search --%>
        <html:hidden property="callingLevel" value="${level.indexNavLink}"/>
        <html:hidden property="levelId" value="${level.genericLevelId}"/>
        <html:hidden property="albumLevel" value="${level.albumLevel}"/>

        <html:hidden property="page" value="0"/>
        <fieldset class="forms">
            <div class="sidebarContent">
                <h3>Startnummer</h3>
                <html:text styleClass="txt" property="startNumber" size="12" maxlength="6" title="Startnummer eingeben"/>
            </div>

            <div class="sidebarContent">
                <h3>Kategorie, Etappe</h3>
                <html:select property="etappe">
                    <%-- etappe < 0 means no etappe chosen  --%>
                    <html:option value="-1" styleClass="txt"><bean:message bundle="CONTENT" key="alleEtappen"/>
                    </html:option>
                    <html:options collection="sportsAlbumList" labelProperty="navTitle" property="genericLevelId"/>
                </html:select>
            </div>
            <div class="sidebarContent">
                <html:image  styleClass="btn" bundle="IMAGES" srcKey="btn.search.src" altKey="btn.search.alt"/>
            </div>
        </fieldset>
    </html:form>

    <p class="sidebarContent">Geben Sie hier Ihre Startnummer ein und wählen Sie Ihre Kategorie oder Etappe.</p>
</div>

<div class="sidebarBotton">
</div>




