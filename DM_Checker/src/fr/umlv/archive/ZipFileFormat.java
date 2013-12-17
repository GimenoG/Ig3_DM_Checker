package fr.umlv.archive;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import fr.umlv.util.Regex;

public class ZipFileFormat implements ArchiveOptionChecker {

	private boolean verbose;
	private ArrayList<String> listZip;

	public ZipFileFormat() {
		this.listZip = new ArrayList<String>();
		this.verbose = false;
	}

	@Override
	public boolean endsWith(String src, String s) {
		try {

			ZipFile fichier_zip = new ZipFile(src);
			Enumeration<? extends ZipEntry> e = fichier_zip.entries();

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

	public static void main(String[] args) throws IOException {
		ZipFileFormat zff = new ZipFileFormat();
		zff.verbose = true;
		zff.existe("/Users/Gui/Downloads/assets.zip", "sol7.png");

	}

	@Override
	public boolean existe(String src, String s) throws IOException {
		try {
			File fSourceZip = new File(src);
			String zipPath = src.substring(0, src.length() - 4);
			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration<? extends ZipEntry> e = zipFile.entries();

			while (e.hasMoreElements()) {

				ZipEntry entry = (ZipEntry) e.nextElement();
				File destinationFilePath = new File(zipPath, entry.getName());

				if (entry.isDirectory()) {
					String tmp = entry.getName().substring(0,
							entry.getName().length() - 1);
					String[] tmpName = tmp.split(Pattern.quote(File.separator));
					String name = tmpName[tmpName.length - 1];
					if (name.matches(s)) {
						if (verbose)
							System.err
							.println(("There is a folder that call " + name));
						return true;
					}
				} else {
					String tmp = entry.getName().substring(0,
							entry.getName().length());
					String[] tmpName = tmp.split(Pattern.quote(File.separator));
					String name = tmpName[tmpName.length - 1];
					System.out.println(name);
					if (name.matches(s)) {
						if (verbose)
							System.err
							.println(("There is a file that call " + name));
						return true;
					}
				}
				if (entry.getName().endsWith(".zip")) {
					existe(destinationFilePath.getAbsolutePath(), s);
				}
			}

		} catch (IOException ioe) {
			if (verbose)
				System.err.println(("IOError :" + ioe));
		}
		return false;
	}

	@Override
	public boolean beginsWith(String src, String s) {
		try {

			ZipFile fichier_zip = new ZipFile(src);
			Enumeration<? extends ZipEntry> e = fichier_zip.entries();

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

	/*@Override
	public boolean oneTop(String src) {
		try {
			ZipFile fichier_zip = new ZipFile(src);
			Enumeration<? extends ZipEntry> e = fichier_zip.entries();
			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				return entry.isDirectory();
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return false;
	}*/
	@Override
	public boolean oneTop(String src) {
		try {
			ZipFile fichier_zip = new ZipFile(src);
			Enumeration<? extends ZipEntry> e = fichier_zip.entries();
			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();
				File zipName = new File(entry.getName());
				if (zipName.getParent() == null)
					return false;
				else
					return true;
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}
		return false;
	}

	@Override
	public String extract(String src, String destination) {
		if (destination == null) {
			destination = src.substring(0, src.length() - 4);
		}

		try {
			File fSourceZip = new File(src);

			if (!oneTop(src)) {
				File temp = new File(destination);
				temp.mkdir();
				if (verbose)
					System.err.println(destination + " created");
				destination += File.separator + Regex.nameZip(src);
				System.out.println("fichier a extraire dans " + destination);
			} else {
				if (destination == null) {
					@SuppressWarnings("null")
					String[] tmp = destination.split(Pattern.quote(File.separator));
					destination = destination.substring(0, destination.length()
							- tmp[tmp.length - 1].length());
				}
			}

			ZipFile zipFile = new ZipFile(fSourceZip);
			Enumeration<? extends ZipEntry> e = zipFile.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				File destinationFilePath = new File(destination,
						entry.getName());

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

			}
			zipFile.close();

		} catch (NullPointerException e) {
			System.out.println("Error : " + e.toString());
		} catch (IOException ioe) {
			System.out.println("IOError :" + ioe);
		}
		return destination;
	}

	@Override
	public ArrayList<String> getPathArchive(String src) {
		try {
			ZipFile fichier_zip = new ZipFile(src);
			Enumeration<? extends ZipEntry> e = fichier_zip.entries();

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				if (entry.getName().endsWith(".zip"))
					listZip.add(entry.getName());
			}
			fichier_zip.close();
		} catch (IOException ex) {
			System.out.println("Erreur" + ex);
		}

		return listZip;
	}

	@Override
	public boolean isValid(String src) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		if (fileNameMap.getContentTypeFor(src).equals("application/zip"))
			return true;
		return false;
	}

}
