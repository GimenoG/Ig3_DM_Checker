package fr.umlv.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import fr.umlv.util.Log;

public class ReadingArchive {
	private static Log log = new Log();
	private static String path;
	private boolean verbose = false;
	private String idName;
	private String idKey;
	private String pathDestination;
	private ArrayList<String> projetEleve;

	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public ReadingArchive(String path) {
		this.path = path;
		this.pathDestination=null;
		projetEleve = new ArrayList<>();
	}

	/**
	 * If you want activate the verbose mode
	 * 
	 * @param boolean
	 */
	public void setVerbose(boolean b) {
		this.verbose = b;
	}

	public void setIdName(String id) {
		this.idName = id;
	}

	public void setIdKey(String id) {
		this.idKey = id;
	}

	public void setDestination(String path){
		this.pathDestination=path;
	}
	
	public ArrayList<String> getProjet(){
		return projetEleve;
	}
	
	/**
	 * Test if path is a zip file
	 * 
	 * @return 0 if true, and error if false
	 */
	public String isValid() {
		try (ZipFile zipFile = new ZipFile(path)) {
			if (zipFile != null) {
				zipFile.close();
				return "0";
			} else
				return "This file isn't a .zip file";
		} catch (ZipException e) {
			return "Error : " + e.toString();
		} catch (IOException e) {
			return "Error : " + e.toString();
		}
	}

	
	
	/**
	 * StartWith
	 * @param start
	 * @return
	 */
	public boolean checkFileStart(String start) {
		try {

			ZipFile fichier_zip = new ZipFile(path);
			Enumeration e = fichier_zip.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				String entryName = entry.getName();
				if (verbose)
					System.out
							.println("Le système compare entre le fichier contenu dans le zip : "
									+ entryName + " et le préfixe : " + start);
				if (entryName.startsWith(start))
					return false;
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return true;
	}

	/**
	 * EndsWith
	 * @param ends
	 * @return
	 */
	public boolean checkFileEnds(String ends) {
		try {

			ZipFile fichier_zip = new ZipFile(path);
			Enumeration e = fichier_zip.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				String entryName = entry.getName();
				if (verbose)
					System.out
							.println("Le système compare entre le fichier contenu dans le zip : "
									+ entryName + " et le suffixe : " + ends);
				if (entryName.endsWith(ends))
					return false;
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return true;
	}

	/**
	 * OneAtTop
	 * @return
	 */
	public boolean isFolderAtTop() {
		try {
			ZipFile fichier_zip = new ZipFile(path);
			Enumeration e = fichier_zip.entries();
			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				return entry.isDirectory();
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return false;
	}

	public void unzip(String file) {
		try {
			File fSourceZip = new File(file);
			String zipPath = file.substring(0, file.length() - 4);

			if (idName != null) {
				File temp = new File(idName);
			}
			// File temp = new File(idKey);

			File temp = new File(zipPath);
			// System.out.println(temp);
			temp.mkdir();
			if (verbose)
				System.err.println(zipPath + " created");
			projetEleve.add(zipPath);

			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				File destinationFilePath = new File(zipPath, entry.getName());

				destinationFilePath.getParentFile().mkdirs();
				if (entry.isDirectory()) {
					continue;
				} else {
					if (verbose)
						System.err.println("Extracting " + destinationFilePath);
					BufferedInputStream bis = new BufferedInputStream(
							zipFile.getInputStream(entry));

					int b;
					byte buffer[] = new byte[2018];

					FileOutputStream fos = new FileOutputStream(
							destinationFilePath);
					BufferedOutputStream bos = new BufferedOutputStream(fos,
							2018);

					while ((b = bis.read(buffer, 0, 2018)) != -1) {
						bos.write(buffer, 0, b);
					}
					bos.flush();
					bos.close();
					bis.close();
				}
				if (entry.getName().endsWith(".zip")) {
					unzip(destinationFilePath.getAbsolutePath());
				}
				destinationFilePath.delete();
			}
			zipFile.close();
		} catch (NullPointerException e) {
			System.out.println("Error : " + e.toString());
		} catch (IOException ioe) {
			System.out.println("IOError :" + ioe);
		}

	}

	/**
	 * Option -X/--existe
	 * This method test if there is a file or directory is named "existe" in the
	 * archive
	 * 
	 * @param file
	 * @param existe
	 * @return true or false
	 * @throws IOException
	 */
	private boolean checkFileExisteLL(String file, String existe)
			throws IOException {
		try {

			File fSourceZip = new File(file);
			String zipPath = file.substring(0, file.length() - 4);
			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				File destinationFilePath = new File(zipPath, entry.getName());

				if (entry.isDirectory()) {
					if (entry.getName()
							.substring(0, entry.getName().length() - 1)
							.equals(existe)) {
						if (verbose)
							System.err.println(("Directory name is " + entry
									.getName()));
						return true;
					}
				} else {
					if (entry.getName()
							.substring(0, entry.getName().length() - 4)
							.equals(existe)) {
						if (verbose)
							System.err.println(("file name is " + entry
									.getName()));
						return true;
					}
				}
				if (entry.getName().endsWith(".zip")) {
					checkFileExisteLL(destinationFilePath.getAbsolutePath(),
							existe);
				}
				zipFile.close();
			}

		} catch (IOException ioe) {
			if (verbose)
				System.err.println(("IOError :" + ioe));
		}
		return false;
	}
	
	public boolean checkFileExiste(String existe)
			throws IOException { 
		return checkFileExisteLL(path, existe);
	}

}