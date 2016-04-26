package com.sangupta.imagelib;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility methods to find the dimensions of the image.
 * 
 * @author sangupta
 *
 */
public class ImageLibDimensions {
    
    /**
     * My private logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibDimensions.class);
    
    public static int[] getImageDimensions(byte[] bytes) {
        if(bytes == null) {
            return null;
        }
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        int[] dim = null;
        
        try {
            dim = getDimensionsFast(bais);
        } catch (IOException e) {
            LOGGER.debug("Unable to read image dimensions using faster method, trying the slow method now");
        }
        
        if(dim != null) {
            return dim;
        }
        
        return getDimensionsSlow(bytes);
    }
    
    private static int[] getDimensionsSlow(byte[] bytes) {
        BufferedImage image = null;
        try {
            image = ImageLibReader.readImage(bytes);
            
            if(image != null) {
                return new int[] { image.getWidth(), image.getHeight() };
            }
        } catch (IOException e) {
            LOGGER.debug("Unable to read image");
        } finally {
            if(image != null) {
                image.flush();
            }
        }
        
        return null;
    }

    /**
     * Read image via ImageReader methods
     * 
     * @param url
     * @throws IOException
     */
    private static int[] getDimensionsFast(InputStream inStream) throws IOException {
        ImageInputStream in = null;
        try {
            in = ImageIO.createImageInputStream(inStream);
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    return new int[] { reader.getWidth(0), reader.getHeight(0) };
                } finally {
                    reader.dispose();
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        
        return null;
    }

}
