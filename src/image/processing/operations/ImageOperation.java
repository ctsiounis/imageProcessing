package image.processing.operations;

import java.awt.Color;

public interface ImageOperation {
	public Color[][] doOperation(Color[][] imageArray);
}
