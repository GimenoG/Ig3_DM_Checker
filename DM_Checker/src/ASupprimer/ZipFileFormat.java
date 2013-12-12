package ASupprimer;

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

public class ZipFileFormat implements Option {
	private String pathDestination;
	private ArrayList<String> projetEleve;
	private boolean verbose = false;
	private static String path;

	public ZipFileFormat(String path) {
		this.path = path;
		this.pathDestination = null;
		projetEleve = new ArrayList<>();
	}

	@Override
	public boolean endsWith(String s) {
		try {

			ZipFile fichier_zip = new ZipFile(path);
			Enumeration e = fichier_zip.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				String entryName = entry.getName();
				if (verbose)
					System.out
							.println("Le système compare entre le fichier contenu dans le zip : "
									+ entryName + " et le suffixe : " + s);
				if (entryName.endsWith(s))
					return false;
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return true;
	}

	@Override
	public boolean existe(String s) throws IOException {
		return checkFileExiste(path, s);
	}

	@Override
	public boolean interdit(String s) throws IOException {
		return checkFileExiste(path, s);

	}

	@Override
	public boolean beginsWith(String s) {
		try {

			ZipFile fichier_zip = new ZipFile(path);
			Enumeration e = fichier_zip.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				String entryName = entry.getName();
				if (verbose)
					System.out
							.println("Le système compare entre le fichier contenu dans le zip : "
									+ entryName + " et le préfixe : " + s);
				if (entryName.startsWith(s))
					return false;
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return true;
	}

	@Override
	public void setVerbose(boolean b) {
		this.verbose = b;
	}

	public void setDestination(String path) {
		this.pathDestination = path;
	}

	public ArrayList<String> getProjet() {
		return projetEleve;
	}

	public void unzip() {
		unzip(path);
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
	 * OneAtTop
	 * 
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
	private void unzip(String file) {
		try {
			File fSourceZip = new File(file);
			String zipPath = file.substring(0, file.length() - 4);

			File temp = new File(zipPath);
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
					destinationFilePath.delete();
				}
				
			}
			zipFile.close();
			
		} catch (NullPointerException e) {
			System.out.println("Error : " + e.toString());
		} catch (IOException ioe) {
			System.out.println("IOError :" + ioe);
		}

	}
	private boolean checkFileExiste(String file, String existe)
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
							.matches(existe)) {
						if (verbose)
							System.err.println(("Directory name is " + entry
									.getName()));
						return true;
					}
				} else {
					if (entry.getName()
							.substring(0, entry.getName().length() - 4)
							.matches(existe)) {
						if (verbose)
							System.err.println(("file name is " + entry
									.getName()));
						return true;
					}
				}
				if (entry.getName().endsWith(".zip")) {
					checkFileExiste(destinationFilePath.getAbsolutePath(),
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
}
