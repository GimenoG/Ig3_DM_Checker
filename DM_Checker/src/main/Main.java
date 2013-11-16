package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.martiansoftware.jsap.JSAPException;

import fr.umlv.options.Options;
import fr.umlv.zip.ReadingArchive;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, JSAPException {
		Options.createOptions();
		Options.checkOptions(args);
		// /Users/Gui/Documents/Lambda/loginnam12.zip
		// /Users/Gui/Documents/Lambda/Archive2.zip
		//ReadingArchive.unzip("/Users/Gui/Documents/Lambda/Archive.zip");
		System.out.println(ReadingArchive.checkFile("/Users/Gui/Documents/Lambda/Archive.zip","logisdsdfnnam12"));
	}
}