<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>

<jsp:useBean id="eventGroupList" type="java.util.List" scope="request"/>
<jsp:useBean id="eventList" type="java.util.List" scope="request"/>
<jsp:useBean id="parentList" type="java.util.List" scope="request"/>
<jsp:useBean id="priceSegmentList" type="java.util.List" scope="request"/>


<html:xhtml/>

<div class="content_wide">
    <div class="content_left496_middle">
        <html:form action="/admin/newLevel" method="POST" enctype="multipart/form-data">
            <fieldset class="forms">
                <h3>Edit Level</h3><br/>
                <table class="form_table">
                    <tr>
                        <th><p>Index picture</p></th>

                        <td class="browsButton"><html:file styleClass="navTitle" property="indexPhoto"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th/>
                        <td><br/></td>
                        <th/>
                    </tr>
                    <tr>
                         <th/>
                        <td>
                            <ul class="album">
                                <!--in each 'li' change class to either 'imageholder-landscape' or 'imageholder_portrait' depending on format-->
                                <li class="albumslide">
                                    <div class="imageholder_landscape">
                                        <html:img src="/images/slide_pic_landscape.gif" alt="index" title=""/>
                                    </div>

                                    <div class="archive_slide_info">
                                        <!--<bean:message bundle="CONTENT" key="cart.incart"/>--></div>
                                </li>
                            </ul>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <th/>
                        <td><br/></td>
                        <th/>
                    </tr>

                    <tr>
                        <th><p>Select Parent Level</p></th>
                        <td><html:select property="genericLevelId">
                            <%--<html:select property="categoryId">--%>
                            <html:options labelProperty="longTitle" collection="parentList" property="genericLevelId"/>
                        </html:select></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><p>Navigation Title</p></th>
                        <td>
                            <html:text styleClass="navTitle" maxlength="30" size="40" property="navTitle" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><p>Long Title</p></th>
                        <td>
                            <html:text styleClass="navTitle" maxlength="50" size="40" property="longTitle" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><p>Description</p></th>
                        <td>
                            <html:text styleClass="navTitle" maxlength="200" size="40" property="description" value=""/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><p>Event date</p></th>
                        <td>
                            <html:text styleClass="navTitle" maxlength="200" size="40" property="eventDateDisplay" value=""/></td>
                        <td>( dd.MM.yyyy)</td>
                    </tr>
                    <tr>
                        <th><p>Private Event?</p></th>
                        <td><html:checkbox styleClass="admin_button" property="privateEvent"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <html:image styleClass="admin_button" styleId="abschicken" src="/images/update_button.gif" alt="" title="und los"/></td>
                        <td></td>
                    </tr>
                </table>
            </fieldset>
        </html:form>
    </div>


</div>
