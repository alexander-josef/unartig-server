<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:xhtml/>

<div class="contentN rightalign padding19bottom"><html:img bundle="IMAGES" srcKey="title.welcome.src" altKey="title.welcome.alt"/></div>

<div class="contentNplus rightalign">
    <table id="home_unartig_statement">
        <tr>
            <th id="framecorner_topleft_home"></th>
            <th id="frame_top"></th>
            <th id="framecorner_topright_home"></th>
        </tr>
        <tr>
            <td id="frame_left"></td>
            <td id="display_imageholder">
                <h1><bean:message bundle="CONTENT" key="slogan"/></h1>
            </td>
            <td id="frame_right"></td>
        </tr>
        <tr>
            <th id="framecorner_bottomleft"></th>
            <th id="frame_bottom"></th>
            <th id="framecorner_bottomright"></th>
        </tr>
    </table>
</div>


<div class="contentN rightalign padding19both"><html:img bundle="IMAGES" srcKey="title.recent.event.src" altKey="title.recent.event.alt"/></div>

<div class="contentNplus rightalign padding19bottom">
    <ul class="aktuell">
        <li>
            <div><p class="aktuell_date">20.01.2007</p>

                <html:link action="/overview/240/Fromm-Ball-2007/show">
                    <img src="/DATA/240/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Fromm-Ball 2007</p>

                <p class="aktuell_picn">794 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">25.11.2006</p>

                <html:link action="/overview/234/Polyball-2006/show">
                    <img src="/DATA/234/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Polyball 2006</p>

                <p class="aktuell_picn">6'881 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">22.09.2006</p>

                <html:link action="/overview/227/Offiziersball-Logistik-Bern-2006/show">
                    <img src="/DATA/227/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Offiziersball Bern, Logistik-OS 2006</p>

                <p class="aktuell_picn">865 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">11-13.07.2006</p>

                <html:link action="/overview/208/Firmenlauf-2006/show">
                    <img src="/DATA/208/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Schweizer Firmenlauf 2006</p>

                <p class="aktuell_picn">951 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">21.06.2006</p>

                <html:link action="/overview/203/Forchlauf-2006/show">
                    <img src="/DATA/203/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Forchlauf 2006</p>

                <p class="aktuell_picn">1'185 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">13.05.2006</p>

                <html:link action="/overview/197/Offiziersball-Panzer-Thun-06/show">
                    <img src="/DATA/197/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Offiziersball Thun, Panzer-OS 2006</p>

                <p class="aktuell_picn">443 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>
        <li>
            <div><p class="aktuell_date">13.05.2006</p>

                <html:link action="/overview/193/Utokulm-Fruehlingsball-2006/show">
                    <img src="/DATA/193/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Hotel Uto Kulm, Frühlingsball 2006</p>

                <p class="aktuell_picn">472 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>

        <li>
            <div><p class="aktuell_date">06.05.2006</p>

                <html:link action="/overview/160/SOLA-Stafette-2006/show">
                    <img src="/DATA/160/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">ASVZ<br/> Sola-Stafette 2006</p>
                
                <p class="aktuell_picn">23'234 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>

        <li>
            <div><p class="aktuell_date">29.04.2006</p>

                <html:link action="/overview/154/Uniball-St-Gallen-2006/show">
                    <img src="/DATA/154/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Uniball St. Gallen 2006</p>

                <p class="aktuell_picn">1'107 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>

        <li>
            <div><p class="aktuell_date">1.4.2006</p>

                <html:link action="/overview/151/Kantiball-Aarau-2006/show">
                    <img src="/DATA/151/index.jpg" alt=""/>
                </html:link>

                <p class="aktuell_name">Kantiball Aarau 2006</p>

                <p class="aktuell_picn">1'903 <bean:message bundle="CONTENT" key="images"/></p>
            </div>
        </li>


    </ul>
</div>

<%--

        <img class="image_title" src="/images/lang_spec_images/title_photographers_de.gif" alt=""/>
        <ul id="photographers">
            <li><div><a href=""><img src="/images/photographers/alex.gif" alt=""/></a>

                <p>alex</p></div></li>
            <li><div><a href=""><img src="/images/photographers/urban.gif" alt=""/></a>

                <p>urban</p></div></li>
        </ul>

--%>
<!--</div>-->
