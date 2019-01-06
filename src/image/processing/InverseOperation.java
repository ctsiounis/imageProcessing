package image.processing;

import java.awt.Color;

public class InverseOperation implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;
		
		Color[][] result = new Color[numOfRows][numOfColumns];
		
		for (int i = 0; i < numOfRows; i++)
			for (int j = 0; j < numOfColumns; j++) {
				int red = Math.round(255*imageArray[i][j].getRed());
				int green = Math.round(255*imageArray[i][j].getGreen());
				int blue = Math.round(255*imageArray[i][j].getBlue());
				
				red = red & 0x000000FF;
				green = green & 0x000000FF;
				blue = blue & 0x000000FF;
				
				result[i][j] = new Color(red, green, blue);
			}
		return result;
	}

}
