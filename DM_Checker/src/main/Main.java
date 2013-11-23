package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.martiansoftware.jsap.JSAPException;

import fr.umlv.IHM.IHM;
import fr.umlv.util.Options;
import fr.umlv.zip.ReadingArchive;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, JSAPException {
	//	Options.createOptions();
	//	Options.checkOptions(args);
		// /Users/Gui/Documents/Lambda/loginnam12.zip
		// /Users/Gui/Documents/Lambda/Archive2.zip
		//ReadingArchive ra = new ReadingArchive("/Users/Gui/Documents/Lambda/loginnam12.zip");
		//ra.unzip();
		//System.out.println(ReadingArchive.checkFile("/Users/Gui/Documents/Lambda/Archive.zip","logisdsdfnnam12"));
		//System.out.println(ra.isValid());
		
		JFrame frame = new IHM();
		JPanel panel = new JPanel();
		((IHM) frame).addBouttons(frame, panel);
		((IHM) frame).addMenu(frame);
	}
}