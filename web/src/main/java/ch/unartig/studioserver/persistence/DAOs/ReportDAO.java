/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 27.06.2006$
 *
 * Copyright (c) 2006 Alexander Josef,WEST House Entertainment AG; All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 *************************************************
 * $Log$
 * Revision 1.1  2007/03/01 18:23:42  alex
 * initial commit maven setup no history
 *
 * Revision 1.5  2006/12/20 19:38:59  alex
 * reporting for euros done
 *
 * Revision 1.4  2006/12/15 22:45:39  alex
 * adding sales report for digital fotos
 *
 * Revision 1.3  2006/12/15 16:26:03  alex
 * reporting works; averages and totals are still incorrect
 *
 * Revision 1.2  2006/07/12 20:33:02  alex
 * reporting
 *
 * Revision 1.1  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 ****************************************************************/
package ch.unartig.studioserver.persistence.DAOs;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.ReportConsSalesPerAlbum;
import ch.unartig.studioserver.model.ReportProductSales;
import ch.unartig.studioserver.persistence.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * all db related query-methods for the report
 */
public class ReportDAO
{
    Logger _logger = Logger.getLogger(getClass().getName());

    /**
     * @return
     * @throws UAPersistenceException
     */
    public List listProductSales() throws UAPersistenceException
    {
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(ReportProductSales.class)
                .addOrder(Order.asc("eventName"));
        return c.list();

    }

    public List listConsSalesPerAlbum() throws UAPersistenceException
    {
        Criteria c = HibernateUtil.currentSession()
                .createCriteria(ReportConsSalesPerAlbum.class)
                .addOrder(Order.asc("eventName"));
        return c.list();
    }

    /**
     * return a report by dates. uses a query as follows:
     * <pre>
     * </pre>
     *
     * if either of the two dates, start date or end date, is null, no date filter will be applied
     *
     * @param startDate start date for this report, or null for no date filter
     * @param endDate end date for this report (inclusive), or null for no date filter
     * @return a list of records of ReportConsSalesPerAlbumDate
     * @throws UAPersistenceException
     */
    public List listConsSalesPerAlbum(Date startDate, Date endDate) throws UAPersistenceException
    {
        // todo: column names of this view 'ReportConsSalesPerAlbumDate' are wrong or misleading. the view is not 'per album' but 'per order'
        // todo: fix in mapping and in view!
        // todo: rename the view. it's not per album, the view is per date (per order)
        List retVal;
        String query;
        Map params = new HashMap();
        debugQuery();
        System.out.println("********** Query Param 3***********");
        query = "select new ch.unartig.studioserver.model.ReportConsSalesPerAlbumDate( " +
                "   report.eventName, " +
                "   report.albumName, " +
                "   report.albumId, " +
                "   sum(prod10_er_chf), " +
                "   sum(prod11_er_chf), " +
                "   sum(prod13_er_chf), " +
                "   sum(prod20_er_chf), " +
                "   sum(prod30x45_chf), " +
                "   sum(prod40x60_chf), " +
                "   sum(prod50x75_chf), " +
                "   sum(prodMousepad_chf), " +
                "   sum(prodDigiFoto_chf), " +
                "   sum(prodDigitalNegative_chf), " +
                "   sum(totalPhotosOrdered_chf), " + // Total Photos
                "   sum(totalOrdersPerAlbumDate_chf) , " + // Total Best Per Album
                "   sum(totalSalesPerAlbumDate_chf), " + // Total CHF per Album
                "   sum(prod10_er_eur), " +
                "   sum(prod11_er_eur), " +
                "   sum(prod13_er_eur), " +
                "   sum(prod20_er_eur), " +
                "   sum(prod30x45_eur), " +
                "   sum(prod40x60_eur), " +
                "   sum(prod50x75_eur), " +
                "   sum(prodMousepad_eur), " +
                "   sum(prodDigiFoto_eur), " +
                "   sum(prodDigitalNegative_eur), " +
                "   sum(totalPhotosOrdered_eur), " + // Total Photos
                "   sum(totalOrdersPerAlbumDate_eur) , " + // Total Best Per Album
                "   sum(totalSalesPerAlbumDate_eur) " + // Total CHF per Album
//                "   avg(avgPhotosPerOrder), " + // average of photos ordered per order todo this should be "totalPhotosOrdered" / sum(totalOrdersPerAlbumDate) ???
//                "   avg(avgSaleChfPerOrder) " +
                "   ) " + // close from 'new ....'
                " from ReportConsSalesPerAlbumDate report ";
        if (startDate != null && endDate != null)
        {
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            System.out.println("params = " + params);
            query += " where report.date between :startDate and :endDate ";
        }
        query += " group by report.eventName,report.albumName,report.albumId ";
        retVal = HibernateUtil.find(query, params);
        return retVal;

/*
        query = "select new ch.unartig.studioserver.model.ReportConsSalesPerAlbum( report.eventName,report.albumName, report.albumId, " +
                "   sum(prod10_er), " +
                "   sum(prod11_er)," +
                "   sum(prod13_er)," +
                "   sum(prod20_er)," +
                "   sum(prod30x45)," +
                "   sum(prod40x60)," +
                "   sum(prod50x75)," +
                "   sum(prodMousepad), " +
                "   (sum(prod10_er) + sum(prod11_er) + sum(prod13_er) + sum(prod20_er) + sum(prod30x45) + sum(prod40x60) + sum(prod50x75) + sum(prodMousepad))," + // Total Photos
                "   count(totalPhotosOrdered) ," + // Total Best Per Album
                "   sum(TOTAL_CHF)," + // Total CHF per Album
                "   (sum(prod10_er) + sum(prod11_er) + sum(prod13_er) + sum(prod20_er) + sum(prod30x45) + sum(prod40x60) + sum(prod50x75) + sum(prodMousepad)) / count(`TOTAL_Best.`), " +
                "   sum(TOTAL_CHF) / count(`TOTAL_Best.`)  ) " +
                "from ReportConsSalesPerAlbumDate report ";
*/

    }

