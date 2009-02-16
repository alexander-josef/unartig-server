<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumBean" type="ch.unartig.studioserver.beans.AbstractAlbumBean" scope="session"/>

<html:xhtml/>

<ul class="pagenav">
    <li class="pagenav_text"><bean:message bundle="CONTENT" key="page"/></li>


    <!--time navigation in many-pages steps:-->
    <c:if test="${'notime' != album.type}">
        <li class="pagenav-a">
            <html:link action="${albumBean.album.actionString}" title="back" name="albumBean" property="jumpBackParams">
                <html:img src="/images/pagenav-spacer.gif" alt=""/>
            </html:link>
        </li>
    </c:if>

    <!--previous page:-->
    <li class="pagenav-b">
        <html:link action="${albumBean.album.actionString}" title="zurueck" name="albumBean" property="previousPageParams">
            <html:img src="/images/pagenav-spacer.gif" alt=""/>
        </html:link>
    </li>




    <!--not all page-number will fit-->
    <c:if test="${albumBean.numberOfPages > albumBean.maxPageNumbersFitOnNavigation}">
        <%--<c:if test="${albumBean.numberOfPages > 20}">--%>

        <!--first page link is shown if page is higher than maxpagenumberfitonnavigation -2 -->
        <c:if test="${albumBean.page >= albumBean.maxPageNumbersFitOnNavigation-2}">
            <html:link action="${albumBean.album.actionString}?page=1" >
                <li class="page">1...</li>
            </html:link>
        </c:if>

        <!--middle part-->
        <c:forEach var="i" begin="${albumBean.pagerMiddlepartStart}" end="${albumBean.pagerMiddlepartEnd}">
            <li class="page">

                <!--page nr is not current page aaa-->
                <c:if test="${i != albumBean.page}">
                    <%--<html:link href="albumPage.html" paramId="page" paramName="i" name="albumBean" property="clickedPageParams">--%>
                    <html:link action="${albumBean.album.actionString}" paramId="page" paramName="i">
                        ${i}
                    </html:link>
                </c:if>
                <!--page nr = current page-->
                <c:if test="${i == albumBean.page}">
                    <span class="active_page">${i}</span>
                </c:if>
            </li>
        </c:forEach>

        <!--last page link-->
        <c:if test="${albumBean.pagerMiddlepartEnd < albumBean.numberOfPages}">
            <li class="page">
                <html:link action="${albumBean.album.actionString}" paramId="page" paramName="albumBean" paramProperty="numberOfPages">
                    ...${albumBean.numberOfPages}
                </html:link>
            </li>
        </c:if>

    </c:if>

    <!--all page numbers fit in page-nav-->
    <c:if test="${albumBean.numberOfPages <= albumBean.maxPageNumbersFitOnNavigation}">
        <c:forEach var="i" begin="1" end="${albumBean.numberOfPages}">
            <li class="page">

                <!--page nr is not current page-->
                <c:if test="${i != albumBean.page}">
                    <html:link action="${albumBean.album.actionString}" paramId="page" paramName="i">
                        ${i}
                    </html:link>
                </c:if>
                <!--page nr = current page-->
                <c:if test="${i == albumBean.page}">
                    <span class="active_page">${i}</span>
                </c:if>
            </li>
        </c:forEach>
    </c:if>

    <!--next page:-->
    <li class="pagenav-c">
        <html:link action="${albumBean.album.actionString}" title="naechste Seite" name="albumBean" property="nextPageParams">
            <html:img src="/images/pagenav-spacer.gif" alt=""/>
        </html:link>
    </li>


    <!--time navigation in 5 min steps:-->
    <c:if test="${'notime' != albumBean.type}">
        <li class="pagenav-d">
            <html:link action="${albumBean.album.actionString}" title="jump forward" name="albumBean" property="jumpForwardParams">
                <html:img src="/images/pagenav-spacer.gif" alt=""/>
            </html:link>
        </li>
    </c:if>

</ul>