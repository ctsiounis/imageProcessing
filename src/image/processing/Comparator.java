package image.processing;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Comparator {
	private String pathFile;

	private String getPathFile() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Choose the file that contains the images' paths: ");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (jfc.getSelectedFile().isFile()) {
				System.out.println("You selected the file: " + jfc.getSelectedFile());
				return jfc.getSelectedFile().toString();
			}
		}
		System.out.println("Rerun specifying pathFile.");
		return null;
	}
	
	public boolean checkArguments(String[] args) {
		if (args.length != 1) {
			System.out.println("You can also use the program in the following format: java ImageProcessing pathFile");
			if ((pathFile = getPathFile()) == null)
				return false;
		} else {
			if (!new File(args[0]).isFile()) {
				System.out.println("First argument is not a file or does not exist.");
				return false;
			} else {
				pathFile = args[0];
			}
		}
		 return true;
	}

	public boolean compare() {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(pathFile));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {

					ImageLoader loader = new ImageLoader();
					boolean success = loader.loadImage(line);

					if (success) {
						// Color[][] colorArray = loader.getColorArray();

						// ImageOperation operation = new InverseOperation();
						// Color[][] result = operation.doOperation(colorArray);

						Color[][] grayArray = loader.getGrayscaleArray();

						ImageOperation studentOp = new ContourOperation();
						Color[][] studentResult = studentOp.doOperation(grayArray);
						
						ImageOperation properOp = new ContourOperation();
						Color[][] properResult = properOp.doOperation(grayArray);
						
						double threshold = 0.1;
						
						int numOfRows = properResult.length;
						int numOfColumns = properResult[0].length;
						
						for (int i = 1; i < numOfRows - 1; i++)
							for (int j = 1; j < numOfColumns - 1; j++) {
								Color student = studentResult[i][j];
								Color proper = properResult[i][j];
								
								if (((Math.abs(proper.getRed() - student.getRed()) / (proper.getRed()*1.0)) > threshold)
										|| ((Math.abs(proper.getBlue() - student.getBlue()) / (proper.getBlue()*1.0)) > threshold)
										|| ((Math.abs(proper.getGreen() - student.getGreen()) / (proper.getGreen()*1.0)) > threshold))
								{
									br.close();
									return false;
								} 
							}
					}
				}
				br.close();
			} catch (IOException ioException) {
				System.out.println("Error when reading the text file.");
				ioException.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException fileException) {
			System.out.println("Text file containing images' paths not found.");
			fileException.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Comparator comparator = new Comparator();
		if (comparator.checkArguments(args))
			System.out.println(comparator.compare());
	}
}
