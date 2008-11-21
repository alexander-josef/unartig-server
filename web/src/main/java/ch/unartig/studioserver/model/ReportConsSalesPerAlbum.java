/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 29.06.2006$
 *
 * Copyright (c) 2006 Alexander Josef,unartig AG; All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 *************************************************
 * $Log$
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.3  2006/10/11 12:52:28  alex
 * typos, unartig AG replaces Westhous
 *
 * Revision 1.2  2006/07/12 20:33:02  alex
 * reporting
 *
 * Revision 1.1  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

/**
 * this model class is based on a view;
 * all product sales shall be reported in a consolidated overview plus the most important calculations are added for the sales of each album;
 * each *album* is listed on one line
 * todo check for usage or delete
 */
public class ReportConsSalesPerAlbum extends GeneratedReportConsSalesPerAlbum
{

    public ReportConsSalesPerAlbum()
    {
    }

    /**
     * constructor that is used by the dao method
     * @param eventName
     * @param albumName
     * @param albumId
     * @param prod10_er
     * @param prod11_er
     * @param prod13_er
     * @param prod20_er
     * @param prod30x45
     * @param prod40x60
     * @param prod50x75
     * @param prodMousepad
     * @param totalPhotos
     * @param totalOrdersPerAlbum
     * @param totalChfPerAlbum
     * @param avgPhotosPerOrder
     * @param avgChfPerOrder
     */
    public ReportConsSalesPerAlbum(String eventName,String albumName, Long albumId,Integer prod10_er,Integer prod11_er,Integer prod13_er,Integer prod20_er,Integer prod30x45,Integer prod40x60, Integer prod50x75,Integer prodMousepad, Integer totalPhotos, Integer totalOrdersPerAlbum, Integer totalChfPerAlbum, Integer avgPhotosPerOrder, Integer avgChfPerOrder)
    {
        setEventName(eventName);
        setAlbumName(albumName);
        setAlbumId(albumId);
        setProd10_er(prod10_er);
        setProd11_er(prod11_er);
        setProd13_er(prod13_er);
        setProd20_er(prod20_er);
        setProd30x45(prod30x45);
        setProd40x60(prod40x60);
        setProd50x75(prod50x75);
        setProdMousepad(prodMousepad);
        setTotalPhotosOrdered(totalPhotos);
        setTotalOrdersPerAlbum(totalOrdersPerAlbum);
        setTotalSalesChfPerAlbum(totalChfPerAlbum);
        setAvgPhotosPerOrder(avgPhotosPerOrder);
        setAvgSaleChfPerOrder(avgChfPerOrder);
    }
}
