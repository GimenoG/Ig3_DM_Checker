package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import fr.umlv.zip.ReadingArchive;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		    String zipfile =  args[0];
		    

		    ReadingArchive.extractFolder(zipfile);
		
		}
		
	}
