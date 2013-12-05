package fr.umlv.main;
import com.martiansoftware.jsap.JSAPException;

import fr.umlv.util.Options;

public class Main {

	public static void main(String[] args) throws JSAPException {
		Options.createOptions();
		Options.checkOptions(args);
		Options.Launch();
		// /Users/Gui/Documents/Lambda/loginnam12.zip
		// /Users/Gui/Documents/Lambda/Archive2.zip
		// ReadingArchive ra = new
		// ReadingArchive("/Users/Gui/Documents/Lambda/Archive.zip");
		// System.out.println(ra.isFolderAtTop());
		// ra.checkFileStart("toto");
		// ra.setVerbose(true);
		// ra.unzip("/Users/Gui/Documents/Lambda/loginnam12.zip");
		// System.out.println(ReadingArchive.checkFile("/Users/Gui/Documents/Lambda/Archive.zip","logisdsdfnnam12"));
		// System.out.println(ra.isValid());
		// ra.checkFileStart("toto");
		// System.out.println("-------------------------");
		// ra.checkFileEnds("toto");
		// JFrame frame = new IHM();
		// JPanel panel = new JPanel();
		// ((IHM) frame).addBouttons(frame, panel);
		// ((IHM) frame).addMenu(frame);
	}

}
