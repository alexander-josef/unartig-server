/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since 10.11.2006$
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
 * Revision 1.2  2006/11/12 11:58:47  alex
 * dynamic album ads
 *
 * Revision 1.1  2006/11/10 15:55:30  alex
 * dynamic album ads
 *
 ****************************************************************/
package ch.unartig.studioserver.model;


/**
 *
 */
public class AlbumAdvertisment extends GeneratedAlbumAdvertisment
{


    /**
     * default constructor
     */
    public AlbumAdvertisment()
    {
    }

    /**
     * full constructor
     * @param position
     * @param level
     * @param adText
     */
    public AlbumAdvertisment(String position,GenericLevel level, String adText)
    {
        this.setPosition(position);
        this.setAlbum(level);
        this.setAdLinkText(adText);
    }
}
