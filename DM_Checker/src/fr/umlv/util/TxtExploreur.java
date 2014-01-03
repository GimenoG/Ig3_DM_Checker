package fr.umlv.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.umlv.IHM.IHM;

/**
 * This class is able to manage the reading and writting in the text files for
 * the HMI.
 * 
 * @see IHM
 * 
 * @author Gimeno and Bourgain
 * 
 */
public class TxtExploreur {
	private TxtExploreur() {
	}

	/**
	 * parsh the txt of the criterion
	 * 
	 * @param pathFile
	 * @return
	 */
	public static List<String[]> getCriterions(String pathFile) {
		ArrayList<String[]> criterions = new ArrayList<>();
		try {
			InputStream ips = new FileInputStream(pathFile);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String[] tmp;
			while ((ligne = br.readLine()) != null) {
				tmp = ligne.split(":");
				for (int i = 1; i < tmp.length; i++) {
					tmp[1] = " " + tmp[1] + "\n";
				}
				criterions.add(tmp);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error on the file " + pathFile
					+ " in the fonction getCriterions");
		}
		return criterions;
	}

	public static List<String[]> getDataFile(String pathFile) {
		ArrayList<String[]> data = new ArrayList<>();
		try {
			InputStream ips = new FileInputStream(pathFile);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String[] tmp;
			while ((ligne = br.readLine()) != null) {
				tmp = ligne.split(":");
				data.add(tmp);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("Error on the file " + pathFile
					+ " in the fonction getDataFile");
		}
		return data;
	}

	/**
	 * return all the student number which are stored in the output path in
	 * parameter
	 */
	public static List<String> getReportStored(String reportPath) {
		ArrayList<String> numberStudents = new ArrayList<>();
		try {
			InputStream ips = new FileInputStream(reportPath);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String[] ligne;
			while ((ligne = br.readLine().split(":")) != null) {
				if (ligne[0].compareTo("") == 0) {
				} else {
					numberStudents.add(ligne[0]);
				}

			}
			br.close();
		} catch (Exception e) {
		}
		return numberStudents;
	}

	/**
	 * 
	 * Take an id and return the report if he exist, else it's return null
	 * 
	 * @param id
	 *            , path
	 * @return
	 */
	public static List<String> getReport(String id, String pathNots) {
		ArrayList<String> report = new ArrayList<>();
		try {
			InputStream ips = new FileInputStream(pathNots);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String[] ligne;
			while ((ligne = br.readLine().split(":")) != null) {

				if (ligne[0].compareTo(id) == 0) {
					for (int i = 2; i < ligne.length; i++) {
						report.add(ligne[i]);
					}
					br.close();
					return report;
				}

			}
			br.close();
		} catch (IOException ioe) {
			System.err.println("Lecture impossible dans " + pathNots
					+ File.separator + "nots.txt");
		}
		return null;
	}

	/**
	 * Save a report if he is new Else update the report
	 * 
	 * @param path
	 * @param msg
	 * @param id
	 */
	public static void saveReport(String path, String msg, String id) {
		String tmpPath = path.substring(0, path.length() - 7);
		tmpPath = tmpPath + "tmpnots.txt";
		try {
			InputStream ips = new FileInputStream(path);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			boolean writeline = false;
			while ((line = br.readLine()) != null) {
				System.out.println("Test sur " + line.split(":")[0]
						+ " sur la ligne et " + id + " de l'ihm");
				if (line.split(":")[0].compareTo(id) == 0) {
					System.out.println("remplace par " + msg);
					writeline = true;
					Log.writeText(tmpPath, msg);
				} else {
					if (line.split(":")[0].compareTo("") == 0) {
					} else {
						Log.writeText(tmpPath, line);
					}
				}

			}
			if (!writeline) {
				Log.writeText(tmpPath, msg);
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Ecriture impossible dans " + path
					+ File.separator + "nots.txt");
		}
		File nots = new File(path);
		File tmpnots = new File(tmpPath);
		if (nots.delete()) {
			tmpnots.renameTo(new File(path));

		}
	}
}
