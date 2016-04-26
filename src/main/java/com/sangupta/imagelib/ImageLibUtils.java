package com.sangupta.imagelib;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageLibUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibUtils.class);
    
    public static void close(Closeable closeable) {
        if(closeable == null) {
            return;
        }
        
        try {
            closeable.close();
        } catch(IOException e) {
            LOGGER.debug("Error closing closeable", e);
        }
    }

    public static void close(BufferedImage image) {
        if(image == null) {
            return;
        }
        
        try {
            image.flush();
        } catch(RuntimeException e) {
            LOGGER.debug("Error closing image", e);
        }
    }

}
