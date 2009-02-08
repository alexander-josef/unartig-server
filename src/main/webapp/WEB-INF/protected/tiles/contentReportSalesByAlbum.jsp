<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reportBean" type="ch.unartig.studioserver.beans.ReportBean" scope="request"/>

<html:xhtml/>

<div class="content_wide">
    <div class="content_left496_middle">
        <html:form action="/admin/reportProductSales">
            <html:hidden property="exportToExcel" value="false"/>
            Startdatum: <html:text property="startDate"/> &nbsp;&nbsp; Enddatum (inclusive): <html:text property="endDate"/> <br/>
            (Format: dd.MM.yyyy)<br/>
            <html:submit value="show report"/>
            <html:submit value="export to excel" onclick="exportToExcel.value='true';return true;"/>
        </html:form>

        <h1>Consolidated Sales by Album in CHF</h1>
        <table border="1">
            <th>Event</th>
            <th>Album</th>
            <th>10-er</th>
            <th>11-er</th>
            <th>13-er</th>
            <th>20-er</th>
            <th>30x45</th>
            <th>40x60</th>
            <th>50x75</th>
            <th>Mousepad</th>
            <th>Digi Foto</th>
            <th>Digital Negative</th>
            <th>Total Fotos bestellt</th>
            <th>Total Bestellungen / Album</th>
            <th>Umsatz CHF / Album</th>
            <th>Durchschn. # Fotos pro Bestellung</th>
            <th>Durchschn. Hoehe in CHF pro Bestellung</th>

            <c:forEach var="reportRow" items="${reportBean.consSalesPerAlbum}">
                <tr>
                    <td>${reportRow.eventName}</td>
                    <td>${reportRow.albumName}</td>
                    <td>${reportRow.prod10_er_chf}</td>
                    <td>${reportRow.prod11_er_chf}</td>
                    <td>${reportRow.prod13_er_chf}</td>
                    <td>${reportRow.prod20_er_chf}</td>
                    <td>${reportRow.prod30x45_chf}</td>
                    <td>${reportRow.prod40x60_chf}</td>
                    <td>${reportRow.prod50x75_chf}</td>
                    <td>${reportRow.prodMousepad_chf}</td>
                    <td>${reportRow.prodDigiFoto_chf}</td>
                    <td>${reportRow.prodDigitalNegative_chf}</td>
                    <td>${reportRow.totalPhotosOrdered_chf}</td>
                    <td>${reportRow.totalOrdersPerAlbumDate_chf}</td>
                    <td>${reportRow.totalSalesPerAlbumDate_chf}</td>
                    <!--todo replace the next two calculations ....-->
                    <td>TODO</td>
                    <td>TODO</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="15">&nbsp;</td>
            </tr>
            <tr style="font-weight:bold;">
                <td colspan="2">Grand Total &nbsp;</td>
                <td>${reportBean.total10_er_chf}</td>
                <td>${reportBean.total11_er_chf}</td>
                <td>${reportBean.total13_er_chf}</td>
                <td>${reportBean.total20_er_chf}</td>
                <td>${reportBean.total30x45_chf}</td>
                <td>${reportBean.total40x60_chf}</td>
                <td>${reportBean.total50x75_chf}</td>
                <td>${reportBean.totalMousepad_chf}</td>
                <td>TODO</td>
                <td>TODO</td>
                <td>${reportBean.grandTotalPhotosOrdered_chf}</td>
                <td>${reportBean.grandTotalOrders_chf}</td>
                <td>${reportBean.grandTotalSalesCHF}</td>
                <td colspan="2"></td>
            </tr>

        </table>

        <h1>Consolidated Sales by Album in EUR</h1>
        <table border="1">
            <th>Event</th>
            <th>Album</th>
            <th>10-er</th>
            <th>11-er</th>
            <th>13-er</th>
            <th>20-er</th>
            <th>30x45</th>
            <th>40x60</th>
            <th>50x75</th>
            <th>Mousepad</th>
            <th>Digi Foto</th>
            <th>Digital Negative</th>
            <th>Total Fotos bestellt</th>
            <th>Total Bestellungen / Album</th>
            <th>Umsatz CHF / Album</th>
            <th>Durchschn. # Fotos pro Bestellung</th>
            <th>Durchschn. Hoehe in CHF pro Bestellung</th>

            <c:forEach var="reportRow" items="${reportBean.consSalesPerAlbum}">
                <tr>
                    <td>${reportRow.eventName}</td>
                    <td>${reportRow.albumName}</td>
                    <td>${reportRow.prod10_er_eur}</td>
                    <td>${reportRow.prod11_er_eur}</td>
                    <td>${reportRow.prod13_er_eur}</td>
                    <td>${reportRow.prod20_er_eur}</td>
                    <td>${reportRow.prod30x45_eur}</td>
                    <td>${reportRow.prod40x60_eur}</td>
                    <td>${reportRow.prod50x75_eur}</td>
                    <td>${reportRow.prodMousepad_eur}</td>
                    <td>${reportRow.prodDigiFoto_eur}</td>
                    <td>${reportRow.prodDigitalNegative_eur}</td>
                    <td>${reportRow.totalPhotosOrdered_eur}</td>
                    <td>${reportRow.totalOrdersPerAlbumDate_eur}</td>
                    <td>${reportRow.totalSalesPerAlbumDate_eur}</td>
                    <!--todo replace the next two calculations ....-->
                    <td>TODO</td>
                    <td>TODO</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="15">&nbsp;</td>
            </tr>
            <tr style="font-weight:bold;">
                <td colspan="2">Grand Total &nbsp;</td>
                <td>${reportBean.total10_er_chf}</td>
                <td>${reportBean.total11_er_chf}</td>
                <td>${reportBean.total13_er_chf}</td>
                <td>${reportBean.total20_er_chf}</td>
                <td>${reportBean.total30x45_chf}</td>
                <td>${reportBean.total40x60_chf}</td>
                <td>${reportBean.total50x75_chf}</td>
                <td>${reportBean.totalMousepad_chf}</td>
                <td>TODO</td>
                <td>TODO</td>
                <td>${reportBean.grandTotalPhotosOrdered_chf}</td>
                <td>${reportBean.grandTotalOrders_chf}</td>
                <td>${reportBean.grandTotalSalesCHF}</td>
                <td colspan="2"></td>
            </tr>

        </table>
    </div>
</div>
