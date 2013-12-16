package fr.umlv.util;

import java.io.FileWriter;
import java.io.IOException;

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

	public void writeResultTest(String path) throws IOException {
		String nomdurepertoire = "nomdurepertoire";
		String nomPrenom = "NOM PRENOMS";
		String nomTest = "nomcompletdutest";
		boolean resultTest = true;

		writeText(path, "<dmchecker>");
		writeText(path, "<soft name=\"" + nomdurepertoire + "\" student=\""
				+ nomPrenom + "\">");

		// UNE BELLE BOUCLE QUI FAIT TOUS LES TESTS
		writeText(path, "<test name=\"" + nomTest + "\" result=" + resultTest
				+ ">");

		writeText(path, "</soft>");
		writeText(path, "</dmchecker>");
	}

}
