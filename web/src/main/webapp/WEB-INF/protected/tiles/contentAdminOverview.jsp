<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="categories" type="java.util.ArrayList" scope="request"/>

<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img styleClass="leftalign" bundle="IMAGES" srcKey="title.admin.over.src" altKey="title.admin.over.alt"/>
</div>

<!--<div id="displaytopspacer"></div>-->

<div class="contentW rightalign padding19both">

    <table id="admin_database">
        <tr>
            <th>Level 1</th>
            <th>Level 2</th>
            <th>Level 3</th>
            <th>Level 4</th>
        </tr>
        <tr>
            <th>Category</th>
            <th>Event-Group</th>
            <th>Event</th>
            <th>Studio</th>
        </tr>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <!--categories:-->
        <logic:iterate name="categories" id="category" indexId="categoryIndex">
            <tr>
                <td>
                    <html:link action="/admin/editLevel" paramId="genericLevelId" paramName="category" paramProperty="genericLevelId" styleClass="link_edit">
                        ${category.navTitle}
                        <img src="/images/admin_edit.gif" class="img_edit" alt="edit" title="edit"/>
                    </html:link>

                    <html:link action="/admin/deleteLevel"
                               onclick="if (confirm('Permanently delete Category? All Levels under this category will be deleted as well!')) return true; else return false;" paramId="genericLevelId"
                               paramName="category" paramProperty="genericLevelId" styleClass="link_trash">
                        <img src="/images/admin_trash.gif" class="img_trash" alt="trash" title="delete"/>
                    </html:link>


                </td>
                <td/>
                <td/>
                <td/>
            </tr>
            <!--event-groups:-->
            <logic:iterate name="category" property="eventGroups" id="eventGroup" indexId="eventGroupIndex">
                <tr>
                    <td/>
                    <td>
                        <html:link action="/admin/editLevel" paramId="genericLevelId" paramName="eventGroup" paramProperty="genericLevelId" styleClass="link_edit">
                            ${eventGroup.navTitle}
                            <img src="/images/admin_edit.gif" class="img_edit" alt="edit" title="edit"/>
                        </html:link>
                        <html:link action="/admin/deleteLevel"
                                   onclick="if (confirm('Permanently delete Event Group? All Events under this category will be deleted as well!')) return true; else return false;"
                                   paramId="genericLevelId" paramName="eventGroup" paramProperty="genericLevelId" styleClass="link_trash">
                            <img src="/images/admin_trash.gif" class="img_trash" alt="trash" title="delete"/>
                        </html:link>
                    </td>
                    <td/>
                    <td/>
                </tr>

                <!--events:-->
                <logic:iterate name="eventGroup" property="events" id="event" indexId="eventIndex">
                    <tr>
                        <td/>
                        <td/>
                        <td>
                            <html:link action="/admin/editLevel" paramId="genericLevelId" paramName="event" paramProperty="genericLevelId" styleClass="link_edit">
                                ${event.navTitle}
                                <img src="/images/admin_edit.gif" class="img_edit" alt="edit" title="edit"/>
                            </html:link>
                            <html:link action="/admin/deleteLevel"
                                       onclick="if (confirm('Permanently delete Event? All Albums under this Event will be deleted as well!')) return true; else return false;" paramId="genericLevelId"
                                       paramName="event" paramProperty="genericLevelId" styleClass="link_trash">
                                <img src="/images/admin_trash.gif" class="img_trash" alt="trash" title="delete"/>
                            </html:link>

                        </td>
                        <td/>
                    </tr>
                    <!--studios:-->
                    <logic:iterate name="event" property="studios" id="studio" indexId="studioIndex">
                        <tr>
                            <td/>
                            <td/>
                            <td/>
                            <td>
                                <html:link action="/admin/editLevel" paramId="genericLevelId" paramName="studio" paramProperty="genericLevelId" styleClass="link_edit">
                                    ${studio.navTitle} ***
                                    <img src="/images/admin_edit.gif" class="img_edit" alt="edit" title="edit"/>
                                </html:link>
                                <html:link action="/admin/deleteLevel"
                                           onclick="if (confirm('Permanently delete Albem? All Photos under this Album will be deleted as well!')) return true; else return false;"
                                           paramId="genericLevelId" paramName="studio" paramProperty="genericLevelId" styleClass="link_trash">
                                    <img src="/images/admin_trash.gif" class="img_trash" alt="trash" title="delete"/>
                                </html:link>
                                <c:if test="${studio.publish}">
                                    <html:link action="/admin/toggleAlbumPublishStatus"
                                               onclick="if (confirm('Toggle Album status from online to offline?')) return true; else return false;"
                                               paramId="genericLevelId" paramName="studio" paramProperty="genericLevelId" styleClass="link_trash">
                                        <img src="/images/album_online.gif" class="img_trash" alt="online" title="Album is online; click to toggle to offline"/>
                                    </html:link>
                                </c:if>

                                <c:if test="${!studio.publish}">
                                    <html:link action="/admin/toggleAlbumPublishStatus"
                                               onclick="if (confirm('Toggle Album status from offline to online?')) return true; else return false;"
                                               paramId="genericLevelId" paramName="studio" paramProperty="genericLevelId" styleClass="link_trash">
                                        <img src="/images/album_offline.gif" class="img_trash" alt="offline" title="Album is offline; Click to toggle to online"/>
                                    </html:link>
                                </c:if>

                            </td>
                        </tr>

                    </logic:iterate>

                </logic:iterate>
            </logic:iterate>

        </logic:iterate>
    </table>
</div>

