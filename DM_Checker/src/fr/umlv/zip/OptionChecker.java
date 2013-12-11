package fr.umlv.zip;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class OptionChecker {

	private String path;
	private boolean verboseMode = false;
	public OptionChecker() {
	this.path=null;
	}

	public OptionChecker(String path) {
		this.path=path;
	}
	
	public void setVerbose(boolean bool){
		this.verboseMode= bool;
	}
	
	public void setPathFile(String path){
		this.path=path;
	}
	
	public boolean isOneTop(){
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
	
	public void testAutreOptionSurFichier(){
		/*
		 * Sur fichier ou sur fichier zip ??
		 * Je ne comprend pas où tu veux aller !!!!!
		 */
	}
}
