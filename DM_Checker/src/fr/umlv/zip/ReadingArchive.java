package fr.umlv.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import fr.umlv.util.Log;

public class ReadingArchive {
	private static Log log = new Log();
	private static String path;

	/**
	 * Constructor
	 * 
	 * @param path
	 */
	public ReadingArchive(String path) {
		this.path = path;
	}

	/**
	 * This method unzipped the file passed in constructor
	 */
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

	private void unzip(String file) {
		try {
			File fSourceZip = new File(file);
			String zipPath = file.substring(0, file.length() - 4);
			File temp = new File(zipPath);
			temp.mkdir();
			System.out.println(zipPath + " created");

			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				File destinationFilePath = new File(zipPath, entry.getName());

				destinationFilePath.getParentFile().mkdirs();
				if (entry.isDirectory()) {
					continue;
				} else {
					System.out.println("Extracting " + destinationFilePath);

					BufferedInputStream bis = new BufferedInputStream(
							zipFile.getInputStream(entry));

					int b;
					byte buffer[] = new byte[1024];

					FileOutputStream fos = new FileOutputStream(
							destinationFilePath);
					BufferedOutputStream bos = new BufferedOutputStream(fos,
							1024);

					while ((b = bis.read(buffer, 0, 1024)) != -1) {
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
		} catch (IOException ioe) {
			System.out.println("IOError :" + ioe);
		}

	}

	/**
	 * This method test if there is a file or directory is named "existe" in the
	 * archive
	 * 
	 * @param file
	 * @param existe
	 * @return true or false
	 * @throws IOException
	 */
	public boolean checkFile(String file, String existe)
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
						log.writeText("/Users/Gui/Documents/Lambda/Log.txt",
								"Nom du repertoire " + entry.getName() + "\n");
						return true;
					}
				} else {
					if (entry.getName()
							.substring(0, entry.getName().length() - 4)
							.equals(existe))
						return true;
					log.writeText("/Users/Gui/Documents/Lambda/Log.txt",
							"Nom du fichier " + entry.getName() + "\n");
				}
				if (entry.getName().endsWith(".zip")) {
					checkFile(destinationFilePath.getAbsolutePath(), existe);
				}
				zipFile.close();
			}

		} catch (IOException ioe) {
			log.writeText("/Users/Gui/Documents/Lambda/Log.txt", "IOError :"
					+ ioe);
		}
		return false;
	}

}