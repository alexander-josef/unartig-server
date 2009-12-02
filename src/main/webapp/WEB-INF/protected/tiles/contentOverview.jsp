<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="overviewEntriesList" type="java.util.List" scope="request"/>
<html:xhtml/>

<div class="contentNplus rightalign padding19both">
    <ul class="aktuell">
        <c:forEach var="entry" items="${overviewEntriesList}">
            <li>
                <div class="imageholder_landscape">
                    <p class="aktuell_date">${entry.eventDateDisplay}</p>

                    <html:link href="${entry.indexNavLink}">
                        <html:img src="${entry.levelOverviewImgUrl}" alt="${entry.levelOverviewImgUrl}" title="${entry.longTitle}"/>
                    </html:link>

                    <p class="aktuell_name">${entry.longTitle}</p>

                    <p class="aktuell_picn">${entry.numberOfPhotos}
                        <bean:message bundle="CONTENT" key="pictures"/>
                    </p>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
