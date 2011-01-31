/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since 10.03.2006$
 *
 * Copyright (c) 2005 unartig AG  --  All rights reserved
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
 * Revision 1.1  2006/03/20 15:33:32  alex
 * first check in for new sports album logic and db changes
 *
 ****************************************************************/
package ch.unartig.studioserver.model;

/**
 *
 * @author Alexander Josef, 2006
 */
public class SportsEvent extends GeneratedSportsEvent
{

    /**
     * this is called via introspection from the admin action when a new SportsEvent is created.
     * todo: refactor this using a common factory
     * @param navTitle
     * @param longTitle
     * @param description
     */
    public SportsEvent(String navTitle, String longTitle, String description)
    {
        setNavTitle(navTitle);
        setLongTitle(longTitle);
        setDescription(description);
    }

    public SportsEvent()
    {
    }

    /**
     * Overridden for SportsEvent: call SportsEvent Action
     * @return URL for the SportsEvent Action
     */
    public String getIndexNavLink()
    {
        // todo: change for sportsevent: use parameter? use different Action?
        String url = "/sportsOverview/" + getGenericLevelId().toString() + "/" + getNavTitle() + "/show.html";

        return url;
    }

    /**
     * overriden
     * // todo: is this good practice?
     * @return true
     */
    public boolean isSportsEventLevel()
    {
        return true;
    }

}
