package fr.umlv.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipManager extends ArchiveRunner {
	private ArrayList<String> projetEleve;
	private File destination = new File("/Users/Gui/Downloads/test_projet");

	public ZipManager(String source, String destination) {
		super(source, destination);
		this.projetEleve = new ArrayList<String>();
	}

	@Override
	public void extract() {
		/*
		 * Ouai super, j'extrait quoi ?
		 */
	}

	@Override
	public void extract(String pathAExtraire) {

		try {
			File fSourceZip = new File(pathAExtraire);
			String zipPath = pathAExtraire.substring(0,
					pathAExtraire.length() - 4);
			String[] nameFolder;
			String Folder;

			nameFolder = zipPath.split(File.separator);
			Folder = destination.getCanonicalPath() + File.separator
					+ nameFolder[nameFolder.length - 1];
			System.out.println("Folder : " + Folder);
			File temp = new File(Folder);
			temp.mkdir();
			// if (verbose)
			// System.err.println(Folder + " created");
			projetEleve.add(Folder);

			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				File destinationFilePath = new File(Folder, entry.getName());

				destinationFilePath.getParentFile().mkdirs();
				if (entry.isDirectory()) {
					continue;
				} else {
					// if (verbose)
					// System.err.println("Extracting " + destinationFilePath);
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
					extract(destinationFilePath.getAbsolutePath());
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

	@Override
	public void setPath() {
		/*
		 * Elle fait quoi cette méthode ? Elle set quelle path ? Destination ?
		 * Fichier à extraire ?
		 */

	}

	@Override
	public ArrayList<String> getPath() {
		return projetEleve;
	}

}
