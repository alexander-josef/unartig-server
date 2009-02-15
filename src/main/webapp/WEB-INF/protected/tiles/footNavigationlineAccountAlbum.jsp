<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="footnavigation" id="footnavigation_account_album">
    <div class="navigationline">
        <ul class="pagenav">
            <li class="pagenav_text">Seite</li>
            <li class="pagenav-a"><a href=""><img src="/images/pagenav-spacer.gif" alt=""/></a></li>
            <li class="pagenav-b"><a href=""><img src="/images/pagenav-spacer.gif" alt=""/></a></li>
            <li class="page"><a href="">1</a></li>
            <li class="page"><a href="">2</a></li>
            <li class="page"><a href="">3</a></li>
            <li class="lastpage"><a href="">&nbsp;...12</a></li>
            <li class="pagenav-c"><a href=""><img src="/images/pagenav-spacer.gif" alt=""/></a></li>
            <li class="pagenav-d"><a href=""><img src="/images/pagenav-spacer.gif" alt=""/></a></li>
        </ul>
        <table class="numberofspages"><tr><td><p>Bilder pro Seite</p></td>
            <td>
                <select name="numbersofpages" class="pagesno">
                    <option value="19">15</option>
                    <option value="20">10</option>
                    <option value="21">20</option>
                    <option value="22">25</option>
                </select>
            </td>
        </tr>
        </table>
    </div>
</div>
<div class="footnavigation" id="footnavigation_account_delete">
   <table class="right_button">
        <tr>
            <td><p>einzelne Bilder löschen</p></td>
            <td><a href="AccountAlbumDelete.html"><img src="/images/enter.gif" alt=""/></a></td>
        </tr>
    </table>
</div>
