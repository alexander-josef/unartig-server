<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>
    <html:xhtml/>
    <head>
        <tiles:insert attribute="htmlHeader"/>
        <tiles:insert attribute="cssOverrule"/>
        <tiles:insert attribute="jsTreeInsert"/>
        <title><tiles:insert attribute="htmlTitle"/></title>
    </head>

    <body onload="preloadimages()">

    <div id="dynamic_whiteboard_wide" class="float">
        <div id="board_wide" class="float">
            <div id="header" class="wide"><a href="/"><img src="/images/logo.gif" alt="unartig home"/></a>

                <div id="menu"  class="wideMenu">
                    <tiles:insert attribute="topMenu"/>
                </div>
            </div>

            <div id="container_wide" class="float">

                <div id="indexnav">
                    <tiles:insert attribute="indexNavigation"/>
                </div>
                <div id="sidebar">
                    <tiles:insert attribute="sidebar1"/>
                    <tiles:insert attribute="sidebar2"/>
                    <tiles:insert attribute="sidebar3"/>
                </div>

                <div class="content_right752">
                    <tiles:insert attribute="pageName"/>
                    <tiles:insert attribute="navigationline"/>
                </div>

                <div class="content_right755">
                    <tiles:insert attribute="content"/>
                </div>

                <div class="content_right752">
                    <tiles:insert attribute="footNavigationline"/>
                </div>
            </div>
        </div>
        <tiles:insert attribute="footerLinks"/>
    </div>

    <div id="footer_wide">
        <ul id="footerlinks-access">
            <li><a href="/index.html">Home</a>|</li>
            <li><a href="/About.html"><bean:message bundle="CONTENT" key="link.about"/></a>|</li>
            <li><a href="/Contact.html"><bean:message bundle="CONTENT" key="link.contact"/></a>|</li>
            <li><a href="/FAQ.html"><bean:message bundle="CONTENT" key="link.faq"/></a>|</li>
            <li><a href="/Copyright.html">&copy; unartig AG 2009</a>|</li>
            <li><a href="/Privacy.html"><bean:message bundle="CONTENT" key="link.privacy"  /></a>|</li>
            <li><a href="/AGB.html"><bean:message bundle="CONTENT" key="link.agb"  /></a>|</li>
            <li><a href="/setLocale.html?language=de">DE</a>|</li>
            <li><a href="/setLocale.html?language=en">EN</a>|</li>
            <li><a href="/setLocale.html?language=fr">FR</a>|</li>
        </ul>
    </div>

    <tiles:insert attribute="Analysis"/>
    </body>
</html:html>
<%--<tiles:insert attribute="secondInsert"/>--%>