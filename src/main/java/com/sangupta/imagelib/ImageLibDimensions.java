/**
 *
 * imagelib - Simple image library
 * Copyright (c) 2015-2016, Sandeep Gupta
 * 
 * http://sangupta.com/projects/imagelib
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
 

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
    
    /**
     * Read the image dimensions by first using the faster method. If it fails, we read the image
     * via the slower method.
     * 
     * @param bytes the image data
     * 
     * @return an integer array with two elements, the first is the width, the
     *         second the height of the image. Returns <code>null</code> if we cannot
     *         read the image.
     * 
     * @throws IllegalArgumentException if image data is <code>null</code>
     * 
     */
    public static int[] getImageDimensions(byte[] bytes) {
        if(bytes == null) {
            throw new IllegalArgumentException("Image bytes cannot be null");
        }
        
        int[] dim = getDimensionsFast(bytes);
        if(dim != null) {
            return dim;
        }
        
        return getDimensionsSlow(bytes);
    }
    
    /**
     * 
     * @param bytes the byte-array containing image data

     * @return an integer array with two elements, the first is the width, the
     *         second the height of the image. Returns <code>null</code> if we cannot
     *         read the image.
     * 
     * @throws IllegalArgumentException if image data is <code>null</code>
     * 
     */
    private static int[] getDimensionsSlow(byte[] bytes) {
        if(bytes == null) {
            throw new IllegalArgumentException("Image bytes cannot be null");
        }
        
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
     * Read image dimensions via {@link ImageReader} methods - this does not
     * read the entire image - but just the metadata.
     * 
     * @param inStream
     *            the {@link InputStream} to read the image from
     * 
     * @return an integer array with two elements, the first is the width, the
     *         second the height of the image
     * 
     * @throws IOException
     *             if something fails
     *
     * @throws IllegalArgumentException if image data is <code>null</code>
     * 
     */
    private static int[] getDimensionsFast(byte[] bytes) {
        if(bytes == null) {
            throw new IllegalArgumentException("Image bytes cannot be null");
        }
        
        ByteArrayInputStream inStream = new ByteArrayInputStream(bytes);
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
        } catch (IOException e) {
            LOGGER.debug("Unable to read image");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // eat this up
                }
            }
        }
        
        return null;
    }

}