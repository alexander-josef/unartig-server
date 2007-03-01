/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Nov 9, 2005$
 *
 * Copyright (c) 2005 unartig AG  --  All rights reserved
 *
 * STATUS  :
 *    $Revision$, $State$, $Name$
 *
 *    $Author$, $Locker$
 *    $Date$
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.studioserver.persistence.DAOs.PriceSegmentDAO;

/**
 * Information for the Segmentation of Prices
 */
public class PriceSegment extends GeneratedPriceSegment
{


    /**
     * 10 x 15 cm for CHF 5 - Segment (ID = 1)
     */
    private static PriceSegment _PS3;
    /**
     * 10 x 15 cm for CHF 10 - Segment (ID = 2)
     */
    private static PriceSegment _PS5;


    /**
     * initialize the static price segments
     * todo this solution should really be replaced
     */
    private static void initSegments()
    {
        PriceSegmentDAO psDao = new PriceSegmentDAO();

        try
        {
            _PS3 = psDao.load(new Long(1));
            _PS5 = psDao.load(new Long(2));
        } catch (UAPersistenceException e)
        {
//            System.out.println("PriceSegment.'static initializer'");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERROR IN PRICE SEGMENT INIT!!! ");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }
    }

    /**
     * standard noarg constructor
     */
    public PriceSegment()
    {
    }

    public boolean equals(Object obj)
    {
        PriceSegment priceSegment = (PriceSegment) obj;
        return getPriceSegmentId().equals(priceSegment.getPriceSegmentId());
    }


    public static PriceSegment get_PS3()
    {
        // lazy init ...
        if (_PS3==null)
        {
            initSegments();
        }
        return _PS3;
    }

    public static PriceSegment get_PS5()
    {
        // lazy init ...
        if (_PS5==null)
        {
            initSegments();
        }
        return _PS5;
    }
}
