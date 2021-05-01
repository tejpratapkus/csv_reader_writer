package com.org;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReaderWriter {

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_READER_PATH = "C:/insurance/";
	private static final String FILE_WRITER_PATH = "C:/insurance/formatted/";
	private static final String COMMA_DELIMITER = ",";

	public static void main(String[] args) {
		File folder = new File(FILE_READER_PATH);
		listFilesForFolder(folder);

	}

	public static void listFilesForFolder(File folder) {
		FileWriter fileWriter = null;
		BufferedReader br = null;
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains(".csv")) {
				System.out.println(fileEntry.getName());

				String line = "";
				try {
					fileWriter = new FileWriter(FILE_WRITER_PATH + fileEntry.getName());
					br = new BufferedReader(new FileReader(fileEntry));
					while ((line = br.readLine()) != null) // returns a Boolean value
					{
						String[] customer = line.split(COMMA_DELIMITER); // use comma as separator

						if (customer[2] != null && !"".equals(customer[2]) && customer[2].contains("/")) {
							String date[] = customer[2].split("/");
							customer[2] = date[2] + "/" + date[0] + "/" + date[1];
						}
						List<String> customerList = new ArrayList<>();
						for(String cust : customer) {
							customerList.add(cust);
						}
						if(customerList.size() < 12) {
							int listSize = customerList.size();
							for(int i = listSize; i <= 12; i++) {
								customerList.add(listSize, "");
							}
						}
						fileWriter.append(customerList.get(0) + COMMA_DELIMITER + customerList.get(1) + COMMA_DELIMITER
								+ customerList.get(2) + COMMA_DELIMITER + customerList.get(3) + COMMA_DELIMITER
								+ customerList.get(4) + COMMA_DELIMITER + customerList.get(5) + COMMA_DELIMITER
								+ customerList.get(6) + COMMA_DELIMITER + customerList.get(7) + COMMA_DELIMITER
								+ customerList.get(8) + COMMA_DELIMITER + customerList.get(9) + COMMA_DELIMITER
								+ customerList.get(10) + COMMA_DELIMITER + customerList.get(11));
						// Add a new line separator after the header
						fileWriter.append(NEW_LINE_SEPARATOR);
					}
					System.out.println("Success for file : === " + fileEntry.getName());
				} catch (IOException e) {
					System.out.println("Error in CsvFileWriter !!!");
					e.printStackTrace();
				} finally {
					try {
						fileWriter.flush();
						fileWriter.close();
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

			}

			/*
			 * if (fileEntry.isDirectory()) { listFilesForFolder(fileEntry); } else {
			 * System.out.println(fileEntry.getName()); }
			 */
		}
	}

}
