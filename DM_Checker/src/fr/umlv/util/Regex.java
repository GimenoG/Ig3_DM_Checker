package fr.umlv.util;

import java.io.File;

public class Regex {

	
	public boolean endsWith(String path,String ends){
		String pathR = path.substring(0, path.length() - 4); // On supprime le .zip
		return pathR.endsWith(ends);
	}
	
	public boolean startWith(String path,String start){
		String file = new File("/Users/Gui/Documents/Lambda/Archive2.zip").getName();
		return file.startsWith(start);
	}

}
