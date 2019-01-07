package image.processing.operations;

public class OperationFactory {
	public static ImageOperation create(String op) {
		switch (op) {
		case "Contour":
			return new ContourOperation();
		case "Inverse":
			return new InverseOperation();
		case "Thresholding":
			return new ThresholdingOperation();
		case "Adjustment":
			return new AdjustmentOperation();
		default:
			return new IdentityOperation();
		}
	}
}
