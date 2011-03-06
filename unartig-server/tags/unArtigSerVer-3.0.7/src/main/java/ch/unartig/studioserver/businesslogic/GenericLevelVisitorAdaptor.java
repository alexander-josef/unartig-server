/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since 15.02.2006$
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
 * Revision 1.1  2006/02/15 15:57:03  alex
 * bug [968] fixed. admin interface does that now
 *
 ****************************************************************/
package ch.unartig.studioserver.businesslogic;

import ch.unartig.studioserver.model.Category;
import ch.unartig.studioserver.model.EventGroup;
import ch.unartig.studioserver.model.Event;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.exceptions.UAPersistenceException;

public class GenericLevelVisitorAdaptor implements GenericLevelVisitor
{

    public void visit(Category category) throws UnartigException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(EventGroup eventGroup) throws UAPersistenceException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(Event event) throws UAPersistenceException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visit(StudioAlbum album) throws UnartigException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
