package fr.umlv.util;

import java.io.File;
import java.net.IDN;

public class Regex {

	
	public boolean endsWith(String path,String ends){
		String pathR = path.substring(0, path.length() - 4);
		return pathR.endsWith(ends);
	}
	
	public boolean startWith(String path,String start){
		String file = new File("/Users/Gui/Documents/Lambda/Archive2.zip").getName();
		return file.startsWith(start);
	}

	public static String idName(String nameFile){
		String[] id;
		id=nameFile.split("_");
		return id[0];
	}
	
	public static String idKey(String nameFile){
		String[] id;
		id=nameFile.split("_");
		return id[2].substring(0, id[2].length() - 4);
	}
	
	public static void main(String[] args) {
		String file = "NOM PRENOMS_nomdonneparletudiantasonrendu_00000.zip";
		System.out.println(idName(file));
		System.out.println(idKey(file));
	}
}
