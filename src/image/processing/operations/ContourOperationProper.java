package image.processing.operations;

import java.awt.Color;

public class ContourOperationProper implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		Color[][] result = new Color[numOfRows][numOfColumns];

		for (int i = 0; i < numOfRows; i++) {
			result[i][0] = new Color(0, 0, 0);
			result[i][numOfColumns - 1] = new Color(0, 0, 0);
		}

		for (int i = 0; i < numOfColumns; i++) {
			result[0][i] = new Color(0, 0, 0);
			result[numOfRows - 1][i] = new Color(0, 0, 0);
		}

		double threshold = 0.95;
		double maxDistance = 3.0 * 255 * 255;

		for (int i = 1; i < numOfRows - 1; i++)
			for (int j = 1; j < numOfColumns - 1; j++) {
				int resultColor = 0;
				
				Color pixel = imageArray[i][j];
				Color n = imageArray[i - 1][j];
				Color ne = imageArray[i - 1][j + 1];
				Color e = imageArray[i][j + 1];
				Color se = imageArray[i + 1][j + 1];
				Color s = imageArray[i + 1][j];
				Color sw = imageArray[i + 1][j - 1];
				Color w = imageArray[i][j - 1];
				Color nw = imageArray[i - 1][j - 1];
				
				
				
				if (	  ((colorDistance(pixel, n) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, ne) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, e) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, se) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, s) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, sw) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, w) / maxDistance) > 1 - threshold)
						||((colorDistance(pixel, nw) / maxDistance) > 1 - threshold)) {
					resultColor = 0;
				} else {
					resultColor = 255;
				}

				/*int color = imageArray[i][j].getRed();
				int n = imageArray[i - 1][j].getRed();
				int ne = imageArray[i - 1][j + 1].getRed();
				int e = imageArray[i][j + 1].getRed();
				int se = imageArray[i + 1][j + 1].getRed();
				int s = imageArray[i + 1][j].getRed();
				int sw = imageArray[i + 1][j - 1].getRed();
				int w = imageArray[i][j - 1].getRed();
				int nw = imageArray[i - 1][j - 1].getRed();
				if (color != 0)
					if (
							((Math.abs(color - n) / (color*1.0)) > threshold)
							|| ((Math.abs(color - ne) / (color*1.0)) > threshold)
							|| ((Math.abs(color - e) / (color*1.0)) > threshold)
							|| ((Math.abs(color - se) / (color*1.0)) > threshold)
							|| ((Math.abs(color - s) / (color*1.0)) > threshold)
							|| ((Math.abs(color - sw) / (color*1.0)) > threshold)
							|| ((Math.abs(color - w) / (color*1.0)) > threshold)
							|| ((Math.abs(color - nw) / (color*1.0)) > threshold)
							) {
						resultColor = 0;
					} else {
						resultColor = 255;
					}
				else
					resultColor = 255;8?

				/*
				 * red = red & 0x000000FF; green = green & 0x000000FF; blue =
				 * blue & 0x000000FF;
				 */

				result[i][j] = new Color(resultColor, resultColor, resultColor);
			}
		return result;
	}
	
	private double colorDistance (Color pixel, Color neighbor) {
		int pixelRed = pixel.getRed();
		int pixelGreen = pixel.getGreen();
		int pixelBlue = pixel.getBlue();
		
		int neighborRed = neighbor.getRed();
		int neighborGreen = neighbor.getGreen();
		int neighborBlue = neighbor.getBlue();
		
		int colorDistance = (pixelRed - neighborRed)*(pixelRed - neighborRed) + 
						 	(pixelGreen - neighborGreen)*(pixelGreen - neighborGreen) + 
						 	(pixelBlue - neighborBlue)*(pixelBlue - neighborBlue);
		
		return colorDistance;
	}

}
