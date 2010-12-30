<%@ page import="ch.unartig.studioserver.Registry" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="albumList" type="java.util.Collection" scope="request"/>

Einzelner Anlass hochladen:
<html>
<head></head>

<body>
<form action="/admin/uploadSingleAlbum.html" method="post" enctype="multipart/form-data">

    <table>
        <tr>
            <td class="formLeft">Studio Name</td>

            <td class="formRight">

                <select name="<%=Registry._ALBUM_ID_NAME%>">
                    <c:forEach var="album" items="${albumList}">
                        <option value="${album.genericLevelId}">${album.longTitle}</option>
                    </c:forEach>
                </select>

                <%--<html:select property="albumId">--%>
                <%--<html:options labelProperty="longTitle" collection="albumList" property="genericLevelId"/>--%>
                <%--</html:select>--%>
            </td>

            <td></td>
            <td>
            </td>
            <td>
                <input type="checkbox" name="importInDb"/>In DB importieren?
            </td>
        </tr>
        <tr>
            <td class="formLeft">Event Pfad auf dem Server:</td>
            <td class="formRight"><input type="text" name="" size="50"></td>
        </tr>
        <tr>
            <td class="formLeft">W&auml;hle eine Zip-Datei:</td>
            <td class="formRight"><input type="file" name="Datei" size="50" accept="application/zip"></td>
        </tr>
        <tr>
            <td class="formLeft"/><td class="formRight"><input type="checkbox" name="generateThumbnails"/>
            Thumbnail-Bilder generieren ? </td>
        </tr>
        <tr>
            <td class="formLeft"/><td class="formRight"><input type="checkbox" name="generateDisplay"/>Display-Bilder
            generieren ? </td>
        </tr>
        <tr>
            <td class="formLeft"/><td class="formRight"><input type="checkbox" name="generateWatermark"/>Watermark ueber
            Bilder legen ? </td>
        </tr>
        <tr>
            <td><input type="submit" name="uploadAlbum" value="Upload single Album"/></td>
        </tr>
    </table>

</form>

<html:form action="/admin/importDirectory">

    <table>
        <td class="formLeft">Studio Name</td>

        <td class="formRight">

            <html:select property="albumId">
                <c:forEach var="album" items="${albumList}">
                    <option value="${album.genericLevelId}">${album.longTitle}</option>
                </c:forEach>
            </html:select>

                <%--<html:select property="albumId">--%>
                <%--<html:options labelProperty="longTitle" collection="albumList" property="genericLevelId"/>--%>
                <%--</html:select>--%>
        </td>
        <tr>
            <td class="formLeft">Pfad auf dem Server:</td>
            <td class="formRight">
                <html:text property="imagePath" size="50"/>
            </td>
        </tr>

        <tr>
            <td class="formLeft">Display und Thumbnails kreieren?</td>
            <td class="formRight">
                <html:checkbox property="processImages"/>
            </td>
        </tr>

        <tr>
            <td><html:submit value="Import"/></td>
            <td></td>
        </tr>
    </table>

</html:form>
</body>
</html>