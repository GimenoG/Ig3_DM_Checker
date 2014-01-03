package fr.umlv.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to write to a text file
 * 
 * @author Gimeno and Bourgain
 * 
 */
public class Log {

	public static void writeText(String path, String text) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(path, true);
			writer.write(text, 0, text.length());
			writer.write(System.getProperty("line.separator"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
