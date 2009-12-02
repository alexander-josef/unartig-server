package ch;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.JPEGEncodeParam;

import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.widget.ScrollingImagePanel;
import java.awt.image.renderable.ParameterBlock;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 01.01.2005
 * Time: 15:10:00
 * To change this template use File | Settings | File Templates.
 */
public class JaiTest
{
    /**
     * This program decodes an image file of any JAI supported
     * formats, such as GIF, JPEG, TIFF, BMP, PNM, PNG, into a
     * RenderedImage, scales the image by 2X with bilinear
     * interpolation, and then displays the result of the scale
     * operation.
     */

    /**
     * The main method.
     */
    public static void main(String[] args) throws IOException
    {
/* Validate input. */
        if (args.length != 1)
        {
            System.out.println("Usage: java JAISampleProgram " + "input_image_filename");
            System.exit(-1);
        }
/*
* Create an input stream from the specified file name
* to be used with the file decoding operator.
*/
        FileSeekableStream stream = null;
        try
        {
            stream = new FileSeekableStream(args[0]);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
/* Create an operator to decode the image file. */
        RenderedOp image1 = JAI.create("stream", stream);

//        stream = new FileSeekableStream("c:/temp/watermark_quer.png");
//        RenderedOp watermark = JAI.create("stream", stream);





/*
* Create a standard bilinear interpolation object to be
* used with the "scale” operator.
*/
        Interpolation interp = Interpolation.getInstance(Interpolation.INTERP_BILINEAR);
        /**
         * Stores the required input source and parameters in a
         * ParameterBlock to be sent to the operation registry,
         * and eventually to the "scale” operator.
         */
        ParameterBlock params = new ParameterBlock();
        params.addSource(image1);
        params.add(0.1); // x scale factor
//        params.add(0.05); // x scale factor
//        params.add(0.05F); // y scale factor
//        params.add(0.0F); // x translate
//        params.add(0.0F); // y translate
//        params.add(interp); // interpolation method
/* Create an operator to scale image1. */
        RenderedOp image2 = JAI.create("subsampleaverage", params);

        float[] fA = new float[4];
        KernelJAI kernel = new KernelJAI(2, 2, fA);

        ParameterBlock unsharpParams = new ParameterBlock();
        unsharpParams.addSource(image2);
        unsharpParams.add(null); // x scale factor
        unsharpParams.add(0.5F);
        RenderedOp image3 = JAI.create("unsharpMask", unsharpParams);

//        ParameterBlock pb = new ParameterBlock();
//        pb.addSource(image3); // arg0
//        pb.addSource(watermark); //arg1

//        pb.add(alpha);
//        pb.add(null);
//        pb.add(new Boolean(false));
//        pb.add(new Integer(CompositeDescriptor.NO_DESTINATION_ALPHA));

//        pb.addSource(new Boolean(false));//arg2
//        pb.addSource(null);// arg3
//        pb.addSource(null);// arg3
//        pb.addSource(new Boolean(false));
//        pb.add(CompositeDescriptor.NO_DESTINATION_ALPHA);
// Create the Add operation.
//        RenderedOp image4 = JAI.create("overlay", pb);





//        String outFileName = "c:/temp/jaiTest.jpg";
//        ImageIO.write(image3, "jpg", new File(outFileName));


        JPEGEncodeParam encParam = new JPEGEncodeParam();
        FileOutputStream out = new FileOutputStream("c:/temp/jaiTestII.jpg");

        encParam.setQuality(1);
        ImageEncoder encoder = ImageCodec.createImageEncoder("JPEG", out, encParam);
        encoder.encode(image3);
        out.close();



/* Get the width and height of image2. */
        int width = image2.getWidth();
        int height = image2.getHeight();
        int width3 = image3.getWidth();
        int height3 = image3.getHeight();
/* Attach image2 to a scrolling panel to be displayed. */
        ScrollingImagePanel panel = new ScrollingImagePanel(image2, width, height);
        ScrollingImagePanel panel2 = new ScrollingImagePanel(image3, width3, height3);
/* Create a frame to contain the panel. */
//        Frame window = new Frame("JAI Sample Program");
//        window.add(panel);
//        window.pack();
//        window.show();
//
//        Frame window2 = new Frame("JAI Sample Program");
//        window2.add(panel2);
//        window2.pack();
//        window2.show();
    }
}

