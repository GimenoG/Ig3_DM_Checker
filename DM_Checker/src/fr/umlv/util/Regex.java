package fr.umlv.util;

import java.io.File;
import java.net.IDN;

public class Regex {

	//A supprimer
	public static boolean endsWith(String ends,String entryName){
		return entryName.endsWith(ends);
	}
	
	//A supprimer
	public boolean startWith(String path,String start){
		String file = new File("/Users/Gui/Documents/Lambda/Archive2.zip").getName();
		return file.startsWith(start);
	}

	public static String changeURL(String s){
		String result;
		result = s.replace("\\" , "\\");
		return result;
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
	
	public static String nameFolder(String s){
		String[] id;
		id=s.split("/");
		return id[id.length-2];
	}
	
	public static void main(String[] args) {
//		String file = "NOM PRENOMS_nomdonneparletudiantasonrendu_00000.zip";
//		System.out.println(idName(file));
//		System.out.println(substitute(idName(file)));
		//System.out.println(endsWith("cou", "coucou"));
		String file = "/Users/Gui/Documents/Lambda/lezipdezip/ HANY WUTHEARA_hawu_projet_9093";
		System.out.println(nameFolder(file));
	}
	
	public static String substitute(String s){
		return s.replaceAll("\\s","_");
	}
}
