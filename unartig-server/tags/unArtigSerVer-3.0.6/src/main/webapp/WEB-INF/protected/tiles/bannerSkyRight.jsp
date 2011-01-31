<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>
<!--add-->
<c:if test="${not empty requestScope.level}">
    <!--album ad available-->
    <jsp:useBean id="level" type="ch.unartig.studioserver.model.GenericLevel" scope="request"/>
    <bean:write name="level" property="albumAdvertisment(right)" scope="request" filter="false"/>
</c:if>
<%--<%=request.getParameter("level")%>--%>
<c:if test="${empty requestScope.level}">
    <%--
    maybe use a resource like this:
    <bean:resource id="" name=""/>
    --%>
    <!--
    default ad
    -->
    <script type="text/javascript"><!--
    google_ad_client = "pub-3729824609330941";
    google_alternate_ad_url = "http://unartig.ch/images/banner/banner_unartig.gif";
    google_ad_width = 120;
    google_ad_height = 600;
    google_ad_format = "120x600_as";
    google_ad_type = "text";
    google_ad_channel = "";
    google_color_border = "FFFFFF";
    google_color_bg = "FFFFFF";
    google_color_link = "E1771E";
    google_color_text = "7F7F7F";
    google_color_url = "008000";
    //--></script>
    <script type="text/javascript"
            src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
    </script>
</c:if>


