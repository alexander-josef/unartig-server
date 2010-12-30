/*-*
 *
 * FILENAME  :
 *    $RCSfile$
 *
 *    @author alex$
 *    @since Oct 24, 2005$
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
 * Revision 1.9  2006/11/22 14:39:05  alex
 * small fixes
 *
 * Revision 1.8  2006/11/21 13:58:45  alex
 * small fixes
 *
 * Revision 1.7  2006/11/05 22:10:02  alex
 * credit card order works
 *
 * Revision 1.6  2006/10/28 21:57:09  alex
 * reformat
 *
 * Revision 1.5  2005/11/27 19:39:10  alex
 * fast upload
 *
 * Revision 1.4  2005/11/25 10:56:58  alex
 * admin tool, sonstiges
 *
 * Revision 1.3  2005/11/16 14:26:49  alex
 * validator works for email, new library
 *
 * Revision 1.2  2005/10/24 14:37:55  alex
 * small fixes, creating directories
 *
 * Revision 1.1  2005/10/24 13:50:07  alex
 * upload of album
 * import in db
 * processing of images
 *
 ****************************************************************/
package ch.unartig.studioserver.imaging;

import ch.unartig.exceptions.UnartigImagingException;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;
import org.apache.log4j.Logger;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.widget.ScrollingImagePanel;
import java.awt.*;
import java.awt.image.renderable.ParameterBlock;
import java.io.*;

public class ImagingHelper
{
    static Logger _logger = Logger.getLogger(ImagingHelper.class.getName());

    /**
     * loads an image from disk and returns a RenderedOp
     *
     * @param file
     * @return image
     * @throws ch.unartig.exceptions.UnartigImagingException File not found or similar problem
     */
    public static RenderedOp load(File file) throws UnartigImagingException
    {

        RenderedOp retVal;
        FileSeekableStream stream;
        try
        {
            stream = new FileSeekableStream(file);
        } catch (IOException e)
        {
            _logger.error("Exception while loading image from file", e);
            throw new UnartigImagingException("Exception while loading image from file", e);
        }
        /* Create an operator to decode the image file. */
//        RenderedOp image1 = JAI.create("stream", stream);
        retVal = JAI.create("stream", stream);
        return retVal;
    }

    public static RenderedOp readImage(InputStream stream)
    {

        return JAI.create("stream", stream);
    }

    /**
     *
     * @param sampledOp
     * @param os output stream for encoder
     * @param quality
     */
    private static void renderJpg(RenderedOp sampledOp, OutputStream os, float quality)
    {
        // todo : robust exception handling
        JPEGEncodeParam encParam = new JPEGEncodeParam();
        try
        {
            encParam.setQuality(quality);
            ImageEncoder encoder = ImageCodec.createImageEncoder("JPEG", os, encParam);
            encoder.encode(sampledOp);
            _logger.debug("ImagingHelper.saveJpg : image encoded");
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    /**
     * @param image
     * @param quality : A setting of 0.0 produces the highest compression ratio, with a sacrifice to image quality. The default value is 0.75
     * @return true for success
     */
    public static boolean saveJpg(RenderedOp image, float quality, File file)
    {
        // todo : robust exception handling
        JPEGEncodeParam encParam = new JPEGEncodeParam();
        FileOutputStream out;
        try
        {
            out = new FileOutputStream(file);
            encParam.setQuality(quality);
            ImageEncoder encoder = ImageCodec.createImageEncoder("JPEG", out, encParam);
            _logger.debug("going to save modified jpg file.getName() = " + file.getName());
            encoder.encode(image);
            _logger.debug("ImagingHelper.saveJpg : image encoded");
            out.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return true;
    }

    /**
     * Todo error handling !!!! For example, if a small image is rescaled, an exception might happen ... try with an index pic
     * @param image
     * @param scaleFactor
     * @return image
     */
    public static RenderedOp reSample(RenderedOp image, Double scaleFactor)
    {

        ParameterBlock params = new ParameterBlock();
        params.addSource(image);
        params.add(scaleFactor); // scale factor
        /* Create an operator to scale image. */
        return JAI.create("subsampleaverage", params);
    }

    /**
     * use unsharpen operation on the renderedOp
     * @param image
     * @param factor = 0 : no effect;  factor> 0 : sharpening;factor -1 < gain < 0 : smoothing
     * @return a renderedOp
     */
    public static RenderedOp unSharpen(RenderedOp image, float factor)
    {
        float[] fA = new float[4];
        KernelJAI kernel = new KernelJAI(2, 2, fA); // todo: what does the kernel do? can we improve something?

        ParameterBlock unsharpParams = new ParameterBlock();
        unsharpParams.addSource(image);
        unsharpParams.add(null); // kernel: 3x3 average
        unsharpParams.add(factor);
        return JAI.create("unsharpMask", unsharpParams);
    }

    public static void display(RenderedOp image)
    {
/* Get the width and height of image2. */
        int width = image.getWidth();
        int height = image.getHeight();

        ScrollingImagePanel panel = new ScrollingImagePanel(image, width, height);
        Frame window = new Frame("JAI Sample Program");
        window.add(panel);
        window.pack();
        window.show();
    }

    /**
     * @param renderedOp
     * @return max width or hight of picture
     */
    public static int getMaxWidthOrHightOf(RenderedOp renderedOp)
    {
        return Math.max(renderedOp.getHeight(), renderedOp.getWidth());
    }

    /**
     * creates a new image based on the passed renderedOp
     *
     * @param fineImage    the source for the new image
     * @param scale
     * @param newImageFile
     * @param quality
     * @param imageSharpFactor
     */
    public static void createNewImage(RenderedOp fineImage, Double scale, File newImageFile, float quality, float imageSharpFactor)
    {
        _logger.debug("Going to create new Image [" + newImageFile.getName() + "]from File : " + fineImage.toString() + " using scale :  " + scale);
        RenderedOp sharpThumbImage;
        RenderedOp thumbImage;
        try
        {
            thumbImage = reSample(fineImage, scale);
            sharpThumbImage = unSharpen(thumbImage, imageSharpFactor);
        } catch (Exception e)
        {
            _logger.info("rendering threw exception. probably rendering result is bigger than original; using original image instead");
            _logger.debug("Exception: ", e);
            sharpThumbImage = fineImage;
        }
        saveJpg(sharpThumbImage, quality, newImageFile);
    }

    /**
     * todo: this takes forever ... find a better method to find photo dimensions
     * @param photoFile
     * @return # of width-pixels for passed photo
     */
    public static Integer getPixelsWidth(File photoFile) throws UnartigImagingException
    {
        return new Integer(load(photoFile).getWidth());
    }

    public static Integer getPixelsHeight(File photoFile) throws UnartigImagingException
    {
        return new Integer(load(photoFile).getHeight());
    }

    /**
     *
     * generic resample function
     * @param file the file to resample
     * @param resampleFactor
     * @throws ch.unartig.exceptions.UnartigImagingException from load; file not found or similar
     * @param os OutputStream
     * @param quality
     */
    public static void reSample(File file, Double resampleFactor, OutputStream os, float quality) throws UnartigImagingException
    {
//        PipedOutputStream retVal = new PipedOutputStream();
        RenderedOp sampledOp = reSample(load(file),resampleFactor);
        renderJpg(sampledOp,os, quality);
    }

}
