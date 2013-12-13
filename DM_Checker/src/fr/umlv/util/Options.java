	package fr.umlv.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

/**
 * This class is able to manage the option which are antry by the user.
 * Options use the JSAP librairy, more information on http://www.martiansoftware.com/jsap/
 * 
 * @author Meriadoc
 *
 */


public class Options {
	private static JSAP jsap;
	private static JSAPResult config;
	private int mode;
	
	private boolean verbose = false;
	private boolean oneTop = false;
	private String [] endWith;
	private String [] beginWith;
	private String [] forbidden;
	private String [] be;
	private String destination;
	private String source;
	private boolean forceOneTop=false;
	private String [] forceBeginsWith;
	private String[] forceEndsWith;
	private String[] forceBe;
	private String[] forceinterdit;
	private String junitPath;

	public Options(){
	}
	
	/**
	 * Create the software options able to be use in the programme.
	 * It's a static mï¿½thode whiche can be use evrywhere in the code.
	 * 
	 * Only must be use ï¿½ the initiation of the soft.
	 * 
	 * @see JSAP, QualifiedSwitch, registerParameter
	 * @throws JSAPException
	 */
	
	private int mode(JSAPResult config){
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
		return 4;
	}
	
	
	public void createOptions() throws JSAPException{
		
		jsap = new JSAP();
		
		Switch opt1 = (Switch) new Switch("opt1").setShortFlag('1').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt2 = (Switch) new Switch("opt2").setShortFlag('2').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt3 = (Switch) new Switch("opt3").setShortFlag('3').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		Switch opt4 = (Switch) new Switch("opt4").setShortFlag('4').setLongFlag(JSAP.NO_LONGFLAG).setHelp("softmode2 the return.");
		
		jsap.registerParameter(opt1);
		jsap.registerParameter(opt2);
		jsap.registerParameter(opt3);
		jsap.registerParameter(opt4);
		
		Switch optV = (Switch) new Switch("optV").setShortFlag('v').setLongFlag("verbose").setHelp("softmode2 the return.");
		jsap.registerParameter(optV);
		
		
		FlaggedOption optD = (FlaggedOption) (new FlaggedOption("optD").setShortFlag('d').setLongFlag("destination").setList(true).setListSeparator(',').setHelp("Specifies the destination directory : -d <destination folder> or --destination <destination folder>."));
		Switch optO = (Switch)(new Switch("optO").setShortFlag('o').setLongFlag("onetop").setHelp("Require only one folder at the top"));
		FlaggedOption optE = (FlaggedOption) (new FlaggedOption("optE").setShortFlag('e').setLongFlag("endswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which end with the paramter : -e <end string to bansih> or --endswith <end string to banish>"));
		FlaggedOption optB = (FlaggedOption) (new FlaggedOption("optB").setShortFlag('b').setLongFlag("beginswith").setList(true).setListSeparator(',').setHelp("Doesn't work with the files which start with the paramter : -b <start string to bansih> or --beginswith <start string to banish>"));
		FlaggedOption optX = (FlaggedOption) (new FlaggedOption("optX").setShortFlag('x').setLongFlag("existe").setAllowMultipleDeclarations(true).setList(true).setListSeparator(',').setHelp("Check if the folder name in parameter already existe in the destination directory. If it's true the folder won't be treaty"));
		FlaggedOption optI = (FlaggedOption) (new FlaggedOption("optI").setShortFlag('i').setLongFlag("interdit").setAllowMultipleDeclarations(true).setList(true).setListSeparator(',').setHelp("Check the lack of the file named <param> in the repository. The head directory isn't concern by is option.\nUse : -I <regex name> or --interdit <regex name>"));
		
		//Option force : pas de long flag !
		FlaggedOption optForceI = (FlaggedOption) new FlaggedOption("optForceI").setList(true).setShortFlag('I').setListSeparator(',').setAllowMultipleDeclarations(true).setLongFlag(JSAP.NO_LONGFLAG).setHelp("Check the lack of the file named <param> in the repository. The head directory isn't concern by is option.\nUse : -I <regex name> or --interdit <regex name>");
		FlaggedOption optForceX = (FlaggedOption) new FlaggedOption("optForceX").setList(true).setShortFlag('X').setListSeparator(',').setAllowMultipleDeclarations(true).setLongFlag(JSAP.NO_LONGFLAG).setHelp("Check if the folder name in parameter already existe in the destination directory. If it's true the folder won't be treaty");
		FlaggedOption optForceB = (FlaggedOption) new FlaggedOption("optForceB").setList(true).setShortFlag('B').setListSeparator(',').setAllowMultipleDeclarations(true).setLongFlag(JSAP.NO_LONGFLAG).setHelp("Doesn't work with the files which start with the paramter : -b <start string to bansih> or --beginswith <start string to banish>");
		FlaggedOption optForceE = (FlaggedOption) new FlaggedOption("optForceE").setList(true).setShortFlag('E').setListSeparator(',').setAllowMultipleDeclarations(true).setLongFlag(JSAP.NO_LONGFLAG).setHelp("Doesn't work with the files which end with the paramter : -e <end string to bansih> or --endswith <end string to banish>");
		Switch optForceO = (Switch)(new Switch("optForceO").setShortFlag('O').setLongFlag(JSAP.NO_LONGFLAG).setHelp("Require only one folder at the top"));
		
		
		
		jsap.registerParameter(optForceI);
		jsap.registerParameter(optForceX);
		jsap.registerParameter(optForceB);
		jsap.registerParameter(optForceE);
		jsap.registerParameter(optForceO);
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
	public void checkOptions(String[] args){
		config = jsap.parse(args);
		//verif que les option sont bien récupéré
		if (!config.success()) {
            
            System.err.println();
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
	public void Launch(){
		//System.out.println("launch");
		//recuperation des paramï¿½tres
		verbose = config.getBoolean("optV");
		oneTop = config.getBoolean("optO");
		endWith = config.getStringArray("optE");
		beginWith = config.getStringArray("optB");
		forbidden = config.getStringArray("optI");
		be = config.getStringArray("optX");
		forceOneTop = config.getBoolean("optForceO");
		forceBeginsWith = config.getStringArray("optForceB");
		forceEndsWith = config.getStringArray("optForceE");
		forceBe = config.getStringArray("optForceX");
		forceinterdit = config.getStringArray("optForceI");
		
		//si -d set on prend cette destination sinon le second paramétre
		destination = config.getString("optD");
		
		//gestion des argument solitaire de la ligne
		//si c'est le mode -3 c'est fichierjUnit source destination
		// si c'est un autre c'est source et destination
		if(getMode()!=3){
			if (config.getString("destination")==null)
				destination=config.getStringArray("param")[1];
			
			source = config.getStringArray("param")[0];
		}else{
			if (config.getString("destination")==null)
				destination=config.getStringArray("param")[2];
			
			source = config.getStringArray("param")[1];
			junitPath = config.getStringArray("param")[0];
		}
		mode = mode(config);
	}
	public String getJUnitPath(){
		return junitPath;
	}
	public boolean isVerbose() {
		return verbose;
	}

	public boolean isOneTop() {
		return oneTop;
	}

	public String[] getEndWith() {
		return endWith;
	}

	public String[] getBeginWith() {
		return beginWith;
	}

	public String[] getForbidden() {
		return forbidden;
	}

	public String[] getBe() {
		return be;
	}

	public String getDestination() {
		return destination;
	}
	
	public int getMode(){
		return mode;
	}
	
	public String getSource(){
		return source;
	}
	public boolean isForceOneTop() {
		return forceOneTop;
	}

	public String[] getForceBeginsWith() {
		return forceBeginsWith;
	}

	public String[] getForceEndsWith() {
		return forceEndsWith;
	}

	public String[] getForceBe() {
		return forceBe;
	}

	public String[] getForceinterdit() {
		return forceinterdit;
	}
	
	public String[] getParam(){
		return config.getStringArray("param");
	}
	
}