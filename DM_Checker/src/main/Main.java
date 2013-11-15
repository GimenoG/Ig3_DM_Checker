package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipFile;
import fr.umlv.zip.ok;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
	//	/Users/Gui/Documents/Lambda/loginnam12.zip
	//	/Users/Gui/Documents/Lambda/Archive2.zip
        //fetch command line argument
		ZipFile zipFile = new ZipFile("/Users/Gui/Documents/Lambda/plusieursautop.zip");
		File unzipDir = new File("/Users/Gui/Documents/Lambda/plop");
		ok.unzip("/Users/Gui/Documents/Lambda/plusieursautop.zip");
		
	}
}