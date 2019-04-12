package com.sangupta.imagelib;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageLibResize {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibResize.class);
    
    public static byte[] resizeImageAsBytes(BufferedImage image, int maxSideLength) {
        return resizeImageAsBytes(image, maxSideLength, null);
    }
    
    public static byte[] resizeImageAsBytes(BufferedImage image, int maxSideLength, byte[] original) {
        BufferedImage resized = resizeImage(image, maxSideLength);
        
        if(original != null && resized == image) {
            return original;
        }
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            ImageIO.write(resized, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            
            return imageBytes;
        } catch(IOException e) {
            LOGGER.error("Unable to convert image to byte array", e);
            return null;
        }
    }

    public static BufferedImage resizeImage(BufferedImage image, int maxSideLength) {
        if(image == null) {
            throw new IllegalArgumentException("Image to resize cannot be null");
        }
        
        if(maxSideLength <= 0) {
            throw new IllegalArgumentException("Max square box size cannot be less than or equal to zero");
        }
        
        double originalWidth = image.getWidth();
        double originalHeight = image.getHeight();
        double scaleFactor = 0.0;
        
        if (originalWidth > originalHeight) {
            scaleFactor = ((double) maxSideLength / originalWidth);
        } else {
            scaleFactor = ((double) maxSideLength / originalHeight);
        }
        
        int newWidth = (int) Math.round(originalWidth * scaleFactor);
        int newHeight = (int) Math.round(originalHeight * scaleFactor);
        
        // create new image
        if (scaleFactor < 1 && newWidth > 1 && newHeight > 1) {
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, image.getType());
            
            // fast scale (Java 1.4 & 1.5)
            Graphics g = resizedImage.getGraphics();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(image, 0, 0, resizedImage.getWidth(), resizedImage.getHeight(), null);
            return resizedImage;
        }
        
        return image;
    }
    
}
