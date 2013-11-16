package fr.umlv.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/*
 * 
 * Classe pour tester les algo que je trouve sur le net
 * pour dézipper. Si meilleur/ plus efficace je changerai
 * 
 */


public class Archive {
	public  void unzip(String zipFile, File unzipBase) throws ZipException, IOException {
	    
	    int BUFFER = 2048;
	    File file = new File(zipFile);

	    ZipFile zip = new ZipFile(file);

	    Enumeration<?> zipFileEntries = zip.entries();

	    // Process each entry
	    while (zipFileEntries.hasMoreElements()) {
	      // grab a zip file entry
	      ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	      String currentEntry = entry.getName();
	      File destFile = new File(unzipBase, currentEntry);
	      // destFile = new File(newPath, destFile.getName());
	      File destinationParent = destFile.getParentFile();

	      // create the parent directory structure if needed
	      destinationParent.mkdirs();

	      if (!entry.isDirectory()) {
	        BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
	        int currentByte;
	        // establish buffer for writing file
	        byte data[] = new byte[BUFFER];

	        // write the current file to disk
	        FileOutputStream fos = new FileOutputStream(destFile);
	        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

	        // read and write until last byte is encountered
	        while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
	          dest.write(data, 0, currentByte);
	        }
	        dest.flush();
	        dest.close();
	        is.close();
	      }

	      if (currentEntry.endsWith(".zip")) {
	        // found a zip file, try to open
	        unzip(destFile.getAbsolutePath(), unzipBase);
	      }
	    }
	  }

}
