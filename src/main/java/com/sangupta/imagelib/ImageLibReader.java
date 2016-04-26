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

import javax.imageio.ImageIO;

import org.apache.sanselan.Sanselan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sangupta.imagelib.reader.JpegReader;

/**
 * 
 * @author sangupta
 *
 */
public class ImageLibReader {
    
	/**
	 * My private logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageLibReader.class);
	
	/**
     * Create {@link BufferedImage} object from the byte representation of the
     * image.
     * 
     * @param bytes
     *            the byte-array data of the image
     * 
     * @return a {@link BufferedImage} instance if we can parse the image, else
     *         <code>null</code>
     * 
     * @throws IOException
     *             if something fails
     *             
     * @throws IllegalArgumentException if image data is <code>null</code>
     */
	public static BufferedImage readImage(final byte[] bytes) throws IOException {
	    if(bytes == null) {
	        throw new IllegalArgumentException("Image bytes cannot be null");
	    }
	    
		BufferedImage image = null;
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		
		// try the default java ImageIO
		try {
			image = ImageIO.read(bais);
			return image;
		} catch(Exception e) {
			// eat up
		} finally {
		    ImageLibUtils.close(bais);
		}
		
		// try basic sanselan
		try {
			image = Sanselan.getBufferedImage(bytes);
			return image;
		} catch(Exception e) {
			// eat up
		}

		// try new profile
		try {
			image = new JpegReader().readImage(bytes);
			return image;
		} catch(Exception e) {
			// eat up
		}
		
		LOGGER.warn("Unable to parse image using any of the known methods"); 
		return null;
	}

}