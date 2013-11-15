package fr.umlv.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ReadingArchive {
	/*
	 * public static void extractFolder(String zipFile) throws ZipException,
	 * IOException { System.out.println(zipFile); int BUFFER = 2048; File file =
	 * new File(zipFile); System.out.println(file); ZipFile zip = new
	 * ZipFile(file); System.out.println(zip); String newPath =
	 * zipFile.substring(0, zipFile.length() - 4); System.out.println(newPath);
	 * 
	 * new File(newPath).mkdir(); Enumeration zipFileEntries = zip.entries();
	 * System.out.println("avant while"); // Process each entry while
	 * (zipFileEntries.hasMoreElements()) { // grab a zip file entry ZipEntry
	 * entry = (ZipEntry) zipFileEntries.nextElement(); String currentEntry =
	 * entry.getName(); File destFile = new File(newPath, currentEntry);
	 * //destFile = new File(newPath, destFile.getName()); File
	 * destinationParent = destFile.getParentFile();
	 * 
	 * // create the parent directory structure if needed
	 * destinationParent.mkdirs(); System.out.println("avant if"); if
	 * (!entry.isDirectory()) { BufferedInputStream is = new
	 * BufferedInputStream(zip .getInputStream(entry)); int currentByte; //
	 * establish buffer for writing file byte data[] = new byte[BUFFER];
	 * 
	 * // write the current file to disk FileOutputStream fos = new
	 * FileOutputStream(destFile); BufferedOutputStream dest = new
	 * BufferedOutputStream(fos, BUFFER);
	 * 
	 * // read and write until last byte is encountered while ((currentByte =
	 * is.read(data, 0, BUFFER)) != -1) { dest.write(data, 0, currentByte); }
	 * dest.flush(); dest.close(); is.close(); }
	 * 
	 * if (currentEntry.endsWith(".zip")) { // found a zip file, try to open
	 * extractFolder(destFile.getAbsolutePath()); } } }
	 */

	// buffer to hold contents

	//	/Users/Gui/Documents/Lambda/loginnam12.zip
	//	/Users/Gui/Documents/Lambda/Archive2.zip

	public static void unzip(ZipFile zipFile, File unzipDir) {
		Enumeration<? extends ZipEntry> files = zipFile.entries();
		File f = null;
		FileOutputStream fos = null;

		while (files.hasMoreElements()) {
			try {
				ZipEntry entry = (ZipEntry) files.nextElement();
				InputStream eis = zipFile.getInputStream(entry);
				byte[] buffer = new byte[1024];
				int bytesRead = 0;

				f = new File(unzipDir.getAbsolutePath() + File.separator
						+ entry.getName());

				if (entry.isDirectory()) {
					f.mkdirs();
					continue;
				} else {
					f.getParentFile().mkdirs();
					f.createNewFile();
				}

				fos = new FileOutputStream(f);

				while ((bytesRead = eis.read(buffer)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						// Log
					}
				}
			}
		}
	}
}
