package image.processing.operations;

import java.awt.Color;

public class IdentityOperationProper implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		return imageArray;
	}

}
