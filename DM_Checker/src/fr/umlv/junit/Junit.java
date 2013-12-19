package fr.umlv.junit;

import java.io.File;
import java.io.IOException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import fr.umlv.util.Log;

/**
 * This class allows you to execute JUnit and save the result in a file
 * 
 * @author Gimeno & Bourgain
 * 
 */
public class Junit {

	private static void executeJunit(Class<?> test, String fichierTxt) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new ExecuteJUnit(fichierTxt));
		@SuppressWarnings("unused")
		Result result = runner.run(test);
	}

	/**
	 * 
	 * @param pathPackageTest
	 *            path to the test files
	 * @param pathSrc
	 *            path to the source file
	 * @param resultat
	 *            path to the output file
	 * @param recursif
	 *            If the function must be recursive or not
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void execute(String pathPackageTest, String pathSrc,
			String resultat, boolean recursif) throws ClassNotFoundException,
			IOException {
		String[] tmpName = pathSrc.split(File.separator);
		String[] tmpName2 = tmpName[tmpName.length - 2].split("_");
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
							.replaceAll(File.separator, ".");

					testPath += "." + filename;
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