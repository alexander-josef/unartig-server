<%@ page import="ch.unartig.studioserver.Registry" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumList" type="java.util.Collection" scope="request"/>

<html:xhtml/>

<div class="contentW rightalign padding19bottom bottomBorder">
    <html:img styleClass="leftalign" bundle="IMAGES" srcKey="title.admin.upload.src" altKey="title.admin.upload.alt"/>
</div>

<div class="contentW rightalign padding19top bottomBorder">

    <form action="/admin/uploadSingleAlbum.html" method="post" enctype="multipart/form-data">
        <fieldset class="forms">
            <h3>Upload and import in database</h3><br/>
            <table class="form_table">
                <tr>
                    <td><p>Album name</p></td>

                    <td>
                        <select name="<%=Registry._ALBUM_ID_NAME%>">
                            <c:forEach var="album" items="${albumList}">
                                <option value="${album.genericLevelId}">${album.longTitle}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td/>
                </tr>

                <tr>
                    <td>
                        <p>Choose zip-file:</p>
                    </td>
                    <td class="browsButton">
                        <input class="navTitle" type="file" name="Datei" accept="application/zip">
                    </td>
                    <%-- <td class="browsButton"><html:file  styleClass="navTitle" property="indexPhoto"/></td>--%>
                    <td/>
                </tr>
                <tr>

                    <td><p>In DB importieren</p></td>
                    <td>
                        <input class="admin_button" type="checkbox" name="importInDb"/>
                    </td>
                    <td/>
                </tr>
                <tr>
                    <td><p>Generate thumnails</p></td>
                    <td><input type="checkbox" class="admin_button" name="generateThumbnails"/></td>
                    <td/>
                </tr>
                <tr>
                    <td><p>Generate displays</p></td>
                    <td><input type="checkbox" class="admin_button" name="generateDisplay"/></td>
                    <td/>
                </tr>
                <tr>
                    <td><p>Apply water mark</p></td>
                    <td><input type="checkbox" class="admin_button" name="generateWatermark"/></td>
                    <td/>
                </tr>
                <tr>
                    <td/>
                    <td><input type="image" class="admin_button" src="/images/upload_button.gif" alt="upload"/></td>
                    <td/>
                </tr>
            </table>
        </fieldset>
    </form>
</div>

<div class="contentW rightalign padding19top bottomBorder">
    <html:form action="/admin/importDirectory">
        <fieldset class="forms">
            <h3>Import uploaded files in database</h3><br/>
            <table class="form_table">
                <tr>
                    <td><p>Album name</p></td>

                    <td>
                        <select name="<%=Registry._ALBUM_ID_NAME%>">
                            <c:forEach var="album" items="${albumList}">
                                <option value="${album.genericLevelId}">${album.longTitle}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><p>Path on Server (/fine):</p></td>
                    <td>
                        <html:text property="imagePath" size="40"/>
                    </td>
                    <td/>
                </tr>

                <tr>
                    <td><p>Generate Thumbs&nbsp;</p></td>
                    <td>
                        <html:checkbox styleClass="admin_button" property="processImages"/>
                        &nbsp; enable this checkbox to create new display and thumbnail images; alternativly copy the
                        display and thumbnail images manually in the directories 'thumbnail' or 'display' respectivly
                    </td>
                    <td/>
                </tr>

                <tr>
                    <td/>
                    <td>
                        <input type="image" class="admin_button" src="/images/import_button.gif" alt="import in database" value="Import">
                    </td>
                    <td/>
                </tr>
            </table>
        </fieldset>
    </html:form>

</div>


