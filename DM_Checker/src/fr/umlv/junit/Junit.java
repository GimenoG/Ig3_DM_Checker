package fr.umlv.junit;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import fr.umlv.util.Log;

public class Junit {

	private static void executeJunit(Class<?> test, String fichierTxt) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new ExecuteJUnit(fichierTxt));
		@SuppressWarnings("unused")
		Result result = runner.run(test);
	}

	public void execute(String pathPackageTest, String pathSrc,
			String resultat, boolean recursif) throws ClassNotFoundException,
			IOException {
		//TODO
		System.out.println(pathPackageTest);
		String[] tmpName = pathSrc.split(Pattern.quote(File.separator));
		String[] tmpName2 = tmpName[tmpName.length - 2].split(Pattern.quote("_"));
		String name = tmpName2[0];

		Log.writeText(resultat, "<dmchecker>");
		Log.writeText(
				resultat,
				"<soft name=\""
						+ pathPackageTest.substring(pathSrc.length() + 1)
						+ "\" student=\"" + name + "\">");

		File file = new File(pathPackageTest);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].getPath().endsWith(".java")) {
					Log.writeText(resultat,
							"<class name=\"" + files[i].getName() + "\">");
					String testPath = pathPackageTest;

					String filename = files[i].getName();
					filename = filename.substring(0, filename.length() - 5);

					testPath = testPath.substring(pathSrc.length() + 1)
							.replaceAll(Pattern.quote(File.separator), Pattern.quote("."));

					testPath += "." + filename;
					//TODO
					System.out.println(testPath);
					Class<?> test = Class.forName(testPath);
					executeJunit(test, resultat);
					Log.writeText(resultat, "</class name>");
				}
			}
		}
		Log.writeText(resultat, "</soft>");
		Log.writeText(resultat, "</dmchecker>");

	}
}