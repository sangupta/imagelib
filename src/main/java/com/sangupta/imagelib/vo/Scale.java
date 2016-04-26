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
 

package com.sangupta.imagelib.vo;

/**
 * 
 * @author sangupta
 *
 */
public class Scale {

	private final double width;
	
	private final double height;
	
	private final double scale;

	public Scale(double width, double height) {
		this.width = width;
		this.height = height;
		scale = width / height;
	}
	
	public static Scale getScaledDimensions(Scale orgScale, Scale scale) {
		double x = orgScale.getWidth() / scale.getWidth();
		double x2 = orgScale.getHeight() / scale.getHeight();
		double w = scale.getWidth();
		double h = scale.getHeight();
		
		if (x < x2) {
			h = Math.ceil(scale.getWidth() / orgScale.getScale());
		} else if (x > x2) {
			w = Math.ceil(orgScale.getScale() * scale.getHeight());
		}

		Scale newScale = new Scale(w, h);
		return newScale;
	}

	// Usual accessors follow

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public double getScale() {
		return this.scale;
	}

}