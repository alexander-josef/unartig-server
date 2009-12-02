<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content_left496_middle">
    <h3><bean:message bundle="CONTENT" key="archive.info"  /></h3>
    <ul class="album">
        <!-- loop this 'li'-->
        <!--in each 'li' change class to either 'imageholder-landscape' or 'imageholder_portrait' depending on format-->
        <li class="albumslide">
            <div class="imageholder_landscape"><a href=""><img src="/images/slide_pic_landscape.gif" alt=""/></a></div>

            <div class="album_slide_info">19:00</div>
        </li>
        <!-- loop end-->
    </ul>
</div>