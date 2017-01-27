package com.sangupta.imagelib;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageLibUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibUtils.class);
    
    /**
	 * Close a given {@link Closeable} without throwing an exception.
	 * 
	 * @param closeable
	 *            the {@link Closeable} to be closed
	 */
    public static void close(Closeable closeable) {
        if(closeable == null) {
            return;
        }
        
        try {
            closeable.close();
        } catch(IOException e) {
            LOGGER.warn("Error closing closeable", e);
        }
    }

    /**
	 * Close a given {@link BufferedImage} without throwing an exception.
	 *
	 * @param image
	 *            the {@link BufferedImage} to be closed
	 */
    public static void close(BufferedImage image) {
        if(image == null) {
            return;
        }
        
        try {
            image.flush();
        } catch(RuntimeException e) {
            LOGGER.warn("Error closing image", e);
        }
    }

}
