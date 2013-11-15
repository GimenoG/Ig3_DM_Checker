package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.zip.ReadingArchive;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// /Users/Gui/Documents/Lambda/loginnam12.zip
		// /Users/Gui/Documents/Lambda/Archive2.zip
		ReadingArchive.unzip("/Users/Gui/Documents/Lambda/Archive2.zip");		
	}
}