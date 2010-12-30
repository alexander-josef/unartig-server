<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<div class="etappe">
    <!--todo: use current level to set the action to the action link of the current level-->
    <!--if the etappe is <0 then the current level shall simply be reloaded-->
    <html:form action="/sportsAlbum/query.html" method="get">
    <%--<html:form action="${level.indexNavLink}">--%>
        <%-- page = 0 indicates the parameter-search --%>
        <html:hidden property="callingLevel" value="${level.indexNavLink}"/>
        <html:hidden property="levelId" value="${level.genericLevelId}"/>
        <html:hidden property="albumLevel" value="${level.albumLevel}"/>

        <html:hidden property="page" value="0"></html:hidden>
        <html:select property="etappe" styleClass="SelectEtappe">
            <%-- etappe < 0 means no etappe chosen  --%>
            <html:option value="-1"><bean:message bundle="CONTENT" key="alleEtappen"/></html:option>
            <html:options collection="sportsAlbumList" labelProperty="navTitle" property="genericLevelId"/>

        </html:select>

        <html:text styleClass="txt" property="startNumber" size="8" title="Startnummer eingeben"/>
        <html:image styleClass="btn" src="/images/go_button.gif" alt="submit"/>

    </html:form>
</div>