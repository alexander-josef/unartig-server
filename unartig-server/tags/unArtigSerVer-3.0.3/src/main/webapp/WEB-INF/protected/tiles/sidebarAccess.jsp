<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<html:img styleClass="leftalign" bundle="IMAGES" srcKey="sidebar.title.access.src" altKey="sidebar.title.access.alt"/>
<div class="sidebarElement">
    <form action="" id="quickaccess">
        <input name="quickaccess" class="txt" type="text" size="15"/>
        <html:link action="/overview/101/2005/show.html">
            <html:img styleClass="leftalign" src="/images/go_button.gif" alt="Polyball"/>
        </html:link>
    </form>
    <br/>
    <p>Geben Sie hier den Zugangscode des gesuchten Anlasses ein.</p>
</div>
<div class="sidebarBotton">
</div>
