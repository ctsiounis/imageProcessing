package image.processing;

import java.awt.Color;

public class IdentityOperation implements ImageOperation {

	@Override
	public Color[][] doOperation(Color[][] imageArray) {
		return imageArray;
	}

}
