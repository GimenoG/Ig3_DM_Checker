package fr.umlv.options;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.QualifiedSwitch;

/**
 * This class is able to manage the option which are antry by the user.
 * Options use the JSAP libray, more information on http://www.martiansoftware.com/jsap/
 * 
 * @author Meriadoc
 *
 */


public class Options {
	private static JSAP jsap;
	
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
	
	public static void createOptions() throws JSAPException{
		jsap = new JSAP();
		
		QualifiedSwitch optD = (QualifiedSwitch) (new QualifiedSwitch("optD").setShortFlag('d').setLongFlag("destination").setList(true).setListSeparator(',').setHelp("Specifies the destination directory : -d <destination folder> or --destination <destination folder>."));
		//TODO qualifiedSwith ou autre ?
		QualifiedSwitch optV = (QualifiedSwitch) (new QualifiedSwitch("optV").setShortFlag('v').setLongFlag("verbose").setList(true).setListSeparator(',').setHelp("Print more information about errors on syserr."));
		//TODO help (a verif sens de l'option)
		QualifiedSwitch optO = (QualifiedSwitch) (new QualifiedSwitch("optO").setShortFlag('o').setLongFlag("onetop").setList(true).setListSeparator(',').setHelp("."));
		QualifiedSwitch optE = (QualifiedSwitch) (new QualifiedSwitch("optE").setShortFlag('e').setLongFlag("endswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which end with the paramter : -e <end string to bansih> or --endswith <end string to banish>"));
		QualifiedSwitch optB = (QualifiedSwitch) (new QualifiedSwitch("optB").setShortFlag('b').setLongFlag("beginswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which start with the paramter : -e <start string to bansih> or --endswith <start string to banish>"));
		//TODO qualifiedSwith ou autre ?
		QualifiedSwitch optX = (QualifiedSwitch) (new QualifiedSwitch("optX").setShortFlag('x').setLongFlag("existe").setList(true).setListSeparator(',').setHelp("Check if the folder name in parameter already existe in the destination directory. If it's true the folder won't be treaty"));
		QualifiedSwitch optI = (QualifiedSwitch) (new QualifiedSwitch("optI").setShortFlag('i').setLongFlag("interdit").setList(true).setListSeparator(',').setHelp("Check the lack of the file named <param> in the repository. The head directory isn't concern by is option.\nUse : -I <regex name> or --interdit <regex name>"));
		
		jsap.registerParameter(optD);
		jsap.registerParameter(optV);
		jsap.registerParameter(optO);
		jsap.registerParameter(optE);
		jsap.registerParameter(optB);
		jsap.registerParameter(optX);
		jsap.registerParameter(optI);
	}
}