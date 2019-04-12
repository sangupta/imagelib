package com.sangupta.imagelib;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ImageLibTouch {

	/**
	 * Converts an image to grey. Use instead of color conversion op, which yields
	 * strange results.
	 *
	 * @param image
	 */
	public static BufferedImage getGrayscaleImage(BufferedImage image) {
		if(image == null) {
			throw new IllegalArgumentException("Image cannot be null");
		}
		
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		result.getGraphics().drawImage(image, 0, 0, null);
		return result;
	}

	public static BufferedImage get8BitRGBImage(BufferedImage image) {
		if(image == null) {
			throw new IllegalArgumentException("Image cannot be null");
		}
		
		// check if it's (i) RGB and (ii) 8 bits per pixel.
		if (image.getType() != BufferedImage.TYPE_INT_RGB || image.getSampleModel().getSampleSize(0) != 8) {
			BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
			img.getGraphics().drawImage(image, 0, 0, null);
			image = img;
		}
		return image;
	}

	/**
	 * 
	 * @param image
	 * @param fromX
	 * @param fromY
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage cropImage(BufferedImage image, int fromX, int fromY, int width, int height) {
		if(image == null) {
			throw new IllegalArgumentException("Image cannot be null");
		}
		
		assert (width > 0 && height > 0);
		int toX = Math.min(fromX + width, image.getWidth());
		int toY = Math.min(fromY + height, image.getHeight());
		// create smaller image
		BufferedImage cropped = new BufferedImage(toX - fromX, toY - fromY, BufferedImage.TYPE_INT_RGB);
		// fast scale (Java 1.4 & 1.5)
		Graphics g = cropped.getGraphics();
		g.drawImage(image, 0, 0, cropped.getWidth(), cropped.getHeight(), fromX, fromY, toX, toY, null);
		return cropped;
	}

	/**
	 * Invert the given {@link BufferedImage}.
	 * 
	 * @param image the inverted image
	 */
	public static void invertImage(BufferedImage image) {
		if(image == null) {
			throw new IllegalArgumentException("Image cannot be null");
		}
		
		WritableRaster inRaster = image.getRaster();
		int[] p = new int[3];
		for (int x = 0; x < inRaster.getWidth(); x++) {
			for (int y = 0; y < inRaster.getHeight(); y++) {
				inRaster.getPixel(x, y, p);
				p[0] = 255 - p[0];
				inRaster.setPixel(x, y, p);
			}
		}
	}

}
