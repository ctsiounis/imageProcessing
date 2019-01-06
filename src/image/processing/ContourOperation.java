package image.processing;

import java.awt.Color;

public class ContourOperation implements ImageOperation {

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

		double threshold = 0.5;

		for (int i = 1; i < numOfRows - 1; i++)
			for (int j = 1; j < numOfColumns - 1; j++) {
				int resultColor = 0;

				int color = imageArray[i][j].getRed();
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
					resultColor = 255;

				/*
				 * red = red & 0x000000FF; green = green & 0x000000FF; blue =
				 * blue & 0x000000FF;
				 */

				result[i][j] = new Color(resultColor, resultColor, resultColor);
			}
		return result;
	}

}
