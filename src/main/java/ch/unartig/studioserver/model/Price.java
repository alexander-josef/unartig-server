/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 07.02.2007$
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
 * Revision 1.2  2007/03/13 16:55:03  alex
 * template for properties
 *
 * Revision 1.1  2007/03/01 18:23:41  alex
 * initial commit maven setup no history
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Price extends GeneratedPrice
{
    public static NumberFormat monetaryAmountFormat = DecimalFormat.getInstance();

    static
    {
        // initialize the NumberFormatter
        monetaryAmountFormat.setMinimumFractionDigits(2);
        monetaryAmountFormat.setMaximumFractionDigits(2);
    }

    public Price()
    {
    }

}
