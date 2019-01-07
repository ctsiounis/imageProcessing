package image.processing.operations;

import java.awt.Color;

public class ThresholdingOperation implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		Color[][] result = new Color[numOfRows][numOfColumns];
		
		int threshold = 100;

		for(int i = 0; i < numOfRows; i++)
		    for(int j = 0; j < numOfColumns; j++) {
		    	Color pixel = imageArray[i][j];
		    	int color = (int) Math.round(
								(0.21 * pixel.getRed()) + 
								(0.71 * pixel.getGreen()) + 
								(0.07 * pixel.getBlue())
		    				);
		    	if (color>threshold) {
		    		result[i][j] = new Color(0, 0, 0);
		    	} else {
		    		result[i][j] = new Color(255, 255, 255);
		    	}
		    	
		    }
		return result;
	}

}
