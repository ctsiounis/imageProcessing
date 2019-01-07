package image.processing.operations;

import java.awt.Color;

public class AdjustmentOperation implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		Color[][] result = new Color[numOfRows][numOfColumns];
		
		int fixedX = 135;
		int fixedY = 140;
		
		double adjustBrightness;
		int newRed, newGreen, newBlue;
		int maxDimension = Math.max(numOfRows, numOfColumns);

		for (int i = 0; i < numOfRows; i++)
			for (int j = 0; j < numOfColumns; j++) {

				adjustBrightness = findAdjustBrightness(i, j, fixedX, fixedY, maxDimension);

				newRed = (int) Math.abs(Math.round(imageArray[i][j].getRed() * adjustBrightness) % 255);
				newGreen = (int) Math.abs(Math.round(imageArray[i][j].getGreen() * adjustBrightness) % 255);
				newBlue = (int) Math.abs(Math.round(imageArray[i][j].getBlue() * adjustBrightness) % 255);

				result[i][j] = new Color(newRed, newGreen, newBlue);
			}
		return result;
	}

	private double findAdjustBrightness(int x, int y, int fixedX, int fixedY, int maxDimension) {
		double distance = Math.sqrt((x - fixedX)*(x - fixedX) + (y - fixedY)*(y - fixedY));
		double adjustBrightness = (maxDimension-distance)/maxDimension;
		
		return adjustBrightness;
	}

}
