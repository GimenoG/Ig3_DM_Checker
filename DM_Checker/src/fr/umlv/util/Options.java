package fr.umlv.util;

import java.awt.geom.Path2D;
import java.nio.file.Paths;
import java.util.Iterator;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.QualifiedSwitch;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

import fr.umlv.zip.ReadingArchive;

/**
 * This class is able to manage the option which are antry by the user.
 * Options use the JSAP libray, more information on http://www.martiansoftware.com/jsap/
 * 
 * @author Meriadoc
 *
 */


public class Options {
	private static JSAP jsap;
	private static JSAPResult config;
	private static int mode;
	
	private Options(){}
	
	/**
	 * Create the software options able to be use in the programme.
	 * It's a static méthode whiche can be use evrywhere in the code.
	 * 
	 * Only must be use à the initiation of the soft.
	 * 
	 * @see JSAP, QualifiedSwitch, registerParameter
	 * @throws JSAPException
	 */
	
	private static int getMode(JSAPResult config){
		//check if there is only one flag set
		if((config.getBoolean("opt1")&&config.getBoolean("opt2"))
				||(config.getBoolean("opt1")&&config.getBoolean("opt3"))
				||(config.getBoolean("opt1")&&config.getBoolean("opt4"))
				||(config.getBoolean("opt3")&&config.getBoolean("opt2"))
				||(config.getBoolean("opt4")&&config.getBoolean("opt2"))
				||(config.getBoolean("opt3")&&config.getBoolean("opt4"))
				||(!config.getBoolean("opt1")&&!config.getBoolean("opt2")&&!config.getBoolean("opt3")&&!config.getBoolean("opt4"))
				){
			return -1;
		}
		if(config.getBoolean("opt1"))
			return 1;
		if (config.getBoolean("opt2"))
			return 2;
		if (config.getBoolean("opt3"))
			return 3;
		return 0;
	}
	
	
	public static void createOptions() throws JSAPException{
		//TODO option forcé ?
		
		jsap = new JSAP();
		
		Switch opt1 = (Switch) new Switch("opt1").setShortFlag('1').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt2 = (Switch) new Switch("opt2").setShortFlag('2').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt3 = (Switch) new Switch("opt3").setShortFlag('3').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt4 = (Switch) new Switch("opt4").setShortFlag('4').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		
		jsap.registerParameter(opt1);
		jsap.registerParameter(opt2);
		jsap.registerParameter(opt3);
		jsap.registerParameter(opt4);
		
		Switch optV = (Switch) new Switch("opt4").setShortFlag('v').setLongFlag("verbose").setHelp("softmode2 the return.");
		
		
		FlaggedOption optD = (FlaggedOption) (new FlaggedOption("optD").setShortFlag('d').setLongFlag("destination").setList(true).setListSeparator(',').setHelp("Specifies the destination directory : -d <destination folder> or --destination <destination folder>."));
		//TODO help (a verif sens de l'option)
		FlaggedOption optO = (FlaggedOption) (new FlaggedOption("optO").setShortFlag('o').setLongFlag("onetop").setList(true).setListSeparator(',').setHelp("."));
		FlaggedOption optE = (FlaggedOption) (new FlaggedOption("optE").setShortFlag('e').setLongFlag("endswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which end with the paramter : -e <end string to bansih> or --endswith <end string to banish>"));
		FlaggedOption optB = (FlaggedOption) (new FlaggedOption("optB").setShortFlag('b').setLongFlag("beginswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which start with the paramter : -b <start string to bansih> or --beginswith <start string to banish>"));
		//TODO qualifiedSwith ou autre ?
		FlaggedOption optX = (FlaggedOption) (new FlaggedOption("optX").setShortFlag('x').setLongFlag("existe").setList(true).setListSeparator(',').setHelp("Check if the folder name in parameter already existe in the destination directory. If it's true the folder won't be treaty"));
		FlaggedOption optI = (FlaggedOption) (new FlaggedOption("optI").setShortFlag('i').setLongFlag("interdit").setList(true).setListSeparator(',').setHelp("Check the lack of the file named <param> in the repository. The head directory isn't concern by is option.\nUse : -I <regex name> or --interdit <regex name>"));
		
		jsap.registerParameter(optV);
		jsap.registerParameter(optD);
		jsap.registerParameter(optO);
		jsap.registerParameter(optE);
		jsap.registerParameter(optB);
		jsap.registerParameter(optX);
		jsap.registerParameter(optI);
		
		UnflaggedOption param = (UnflaggedOption) new UnflaggedOption("param").setStringParser(JSAP.STRING_PARSER).setRequired(true).setGreedy(true);
		
		jsap.registerParameter(param);
	}
	/**
	 * 
	 * Check the param in a table of String (the argv table of the main)
	 * 
	 * @param args
	 */
	public static void checkOptions(String[] args){
		config = jsap.parse(args);
		//TODO revoire la gestion de l'erreur
		if (!config.success()) {
            
            System.err.println();
            //TODO check les erreurs dans un logger pour le mode verbose ?
            // print out specific error messages describing the problems
            // with the command line, THEN print usage, THEN print full
            // help.  This is called "beating the user with a clue stick."
            for (Iterator errs = config.getErrorMessageIterator();
                    errs.hasNext();) {
                System.err.println("Error: " + errs.next());
            }
            
            System.err.println();
            System.err.println("Usage: java "+ Options.class.getName());
            System.err.println(" "+ jsap.getUsage());
            System.err.println();
            System.err.println(jsap.getHelp());
            System.exit(1);
		}	
	}
	/**
	 * Launch the software with the rigth option.
	 * 
	 */
	//TODO pattern ? Options porte aussi l'execution du prog :s mais je ne voi pas comment faire autrement
	public static void Launch(){
		ReadingArchive ra = new ReadingArchive(config.getString("path"));//ordre ?
		
		
		switch(getMode(config)){
			case 1 : System.out.println("scenario 1");break;
			case 2 : System.out.println("scenario 2");break;
			case 3 : System.out.println("scenario 3");break;
			case 4 : System.out.println("scenario 4");break;
			default : System.err.println("Erreur argurment du mode d'action");
		}
		
		//option commune a tout
		if(config.getBoolean("optE")){
			//modif du path (retire a la fin
		}
		if(config.getBoolean("optB")){
			//modif du path (retire au debit
		}
		if(config.getBoolean("optV")){
			//creation du logger ? -> ajout dans le bordel
			//set bolleen dans readingarchive
		}
		//mode d'utilisation
		if(config.getBoolean("optX")){
			String [] fileToCheck = config.getStringArray("optX");
			//parcours du dossier a la recherche du fichier / des fichiers
			//affiche le resultat
		}
		else if(config.getBoolean("optI")){
			String [] fileToCheck = config.getStringArray("optI");
			//check la présence du fichier / des fichiers
			//affiche le résultat
		}
		if(config.getBoolean("optO")){
			//check format de la racine de l'archive
		}
		
	}
	
	
}