<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="padding19bottom">
    <html:link action="/unartigPortfolio">
        <img src="/images/button/portfolio.gif" alt=""/>
    </html:link>
</div>

<html:img styleClass="leftalign" bundle="IMAGES" srcKey="siedbar.title.mypictures.src" altKey="siedbar.title.mypictures.alt"/>

<div class="sidebarElement">
    <script language="JavaScript" type="text/javascript">
        new tree (TREE_ITEMS, TREE_TPL, 'unartigTree');
    </script>
    <noscript >
        <jsp:include page="../generatedStaticEventLinks.jsp"/>
    </noscript>
</div>
<div class="sidebarBotton">
</div>