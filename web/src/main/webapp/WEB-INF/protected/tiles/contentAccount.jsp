<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="content" id="content753">
    <div id="content_left496_head">
        <img class="content_title" src="/images/lang_spec_images/title_account_de.gif" alt="">

        <p id="head_coment">
            <bean:message bundle="CONTENT" key="order.thanks"  /></p>
    </div>

    <div class="content_left496_middle">
        <h3><bean:message bundle="CONTENT" key="welcome.album"  /></h3>

        <p><bean:message bundle="CONTENT" key="account.welcome.info"  /></p>
    </div>


    <div class="content_left496_middle" id="content_left496_last">
        <h3><bean:message bundle="CONTENT" key="account.new.images"  /></h3>
        <ul class="album">
            <!-- loop this 'li' and adapt div class to format-->
            <li class="albumslide">
                <div class="imageholder_landscape"><a href=""><img src="/images/slide_pic_landscape.gif" alt=""/></a>
                </div>

                <div class="archive_slide_info"><!-- short title-->Polyball 2004</div>
            </li>
            <!-- end loop -->
        </ul>
    </div>
</div>
