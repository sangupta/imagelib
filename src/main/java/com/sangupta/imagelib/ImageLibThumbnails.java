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

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sangupta.imagelib.vo.Scale;

public class ImageLibThumbnails {
    
    /**
     * My private logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibThumbnails.class);

    /**
     * Create a thumbnail from the soruce {@link BufferedImage} in the given
     * width and height.
     * 
     * @param sourceImage
     *            the source image - must not be null
     * 
     * @param thumbWidth
     *            the width of the required thumbnail
     * 
     * @param thumbHeight
     *            the height of the required thumbnail
     * 
     * @return the thumbnail represented as {@link BufferedImage}. If the
     *         dimensions for the thumbnail are greater than the image's
     *         dimensions, original image size is returned
     * 
     * @throws NullPointerException
     *             if the source image object is <code>null</code>
     *             
     * @throws IllegalArgumentException if sourceImage is <code>null</code> 
     * 
     */
    public static BufferedImage createThumbnail(final BufferedImage sourceImage, int thumbWidth, int thumbHeight) {
        if(sourceImage == null) {
            throw new IllegalArgumentException("Source image cannot be null");
        }
        
        boolean onlyCrop = false;
        
        int imageWidth = sourceImage.getWidth();
        int imageHeight = sourceImage.getHeight();
        
        if(imageWidth <= thumbWidth && imageHeight <= thumbHeight) {
            return sourceImage;
        }
        
        if(imageWidth == thumbWidth || imageHeight == thumbHeight) {
            // one of the image dimensions is what we need - only crop the extra region
            onlyCrop = true;
        }
        
        if (thumbWidth > imageWidth) {
            thumbWidth = imageWidth;
        }
        
        if (thumbHeight > imageHeight) {
            thumbHeight = imageHeight;
        }
        
        // resize, if required
        BufferedImage thumbnail = sourceImage;

        if(!onlyCrop) {
            Scale orgScale = new Scale(imageWidth, imageHeight);
            Scale scale = new Scale(thumbWidth, thumbHeight);

            Scale newScale = Scale.getScaledDimensions(orgScale, scale);

            thumbnail = Scalr.resize(sourceImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, (int) newScale.getWidth(), (int) newScale.getHeight(), Scalr.OP_ANTIALIAS);
            
            imageWidth = thumbnail.getWidth();
            imageHeight = thumbnail.getHeight();
        }
        
        return thumbnail;
    }
    
}