<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<ul id="topmenu" class="leftalign">
    <li id="menu_home" class="leftalign">
        <html:link action="/index" styleClass="leftalign"><img src="/images/topMenu/link_home_spacer.gif" alt=""/>
        </html:link>
    </li>
    <li id="menu_about" class="leftalign">
        <html:link action="/About" styleClass="leftalign">
            <html:img bundle="IMAGES" srcKey="topmenu.about.src" altKey="topmenu.about.alt"/>
        </html:link>
    </li>
    <li id="menu_contact" class="leftalign">
        <html:link action="/Contact" styleClass="leftalign">
            <html:img bundle="IMAGES" srcKey="topmenu.contact.src" altKey="topmenu.contact.alt"/>
        </html:link>
    </li>
    <%--
        <li id="menu_account">
            <a href="/Account.html">
                <html:img bundle="IMAGES" srcKey="topmenu.account.src" altKey="topmenu.account.alt"/>
            </a>
        </li>
    --%>
    <li id="menu_shopping_cart" class="leftalign">
        <html:link action="/toShoppingCart" styleClass="leftalign">
            <img src="/images/topMenu/link_shopping_cart_spacer.gif" alt=""/>
        </html:link>
    </li>
    <li id="menu_login" class="leftalign">
        <html:link action="/Login" styleClass="leftalign">
            <img src="/images/topMenu/link_login_spacer.gif" alt=""/>
        </html:link>

    </li>
    <%--
        <li id="menu_logout"><a href="/index.html"><img src="/images/topMenu/link_logout_spacer.gif" alt=""/></a></li>
    --%>
    <li id="menu_help" class="leftalign"><a href="/FAQ.html" class="leftalign"><img src="/images/topMenu/link_question_spacer.gif" alt=""/></a></li>
    <%--
        <li id="menu_user"><!--User--></li>
    --%>
</ul>
<ul id="langmenu" class="rightalign">

    <%--<html:hidden property="page" value="<%=referer%>"/>--%>
    <%--<html:hidden property="page2" value="<%=page2%>"/>--%>

    <li id="lang_de" class="leftalign">
        <html:link action="/setLocale?language=de">
            <html:img src="/images/topMenu/link_lang_spacer.gif" title="Sprache Deutsch" alt=""/>
        </html:link>
    </li>
    <%--<html:image src="/images/link_lang_DE_active.gif" title="Sprache Deutsch" property="language" value="de" alt=""/></li>--%>
    <li id="lang_en" class="leftalign">
        <html:link action="/setLocale?language=en">
            <html:img src="/images/topMenu/link_lang_spacer.gif" title="Language english" alt=""/>
        </html:link>
    </li>
    <li id="lang_fr" class="leftalign">
        <html:link action="/setLocale?language=fr">
            <html:img src="/images/topMenu/link_lang_spacer.gif" title="Longage francais" alt=""/>
        </html:link>
    </li>
</ul>
