<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>
<div class="contentW rightalign padding19both">
<html:form action="/accountOptOut">
    <table class="left_button">
        <tr>
            <td><html:checkbox property="noUnartigAccount" value="true"/></td>
            <td><p class="checkboxtext">&nbsp;<bean:message bundle="CONTENT" key="account.no"/></p></td>
        </tr>
        <tr>
            <td><html:checkbox property="noCoplaEmails" value="true"/></td>
            <td><p class="checkboxtext">&nbsp;<bean:message bundle="CONTENT" key="coplaEmails.no"/></p></td>
        </tr>

    </table>
</div>
<div class="contentW rightalign padding19top">

<div class="rightalign ">
                <html:link href="index.html">
                    <html:img bundle="IMAGES" srcKey="btn.next.src" altKey="btn.next.alt"/>
                </html:link>

</div>


</html:form>


</div>