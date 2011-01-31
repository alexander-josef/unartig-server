/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 27.06.2006$
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
 * Revision 1.5  2006/12/20 19:38:59  alex
 * reporting for euros done
 *
 * Revision 1.4  2006/12/15 16:26:03  alex
 * reporting works; averages and totals are still incorrect
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
package ch.unartig.studioserver.beans;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.model.ReportConsSalesPerAlbumDate;
import ch.unartig.studioserver.persistence.DAOs.ReportDAO;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ReportBean
{
    /**
     * Simple Date Format to parse or format a pattern "dd.MM.yyyy"
     */
    public static final SimpleDateFormat _SIMPLE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    private List consSalesPerAlbum;
    private Float grandTotalSalesCHF;
    private int grandTotalPhotosOrdered_chf;
    private int grandTotalOrders_chf;
    private int total10_er_chf;
    private int total11_er_chf;
    private int total13_er_chf;
    private int total20_er_chf;
    private int total30x45_chf;
    private int total40x60_chf;
    private int total50x75_chf;
    private int totalMousepad_chf;
    private Float grandTotalSalesEUR;
    private int grandTotalPhotosOrdered_eur;
    private int grandTotalOrders_eur;
    private int total10_er_eur;
    private int total11_er_eur;
    private int total13_er_eur;
    private int total20_er_eur;
    private int total30x45_eur;
    private int total40x60_eur;
    private int total50x75_eur;
    private int totalMousepad_eur;

    private ReportBean()
    {
    }

    public void prepareProductSalesReport()
    {
        // todo
        //
    }


    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws UAPersistenceException
     */
    public static ReportBean prepareConsSalesPerAlbum(Date startDate, Date endDate) throws UAPersistenceException
    {
        ReportBean instance = new ReportBean();
        ReportDAO reportDAO = new ReportDAO();
        List consSalesPerAlbum = reportDAO.listConsSalesPerAlbum(startDate, endDate);
        Collections.sort(consSalesPerAlbum, ReportConsSalesPerAlbumDate.eventAlbumComparator);
        instance.setConsSalesPerAlbum(consSalesPerAlbum);
        instance.calcGrandTotals();
        return instance;
    }

    /**
     * prepare getters for the grand total line in the table
     */
    private void calcGrandTotals()
    {
        float totalSalesCHF =0;
        float totalSalesEUR =0;

        for (int i = 0; i < consSalesPerAlbum.size(); i++)
        {
            ReportConsSalesPerAlbumDate reportConsSalesPerAlbumDate = (ReportConsSalesPerAlbumDate) consSalesPerAlbum.get(i);
            totalSalesCHF = totalSalesCHF + reportConsSalesPerAlbumDate.getTotalSalesPerAlbumDate_chf().floatValue();
            total10_er_chf +=reportConsSalesPerAlbumDate.getProd10_er_chf().intValue();
            total11_er_chf +=reportConsSalesPerAlbumDate.getProd11_er_chf().intValue();
            total13_er_chf +=reportConsSalesPerAlbumDate.getProd13_er_chf().intValue();
            total20_er_chf +=reportConsSalesPerAlbumDate.getProd20_er_chf().intValue();
            total30x45_chf +=reportConsSalesPerAlbumDate.getProd30x45_chf().intValue();
            total40x60_chf +=reportConsSalesPerAlbumDate.getProd40x60_chf().intValue();
            total50x75_chf +=reportConsSalesPerAlbumDate.getProd50x75_chf().intValue();
            totalMousepad_chf +=reportConsSalesPerAlbumDate.getProdMousepad_chf().intValue();
            grandTotalPhotosOrdered_chf +=reportConsSalesPerAlbumDate.getTotalPhotosOrdered_chf().intValue();
            grandTotalOrders_chf +=reportConsSalesPerAlbumDate.getTotalOrdersPerAlbumDate_chf().intValue();
            totalSalesEUR = totalSalesEUR + reportConsSalesPerAlbumDate.getTotalSalesPerAlbumDate_eur().floatValue();
            total10_er_eur +=reportConsSalesPerAlbumDate.getProd10_er_eur().intValue();
            total11_er_eur +=reportConsSalesPerAlbumDate.getProd11_er_eur().intValue();
            total13_er_eur +=reportConsSalesPerAlbumDate.getProd13_er_eur().intValue();
            total20_er_eur +=reportConsSalesPerAlbumDate.getProd20_er_eur().intValue();
            total30x45_eur +=reportConsSalesPerAlbumDate.getProd30x45_eur().intValue();
            total40x60_eur +=reportConsSalesPerAlbumDate.getProd40x60_eur().intValue();
            total50x75_eur +=reportConsSalesPerAlbumDate.getProd50x75_eur().intValue();
            totalMousepad_eur +=reportConsSalesPerAlbumDate.getProdMousepad_eur().intValue();
            grandTotalPhotosOrdered_eur +=reportConsSalesPerAlbumDate.getTotalPhotosOrdered_eur().intValue();
            grandTotalOrders_eur +=reportConsSalesPerAlbumDate.getTotalOrdersPerAlbumDate_eur().intValue();
        }
        grandTotalSalesCHF=new Float(totalSalesCHF);
        grandTotalSalesEUR=new Float(totalSalesCHF);
    }


    public List getConsSalesPerAlbum()
    {
        return consSalesPerAlbum;
    }

    public void setConsSalesPerAlbum(List consSalesPerAlbum)
    {
        this.consSalesPerAlbum = consSalesPerAlbum;
    }


    public Float getGrandTotalSalesCHF()
    {
        return grandTotalSalesCHF;
    }

    public void setGrandTotalSalesCHF(Float grandTotalSalesCHF)
    {
        this.grandTotalSalesCHF = grandTotalSalesCHF;
    }

    public int getGrandTotalPhotosOrdered_chf()
    {
        return grandTotalPhotosOrdered_chf;
    }

    public void setGrandTotalPhotosOrdered_chf(int grandTotalPhotosOrdered_chf)
    {
        this.grandTotalPhotosOrdered_chf = grandTotalPhotosOrdered_chf;
    }

    public int getGrandTotalOrders_chf()
    {
        return grandTotalOrders_chf;
    }

    public void setGrandTotalOrders_chf(int grandTotalOrders_chf)
    {
        this.grandTotalOrders_chf = grandTotalOrders_chf;
    }

    public int getTotal10_er_chf()
    {
        return total10_er_chf;
    }

    public void setTotal10_er_chf(int total10_er_chf)
    {
        this.total10_er_chf = total10_er_chf;
    }

    public int getTotal11_er_chf()
    {
        return total11_er_chf;
    }

    public void setTotal11_er_chf(int total11_er_chf)
    {
        this.total11_er_chf = total11_er_chf;
    }

    public int getTotal13_er_chf()
    {
        return total13_er_chf;
    }

    public void setTotal13_er_chf(int total13_er_chf)
    {
        this.total13_er_chf = total13_er_chf;
    }

    public int getTotal20_er_chf()
    {
        return total20_er_chf;
    }

    public void setTotal20_er_chf(int total20_er_chf)
    {
        this.total20_er_chf = total20_er_chf;
    }

    public int getTotal30x45_chf()
    {
        return total30x45_chf;
    }

    public void setTotal30x45_chf(int total30x45_chf)
    {
        this.total30x45_chf = total30x45_chf;
    }

    public int getTotal40x60_chf()
    {
        return total40x60_chf;
    }

    public void setTotal40x60_chf(int total40x60_chf)
    {
        this.total40x60_chf = total40x60_chf;
    }

    public int getTotal50x75_chf()
    {
        return total50x75_chf;
    }

    public void setTotal50x75_chf(int total50x75_chf)
    {
        this.total50x75_chf = total50x75_chf;
    }

    public int getTotalMousepad_chf()
    {
        return totalMousepad_chf;
    }

    public void setTotalMousepad_chf(int totalMousepad_chf)
    {
        this.totalMousepad_chf = totalMousepad_chf;
    }


    public Float getGrandTotalSalesEUR()
    {
        return grandTotalSalesEUR;
    }

    public void setGrandTotalSalesEUR(Float grandTotalSalesEUR)
    {
        this.grandTotalSalesEUR = grandTotalSalesEUR;
    }

    public int getGrandTotalPhotosOrdered_eur()
    {
        return grandTotalPhotosOrdered_eur;
    }

    public void setGrandTotalPhotosOrdered_eur(int grandTotalPhotosOrdered_eur)
    {
        this.grandTotalPhotosOrdered_eur = grandTotalPhotosOrdered_eur;
    }

    public int getGrandTotalOrders_eur()
    {
        return grandTotalOrders_eur;
    }

    public void setGrandTotalOrders_eur(int grandTotalOrders_eur)
    {
        this.grandTotalOrders_eur = grandTotalOrders_eur;
    }

    public int getTotal10_er_eur()
    {
        return total10_er_eur;
    }

    public void setTotal10_er_eur(int total10_er_eur)
    {
        this.total10_er_eur = total10_er_eur;
    }

    public int getTotal11_er_eur()
    {
        return total11_er_eur;
    }

    public void setTotal11_er_eur(int total11_er_eur)
    {
        this.total11_er_eur = total11_er_eur;
    }

    public int getTotal13_er_eur()
    {
        return total13_er_eur;
    }

    public void setTotal13_er_eur(int total13_er_eur)
    {
        this.total13_er_eur = total13_er_eur;
    }

    public int getTotal20_er_eur()
    {
        return total20_er_eur;
    }

    public void setTotal20_er_eur(int total20_er_eur)
    {
        this.total20_er_eur = total20_er_eur;
    }

    public int getTotal30x45_eur()
    {
        return total30x45_eur;
    }

    public void setTotal30x45_eur(int total30x45_eur)
    {
        this.total30x45_eur = total30x45_eur;
    }

    public int getTotal40x60_eur()
    {
        return total40x60_eur;
    }

    public void setTotal40x60_eur(int total40x60_eur)
    {
        this.total40x60_eur = total40x60_eur;
    }

    public int getTotal50x75_eur()
    {
        return total50x75_eur;
    }

    public void setTotal50x75_eur(int total50x75_eur)
    {
        this.total50x75_eur = total50x75_eur;
    }

    public int getTotalMousepad_eur()
    {
        return totalMousepad_eur;
    }

    public void setTotalMousepad_eur(int totalMousepad_eur)
    {
        this.totalMousepad_eur = totalMousepad_eur;
    }
}