    /**
     * make a query without instantiating the objects and print out types
     * @throws UAPersistenceException
     */
    private void debugQuery() throws UAPersistenceException
    {
        _logger.debug("######################### DEBUG QUERY #######################");
        System.out.println("********** Query Param 3***********");
        Map params = new HashMap();

        try
        {
            String query = "select  " +
                    "   report.eventName, " +
                    "   report.albumName, " +
                    "   report.albumId, " +
                    "   sum(prod10_er_chf), " +
                    "   sum(prod11_er_chf), " +
                    "   sum(prod13_er_chf), " +
                    "   sum(prod20_er_chf), " +
                    "   sum(prod30x45_chf), " +
                    "   sum(prod40x60_chf), " +
                    "   sum(prod50x75_chf), " +
                    "   sum(prodMousepad_chf), " +
                    "   sum(prodDigiFoto_chf), " +
                    "   sum(prodDigitalNegative_chf), " +
                    "   sum(totalPhotosOrdered_chf), " + // Total Photos
                    "   sum(totalOrdersPerAlbumDate_chf) , " + // Total Best Per Album
                    "   sum(totalSalesPerAlbumDate_chf), " + // Total CHF per Album
                    "   sum(prod10_er_eur), " +
                    "   sum(prod11_er_eur), " +
                    "   sum(prod13_er_eur), " +
                    "   sum(prod20_er_eur), " +
                    "   sum(prod30x45_eur), " +
                    "   sum(prod40x60_eur), " +
                    "   sum(prod50x75_eur), " +
                    "   sum(prodMousepad_eur), " +
                    "   sum(prodDigiFoto_eur), " +
                    "   sum(prodDigitalNegative_eur), " +
                    "   sum(totalPhotosOrdered_eur), " + // Total Photos
                    "   sum(totalOrdersPerAlbumDate_eur) , " + // Total Best Per Album
                    "   sum(totalSalesPerAlbumDate_eur) " + // Total CHF per Album
//                "   avg(avgPhotosPerOrder), " + // average of photos ordered per order todo this should be "totalPhotosOrdered" / sum(totalOrdersPerAlbumDate) ???
//                "   avg(avgSaleChfPerOrder) " +
                    " from ReportConsSalesPerAlbumDate report ";
            query += " group by report.eventName,report.albumName,report.albumId ";
            List result = HibernateUtil.find(query,params);


            for (int i = 0; i < result.size(); i++)
            {
                Object o = result.get(i);

                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                System.out.println("o.getClass().getName() = " + o.getClass().getName());
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                Object[] obAr = (Object[])o;

                for (int j = 0; j < obAr.length; j++)
                {
                    Object o1 = obAr[j];
                    System.out.println("o1.getClass().getName() = " + o1.getClass().getName());
                }
            }
        } catch (UAPersistenceException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch(Exception e2)
        {
            e2.printStackTrace();
        }


    }
}
