<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html:xhtml/>

<html:img styleClass="leftalign" bundle="IMAGES" srcKey="sidebar.title.search.src" altKey="sidebar.title.search.alt"/>
<div class="sidebarElement">
    <c:if test="${'notime' != albumBean.album.albumType}">
        <html:form styleClass="timeselect" action="/album/${albumBean.album.genericLevelId}/${albumBean.album.event.eventGroup.navTitle}/${albumBean.album.event.navTitle}/${albumBean.album.navTitle}">
            <html:hidden property="page" value="0"/>
            <fieldset class="forms">
                <table class="sidebarContent">
                    <tr>
                        <td>
                            <h3><bean:message bundle="CONTENT" key="hour"/></h3>
                        </td>
                        <td>
                            <h3><bean:message bundle="CONTENT" key="minute"/></h3>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <html:select styleClass="hours" property="hour">
                                <html:option value="-1">--</html:option>
                                <html:option value="7"/>
                                <html:option value="8"/>
                                <html:option value="9"/>
                                <html:option value="10"/>
                                <html:option value="11"/>
                                <html:option value="12"/>
                                <html:option value="13">13</html:option>
                                <html:option value="14">14</html:option>
                                <html:option value="15">15</html:option>
                                <html:option value="16">16</html:option>
                                <html:option value="17">17</html:option>
                                <html:option value="18">18</html:option>
                                <html:option value="19">19</html:option>
                                <html:option value="20">20</html:option>
                                <html:option value="21">21</html:option>
                                <html:option value="22">22</html:option>
                                <html:option value="23">23</html:option>
                                <html:option value="00">00</html:option>
                                <html:option value="01">01</html:option>
                                <html:option value="02">02</html:option>
                                <html:option value="03">03</html:option>
                                <html:option value="04">04</html:option>
                            </html:select>
                        </td>
                        <td><html:select property="minutes" styleClass="minute">
                            <html:option value="-1">--</html:option>
                            <html:option value="00"/>
                            <html:option value="05"/>
                            <html:option value="10"/>
                            <html:option value="15"/>
                            <html:option value="20"/>
                            <html:option value="25"/>
                            <html:option value="30"/>
                            <html:option value="35"/>
                            <html:option value="40"/>
                            <html:option value="45"/>
                            <html:option value="50"/>
                            <html:option value="55"/>
                        </html:select>
                        </td>
                    </tr>
                </table>
                <div class="sidebarContent">
                    <html:image styleClass="btn" bundle="IMAGES" srcKey="btn.search.src" altKey="btn.search.alt"/>
                </div>
            </fieldset>
        </html:form>
    </c:if>
    <p  class="sidebarContent"><bean:message bundle="CONTENT" key="sidebar.time.info"/></p>
</div>

<div class="sidebarBotton pad6Left">
</div>