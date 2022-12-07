package com.fileexercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Files {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String mainPageHeading = "\t\t\t\tWelcome to File Exercises";
	String mainMenuOptions = "Enter Choice\n1 to Reading from a file and displaying it on the screen"
			+ "\n2 to Writing to a file \n3 to Copying one file to another\n4 to Decipher a file"
			+ "\n5 to Processing numerical data in a text file\nq or Q to quit";
	String thankYouMessage = "\t\t\t\tThank You for using File Exercises";

	private static final String QUIT = "Q";
	private static final String EXT_TXT_FILE = ".txt";
	private static final String NOT_EXIST = " NOT EXIST";
	private String path = "";

	/* This method will be entry point to run file exercises */
	public void runFileTests() {
		System.out.println(mainPageHeading);
//		System.out.println("\n!!! Please specify the path to the directory where files are present or LEAVE blank for active directory:");
//		System.out.println("e.g., E:/Portal_DS/SpringWorkspace/FileExercises");
//		try {
//			String userInput = reader.readLine();
//
//		} catch (IOException e1) {
//			System.err.println("ERROR: while setting path!!!!!!!!");
//		}
		while (Boolean.TRUE) {
			System.out.println(mainMenuOptions);
			String choice = "";
			try {
				choice = reader.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			// To handle quit condition
			if (QUIT.equalsIgnoreCase(choice)) {
				System.out.println(thankYouMessage);
				break;
			}

			// TO handle other choices if user doesn't quit
			if ("1".equalsIgnoreCase(choice)) {
				// to Reading from a file and displaying it on the screen
				readFromFile();
			} else if ("2".equalsIgnoreCase(choice)) {
				// to Writing to a file
				writeIntoFile();
			} else if ("3".equalsIgnoreCase(choice)) {
				// to Copying one file to another
				copyFileContentToOtherFile();
			} else if ("4".equalsIgnoreCase(choice)) {
				// to Decipher a file
				decipherFileContent();
			} else if ("5".equalsIgnoreCase(choice)) {
				// to Processing numerical data in a text file
				processNumericalContentOfFile();
			} else {
				// Invalid Choice
				System.out.println("INVALID CHOICE");
			}

		}

	}

	private void processNumericalContentOfFile() {
		// TODO Auto-generated method stub
		// Bush, Mildred: Average score is x.xx.
		try {
			System.out.println("Enter input/source file name:");
			String inputFileName = reader.readLine();
			// System.out.println("Enter output/destination file name:");
			String outputFileName = "detailsaverage";
			if (isFileExist(getFullFileName(inputFileName))) {
				createFileIfNotExist(getFullFileName(outputFileName));
				BufferedReader fileReader = new BufferedReader(new FileReader(getFullFileName(inputFileName)));
				FileWriter fWriter = new FileWriter(getFullFileName(outputFileName));
				String lineToCopy = fileReader.readLine();
				while (lineToCopy != null) {
					String[] elementsOfLine = lineToCopy.split(" ");
					String contentForFile = getFormattedLineToCopy(elementsOfLine);
					System.out.println("Copying: ".concat(contentForFile).concat(("\n")));
					// read next line
					fWriter.write(contentForFile.concat("\n"));
					lineToCopy = fileReader.readLine();
				}
				System.err.println("INFO: Copied from ".concat(getFullFileName(inputFileName)).concat(" to ")
						.concat(getFullFileName(outputFileName)));
				fWriter.close();
				fileReader.close();
			} else {
				System.err.println("ERROR: ".concat(getFullFileName(inputFileName)).concat(NOT_EXIST));

			}
			System.out.println();
		}

		// Catch block to handle if exception occurs
		catch (IOException e) {
			// Print the exception
			System.err.print(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private String getFormattedLineToCopy(String[] elementsOfLine) {
		String contentForFile = "";
		String firstName = elementsOfLine[0];
		String lastName = elementsOfLine[1];
		int sum = 0;
		int size = elementsOfLine.length;
		for (int i = 2; i < size; i++) {
			sum += Integer.parseInt(elementsOfLine[i]);
		}
		float average = (float) sum / (size - 2);

		contentForFile = contentForFile.concat(lastName).concat(", ").concat(firstName).concat(": Average score is ")
				+ average + ".";
		return contentForFile;
	}

	private void decipherFileContent() {

		try {
			System.out.println("Enter input/source file name:");
			String inputFileName = reader.readLine();
//			System.out.println("Enter output/destination file name:");
			String outputFileName = "decipher";
			if (isFileExist(getFullFileName(inputFileName))) {
				createFileIfNotExist(getFullFileName(outputFileName));
				BufferedReader fileReader = new BufferedReader(new FileReader(getFullFileName(inputFileName)));
				FileWriter fWriter = new FileWriter(getFullFileName(outputFileName));
				String cipheredText = fileReader.readLine();
				while (cipheredText != null) {
					String decipheredText = cipherDecipherString(cipheredText);
					System.out.println("Copying: ".concat(decipheredText).concat(("\n")));
					// read next line
					fWriter.write(decipheredText.concat("\n"));
					cipheredText = fileReader.readLine();
				}
				System.err.println("INFO: Copied from ".concat(getFullFileName(inputFileName)).concat(" to ")
						.concat(getFullFileName(outputFileName)));
				fWriter.close();
				fileReader.close();
			} else {
				System.err.println("ERROR: ".concat(getFullFileName(inputFileName)).concat(NOT_EXIST));

			}
			System.out.println();
		}

		// Catch block to handle if exception occurs
		catch (IOException e) {

			// Print the exception
			System.out.print(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void copyFileContentToOtherFile() {
		try {
			System.out.println("Enter input/source file name:");
			String inputFileName = reader.readLine();
			System.out.println("Enter output/destination file name:");
			String outputFileName = reader.readLine();
			if (isFileExist(getFullFileName(inputFileName))) {
				createFileIfNotExist(getFullFileName(outputFileName));
				BufferedReader fileReader = new BufferedReader(new FileReader(getFullFileName(inputFileName)));
				FileWriter fWriter = new FileWriter(getFullFileName(outputFileName));
				String lineToCopy = fileReader.readLine();
				while (lineToCopy != null) {
					System.out.println("Copying: ".concat(lineToCopy).concat(("\n")));
					// read next line
					fWriter.write(lineToCopy.concat("\n"));
					lineToCopy = fileReader.readLine();
				}
				System.err.println("INFO: Copied from ".concat(getFullFileName(inputFileName)).concat(" to ")
						.concat(getFullFileName(outputFileName)));
				fWriter.close();
				fileReader.close();
			} else {
				System.err.println("ERROR: ".concat(getFullFileName(inputFileName)).concat(NOT_EXIST));

			}
			System.out.println();
		}

		// Catch block to handle if exception occurs
		catch (IOException e) {

			// Print the exception
			System.err.print(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void writeIntoFile() {
		// Content to be assigned to a file
		// Custom input just for illustration purposes
		// Try block to check if exception occurs
		try {
			System.out.println("Enter file name you want to write into:");
			String fileName = reader.readLine();
			if (isFileExist(getFullFileName(fileName))) {
				// Create a FileWriter object
				// to write in the file
				FileWriter fWriter = new FileWriter(getFullFileName(fileName));

				while (Boolean.TRUE) {
					// Writing into file
					// Note: The content taken above inside the
					// string
					System.out.println("Enter Line You want to write to file:");
					String textToWrite = reader.readLine();
					if (textToWrite.isEmpty()) {
						break;
					}
					fWriter.write(textToWrite.concat("\n"));
				}
				// Closing the file writing connection
				fWriter.close();

				// Display message for successful execution of
				// program on the console
				System.err.println("INFO: CONTENT is successfully saved to ".concat(getFullFileName(fileName)));
			} else {
				System.err.println(getFullFileName(fileName).concat(NOT_EXIST));
			}
			System.out.println();
		}

		// Catch block to handle if exception occurs
		catch (IOException e) {

			// Print the exception
			System.err.print(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	/* This method will read from file and display on screen */
	private void readFromFile() {

		try {
			System.out.println("Enter file name you want to read:");
			String fileName = reader.readLine();
			if (isFileExist(getFullFileName(fileName))) {
				BufferedReader fileReader = new BufferedReader(new FileReader(getFullFileName(fileName)));
				String line = fileReader.readLine();
				System.out.println("CONTENT of ".concat(fileName).concat(".txt is:\n"));
				while (line != null) {
					System.out.println(line);
					// read next line
					line = fileReader.readLine();
				}
				fileReader.close();
			} else {
				System.err.println("ERROR: " + getFullFileName(fileName).concat(NOT_EXIST));
			}
			System.out.println();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private Boolean isFileExist(String fileName) {
		return new File(fileName).exists();
	}

	private void createFileIfNotExist(String fileName) {
		File file = new File(fileName);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("ERROR: while creating file " + fileName);
			}
	}

	private String getFullFileName(String fileName) {

		if (path.isEmpty()) {
			return fileName.concat(EXT_TXT_FILE);
		} else if (path.endsWith("/")) {
			return path.concat(fileName).concat(EXT_TXT_FILE);
		} else {
			return path.concat("/").concat(fileName).concat(EXT_TXT_FILE);
		}
	}

	// ## COPY THE FOLLOWING TWO LINES OF CODE AS FIELDS INTO YOUR 'FILES' CLASS -
	// DON'T MODIFY THEM
	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";

	// ## COPY THE FOLLOWING METHOD INTO YOUR 'FILES' CLASS TOO - AGAIN, DON'T
	// MODIFY ITS CONTENTS
	/**
	 * method to encipher and decipher a given String using parallel arrays (crypt1
	 * & crypt2)
	 *
	 * @param text A String containing text that is to be enciphered or deciphered
	 * @return A new String containing the result, e.g. the en/deciphered version of
	 *         the String provided as an input
	 */
	private static String cipherDecipherString(String text) {
		// declare variables we need
		int i, j;
		boolean found = false;
		String temp = ""; // empty String to hold converted text

		for (i = 0; i < text.length(); i++) // look at every character in text
		{
			found = false;
			if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
			{
				found = true; // yes!
				temp = temp + crypt2.charAt(j); // add the cipher character to temp
			} else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
			{
				found = true;
				temp = temp + crypt1.charAt(j);
			}

			if (!found) // to deal with cases where char is NOT in crypt2 or 2
			{
				temp = temp + text.charAt(i); // just copy across the character
			}
		}
		return temp;
	}

}
