package com.sangupta.imagelib.vo;

/**
 * Value object that helps specify image dimensions.
 * 
 * @author sangupta
 *
 */
public class ImageDimensions {
	
	public final int width;
	
	public final int height;
	
	public ImageDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int hashCode() {
		return this.width * this.height;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof ImageDimensions)) {
			return false;
		}
		
		ImageDimensions other = (ImageDimensions) obj;
		
		return this.width == other.width && this.height == other.height;
	}
}
