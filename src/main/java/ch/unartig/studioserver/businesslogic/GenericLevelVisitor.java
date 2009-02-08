package ch.unartig.studioserver.businesslogic;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.model.Category;
import ch.unartig.studioserver.model.Event;
import ch.unartig.studioserver.model.EventGroup;
import ch.unartig.studioserver.model.StudioAlbum;

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

public interface GenericLevelVisitor
{
    public void visit(Category category) throws UnartigException;
    public void visit(EventGroup eventGroup) throws UAPersistenceException;
    public void visit(Event event) throws UAPersistenceException;
    public void visit(StudioAlbum album) throws UnartigException;
}
