package image.processing;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

import image.processing.operations.ImageOperation;
import image.processing.operations.OperationFactory;

public class ImageProcessing {
	private String pathFile;
	private String outputDir;

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

	private String getOutputDirectory() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Choose a directory to save your output images: ");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (jfc.getSelectedFile().isDirectory()) {
				System.out.println("You selected the directory: " + jfc.getSelectedFile());
				return jfc.getSelectedFile().toString();
			}
		}
		System.out.println("Rerun specifying pathFile.");
		return null;
	}
	
	private String createOutputImageName(String inputPath, String op) {
		String[] parts = inputPath.split("\\\\");
		String[] inputName = parts[parts.length - 1].split("\\.");
		return inputName[0] + "_output_"+ op +"." + inputName[1];
	}
	
	public boolean checkArguments(String[] args) {
		if (args.length != 2) {
			System.out.println("You can also use the program in the following format: java ImageProcessing pathFile outputFolder");
			if ((pathFile = getPathFile()) == null)
				return false;
			if ((outputDir = getOutputDirectory()) == null)
				return false;
		} else {
			if (!new File(args[0]).isFile()) {
				System.out.println("First argument is not a file or does not exist.");
				return false;
			} else {
				pathFile = args[0];
			}

			if (!new File(args[1]).isDirectory()) {
				System.out.println("Second argument is not a directory.");
				return false;
			} else {
				outputDir = args[1];
			}
		}
		 return true;
	}

	public void processImage() {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(pathFile));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					String[] parameters = line.split("\\s+");
					
					Color[][] array;
					
					ImageLoader loader = new ImageLoader();
					boolean success = loader.loadImage(parameters[1]);
					
					if ("Color".equals(parameters[0])) {
						array = loader.getColorArray();
					} else if ("Grayscale".equals(parameters[0])) {
						array = loader.getGrayscaleArray();
					} else {
						System.out.println("Please provide Color or Grayscale in image: " + parameters[1]);
						br.close();
						return;
					}
					
					if (success) {
						int numOfOperations = Integer.parseInt(parameters[2]);
						for (int i = 1; i <= numOfOperations; i++) {
							ImageOperation operation = OperationFactory.create(parameters[2+i]);
							Color[][] result = operation.doOperation(array);
							
							String outputFile = createOutputImageName(parameters[1], parameters[2+i]);
							ImageWriter writer = new ImageWriter();
							writer.writeImage(outputDir + "\\" + outputFile, result);
						}
					}
				}
				br.close();
			} catch (IOException ioException) {
				System.out.println("Error when reading the text file.");
				ioException.printStackTrace();
			}
		} catch (FileNotFoundException fileException) {
			System.out.println("Text file containing images' paths not found.");
			fileException.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ImageProcessing process = new ImageProcessing();
		if (process.checkArguments(args))
			process.processImage();
	}

}
