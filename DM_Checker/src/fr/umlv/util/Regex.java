package fr.umlv.util;



import java.io.File;



public class Regex {



	public static String idName(String nameFile) {
	
		String[] id = nameFile.split("_");
		String[] id2;
		id2 = id[0].split(File.separator);
		return id2[id2.length - 1];
	
	}
	
	
	
	public static String idKey(String nameFile) {
	
		String[] id = nameFile.split("_");
		return id[id.length - 1].substring(0, id[id.length - 1].length() - 4);
	}



	public static String nameFolder(String s) {
	
		String[] id = s.split(File.separator);
		return id[id.length - 2];
	}



}