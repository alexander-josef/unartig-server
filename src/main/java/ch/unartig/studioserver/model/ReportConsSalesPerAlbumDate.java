/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 06.07.2006$
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
 * Revision 1.6  2006/12/20 19:38:59  alex
 * reporting for euros done
 *
 * Revision 1.5  2006/12/15 22:45:39  alex
 * adding sales report for digital fotos
 *
 * Revision 1.4  2006/12/15 16:26:03  alex
 * reporting works; averages and totals are still incorrect
 *
 * Revision 1.3  2006/11/21 21:56:28  alex
 * small fixes
 *
 * Revision 1.2  2006/10/11 12:52:28  alex
 * typos, unartig AG replaces Westhous
 *
 * Revision 1.1  2006/07/12 20:33:02  alex
 * reporting
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * this model class is based on a view;
 * all product sales shall be reported in a consolidated overview plus the most important calculations are added for the sales of each order;
 * each order is listed on one line
 * grouping by date, this class serves also for reporting sales by album ! or event!
 */
public class ReportConsSalesPerAlbumDate extends GeneratedReportConsSalesPerAlbumDate
{

    public ReportConsSalesPerAlbumDate()
    {
    }

    /**
     * constructor that is used by hql query to report sales by album. grouped by date, so variable date in unused
     * @param eventName Name of the reported event
     * @param albumName name of the reported album
     * @param albumId Id as in the database and filestructure
     * @param prod10_er_chf sum of 10-er product
     * @param prod11_er_chf sum of 10-er product
     * @param prod13_er_chf sum of 10-er product
     * @param prod20_er_chf sum of 10-er product
     * @param prod30x45_chf sum of 10-er product
     * @param prod40x60_chf sum of 10-er product
     * @param prod50x75_chf sum of 10-er product
     * @param prodMousepad_chf sum of 10-er product
     * @param totalPhotos_chf sum total photos ordered
     * @param totalOrdersPerAlbum_chf count total fotos ordered total best. per album
     * @param totalPerAlbum_chf sum sales CHF per album
     * @param prodDigiFoto_chf sum of digi foto sold
     * @param prodDigitalNegative_chf sum of digital negative fotos sold
     * @param prod10_er_eur
     * @param prod11_er_eur
     * @param prod13_er_eur
     * @param prod20_er_eur
     * @param prod30x45_eur
     * @param prod30x45_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod40x60_eur
     * @param prod50x75_eur
     * @param prodMousepad_eur
     * @param prodDigiFoto_eur
     * @param prodDigitalNegative_eur
     * @param totalPhotos_eur
     * @param totalOrdersPerAlbum_eur
     * @param totalPerAlbum_eur
     */
//    public ReportConsSalesPerAlbumDate(String eventName,String albumName, Long albumId,Long prod10_er_chf,Long prod11_er_chf,Long prod13_er_chf,Long prod20_er_chf,Long prod30x45_chf,Long prod40x60_chf, Long prod50x75_chf,Long  prodMousepad_chf,Long  prodDigiFoto_chf, Long  prodDigitalNegative_chf, Long totalPhotos_chf,Long totalOrdersPerAlbum_chf, BigDecimal totalPerAlbum_chf,Long prod10_er_eur,Long prod11_er_eur,Long prod13_er_eur,Long prod20_er_eur,Long prod30x45_eur,Long prod40x60_eur, Long prod50x75_eur,Long  prodMousepad_eur,Long  prodDigiFoto_eur, Long  prodDigitalNegative_eur, Long totalPhotos_eur,Long totalOrdersPerAlbum_eur, BigDecimal totalPerAlbum_eur, Double avgPhotosPerOrder, Double avgChfPerOrder)
    public ReportConsSalesPerAlbumDate(String eventName,String albumName, Long albumId,Long prod10_er_chf,Long prod11_er_chf,Long prod13_er_chf,Long prod20_er_chf,Long prod30x45_chf,Long prod40x60_chf, Long prod50x75_chf,Long  prodMousepad_chf,Long  prodDigiFoto_chf, Long  prodDigitalNegative_chf, Long totalPhotos_chf,Long totalOrdersPerAlbum_chf, BigDecimal totalPerAlbum_chf,Long prod10_er_eur,Long prod11_er_eur,Long prod13_er_eur,Long prod20_er_eur,Long prod30x45_eur,Long prod40x60_eur, Long prod50x75_eur,Long  prodMousepad_eur,Long  prodDigiFoto_eur, Long  prodDigitalNegative_eur, Long totalPhotos_eur,Long totalOrdersPerAlbum_eur, BigDecimal totalPerAlbum_eur)
    {
        System.out.println(" debug constructor");
        setEventName(eventName);
        setAlbumName(albumName);
        setAlbumId(albumId);
        setProd10_er_chf(prod10_er_chf);
        setProd11_er_chf(prod11_er_chf);
        setProd13_er_chf(prod13_er_chf);
        setProd20_er_chf(prod20_er_chf);
        setProd30x45_chf(prod30x45_chf);
        setProd40x60_chf(prod40x60_chf);
        setProd50x75_chf(prod50x75_chf);
        setProdMousepad_chf(prodMousepad_chf);
        setProdDigiFoto_chf(prodDigiFoto_chf);
        setProdDigitalNegative_chf(prodDigitalNegative_chf);
        setTotalPhotosOrdered_chf(totalPhotos_chf);
        setTotalOrdersPerAlbumDate_chf(totalOrdersPerAlbum_chf);
        setTotalSalesPerAlbumDate_chf(totalPerAlbum_chf);
        setProd10_er_eur(prod10_er_eur);
        setProd11_er_eur(prod11_er_eur);
        setProd13_er_eur(prod13_er_eur);
        setProd20_er_eur(prod20_er_eur);
        setProd30x45_eur(prod30x45_eur);
        setProd40x60_eur(prod40x60_eur);
        setProd50x75_eur(prod50x75_eur);
        setProdMousepad_eur(prodMousepad_eur);
        setProdDigiFoto_eur(prodDigiFoto_eur);
        setProdDigitalNegative_eur(prodDigitalNegative_eur);
        setTotalPhotosOrdered_eur(totalPhotos_eur);
        setTotalOrdersPerAlbumDate_eur(totalOrdersPerAlbum_eur);
        setTotalSalesPerAlbumDate_eur(totalPerAlbum_eur);
//        setAvgPhotosPerOrder(avgPhotosPerOrder);
//        setAvgSaleChfPerOrder(avgChfPerOrder);
    }

    /**
     * compares first by eventname, then by albumname
     */
    public static Comparator eventAlbumComparator = new Comparator()
    {
        public int compare(Object o1, Object o2)
        {
            ReportConsSalesPerAlbumDate report1 = ((ReportConsSalesPerAlbumDate) o1);
            ReportConsSalesPerAlbumDate report2 = ((ReportConsSalesPerAlbumDate) o2);
            if (report1.getEventName().equals(report2.getEventName()))
            {
                return report1.getAlbumName().compareTo(report2.getAlbumName());
            }
            return report1.getEventName().compareTo(report2.getEventName());
        }
    };
}
