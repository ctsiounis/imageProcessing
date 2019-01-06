package image.processing;

public class OperationFactory {
	public static ImageOperation create(String op) {
		switch (op) {
		case "Contour":
			return new ContourOperation();
		case "Inverse":
			return new InverseOperation();
		default:
			return new IdentityOperation();
		}
	}
}
