<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content" id="content_account_album">
    <ul class="album">
        <!-- loop this 'li' start -->
        <li class="albumslide">
            <div class="imageholder_landscape">
                <img src="/images/slide_pic_landscape.gif" alt=""/>
            </div>
            <div class="album_slide_info_sc"><a href=""><bean:message bundle="BUTTONS" key="delete"  /></a></div>
        </li>
        <!-- loop this 'li' end -->
    </ul>
</div>
