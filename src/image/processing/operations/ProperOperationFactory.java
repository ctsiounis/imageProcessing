package image.processing.operations;

public class ProperOperationFactory {
	public static ImageOperation create(String op) {
		switch (op) {
		case "Contour":
			return new ContourOperationProper();
		case "Inverse":
			return new InverseOperationProper();
		case "Thresholding":
			return new ThresholdingOperationProper();
		case "Adjustment":
			return new AdjustmentOperationProper();
		default:
			return new IdentityOperationProper();
		}
	}
}
