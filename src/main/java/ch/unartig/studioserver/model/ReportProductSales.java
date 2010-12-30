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
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 * Revision 1.1  2006/06/29 15:03:58  alex
 * reporting, download photos check in
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

/**
 * this model class is based on a view;
 * all product sales shall be reported in a simple overview;
 * each product is listed on one line
 */
public class ReportProductSales extends GeneratedReportProductSales
{


    public String toString()
    {
        StringBuffer retVal = new StringBuffer();
        retVal.append("Anlass Name : ").append(getEventName())
                .append("  // Album Name : ").append(getAlbumName())
                .append("  // Count : ").append(getCount())
                .append("  // Quantity : ").append(getQuantity())
                .append("  // Product Name  : ").append(getProductName());
        return retVal.toString();
    }

}
