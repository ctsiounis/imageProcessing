package image.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	private BufferedImage image;

	public boolean loadImage(String filename) {
		try {
			image = ImageIO.read(new File(filename));
			return true;
		} catch (IOException ioe) {
			System.out.println("Couldn't find file " + filename + ". Please specify full path.");
			return false;
		}
	}

	public Color[][] getColorArray() {
		ImageConverter converter = new ImageConverter();
		return converter.bufferedImageToColorArray(image);
	}

	public Color[][] getGrayscaleArray() {
		ImageConverter converter = new ImageConverter();
		return converter.bufferedImageToGrayscaleArray(image);
	}
}
