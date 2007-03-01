/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$             
 *    @since Nov 24, 2005$
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
 * Revision 1.3  2005/11/29 02:00:17  alex
 * bug fixes
 *
 * Revision 1.2  2005/11/27 19:39:10  alex
 * fast upload
 *
 * Revision 1.1  2005/11/25 10:56:58  alex
 * todo liste, admin tool, sonstiges
 *
 ****************************************************************/
package ch.unartig.studioserver.businesslogic;

import ch.unartig.exceptions.UAPersistenceException;
import ch.unartig.exceptions.UnartigException;
import ch.unartig.studioserver.model.StudioAlbum;
import ch.unartig.studioserver.persistence.DAOs.GenericLevelDAO;
import ch.unartig.util.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Uploader extends Thread
{

    Logger _logger = Logger.getLogger(getClass().getName());

    String imagePath;
    Long albumId;
    Boolean processImages;

    public Uploader(String imagePath, Long albumId, Boolean processImages)
    {
        this.imagePath = imagePath;
        this.albumId = albumId;
        if (processImages == null || processImages == Boolean.FALSE)
        {
            this.processImages = Boolean.FALSE;
        } else
        {
            this.processImages = Boolean.TRUE;
        }
    }

    public void run()
    {
        try
        {
            doImport();
        } catch (IOException e)
        {
            _logger.error("IO-error in uploader thread. stopping thread", e);
        } catch (UnartigException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void doImport() throws IOException, UnartigException, UAPersistenceException
    {
        GenericLevelDAO glDao = new GenericLevelDAO();
        StudioAlbum album = (StudioAlbum) glDao.load(albumId, StudioAlbum.class);
        File imageDir = new File(imagePath);
        //
        _logger.debug("imageDir.isDirectory() = " + imageDir.isDirectory());
        _logger.debug("album.getFinePath().isDirectory() = " + album.getFinePath().isDirectory());
        FileUtils.copyDir(imageDir, album.getFinePath(), new FileUtils.JpgFileFilter());

        // null?
        if (processImages != null)
        {
            album.registerPhotos(processImages.booleanValue());
        } else
        {
            throw new UnartigException("cannot import ... null");
        }

//        _logger.debug("process images? : " + processImages);
//        if (processImages.booleanValue())
//        {
//            album.processImages();
//        }
    }
}
