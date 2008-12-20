<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:html>
    <html:xhtml/>
    <head>
        <html:base/>

        <tiles:insert attribute="htmlHeader"/>
        <tiles:insert attribute="cssOverrule"/>
        <tiles:insert attribute="jsTreeInsert"/>
        <title><tiles:insert attribute="htmlTitle"/></title>
    </head>

    <body onload="preloadimages()">
    <div id="dynamic_whiteboard" class="float">
        <div id="board" class="float">
            <div id="header" class="narrow">
                <html:link action="/index">
                    <img src="/images/logo.gif" alt="unartig home"/>
                </html:link>

                <div id="menu" class="narrowMenu">
                    <tiles:insert attribute="topMenu"/>
                </div>
            </div>

            <div id="container" class="float">
                <div id="indexnav">
                <tiles:insert attribute="indexNavigation"/>
                </div>
                <div id="sidebar">
                    <tiles:insert attribute="sidebar1"/>
                    <tiles:insert attribute="sidebar2"/>
                    <tiles:insert attribute="sidebar3"/>
                </div>
                <tiles:insert attribute="pageName"/>


                <tiles:insert attribute="navDivOpen"/>

                <tiles:insert attribute="navPagenav"/>

                <tiles:insert attribute="navDivClose"/>


                <!--<div class="content" id="content612">-->
                <tiles:insert attribute="contentHead"/>
                <tiles:insert attribute="content1"/>
                <tiles:insert attribute="content2"/>
                <tiles:insert attribute="content3"/>
                <tiles:insert attribute="content4"/>
                <tiles:insert attribute="content5"/>
                <tiles:insert attribute="content6"/>
                <tiles:insert attribute="content7"/>
                <!--</div>-->
            </div>
        </div>
        <tiles:insert attribute="footerLinks"/>
    </div>

    <div id="footer">
        <%-- todo use tile--%>
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

    <div id="banner">
        <div id="bannerTop"></div>

        <div id="bannerCenter">
            <div id="bannerContent">
                <tiles:insert attribute="Skyscraper"/>
             </div>
        </div>

        <div id="bannerBottom">
        </div>
    </div>

    <tiles:insert attribute="Analysis"/>
    </body>
</html:html>