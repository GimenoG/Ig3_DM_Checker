package fr.umlv.main;

import java.io.IOException;

import fr.umlv.util.Options;
import fr.umlv.util.Regex;
import fr.umlv.zip.ReadingArchive;

public class Scenario {
	private final Options options;
	private final ReadingArchive ra;
	
	
	public Scenario(Options opt){
		options=opt;
		ra = new ReadingArchive(Regex.changeURL(options.getSource()));
	}
	//TODO add tri Force
	private void scenario1() throws IOException{
		//set verbose
		ra.setVerbose(options.isVerbose());
		//check si un fichier existe
		for(String s : options.getBe()){
			if (!ra.checkFileExiste(s))
				System.out.println("Le fichier "+s+" n'existe pas");
			System.out.println(s+" existe");
		}
		//endwith
		for(String s : options.getEndWith()){
			if (!ra.checkFileEnds(s))
				System.out.println("EndsWith "+s+" NOK");
			System.out.println("EndsWith "+s+" OK");
		}
		
		//startwiths
		for(String s : options.getEndWith()){
			if (!ra.checkFileEnds(s))
				System.out.println("StartWith "+s+" NOK");
			System.out.println("StartWith "+s+" OK");
		}
		//onetop
		if(options.isOneTop()){
			if (ra.isFolderAtTop())
				System.out.println("ONE TOP OK");
			System.out.println("ONE TOP NOK");
		}
		//dezip
		ra.unzip(options.getSource());
		
	}
	
	public void start(){
		try{
			switch(options.getMode()){
				case 1 : scenario1();break;
				case 2 : System.out.println("todo scenario 2");break;
				case 3 : System.out.println("todo todo scenario 3");break;
				case 4 : System.out.println("toso scenario 4");break;
				default : System.err.println("Erreur : pas de scenario associe");
			}
		}catch(IOException e){
			//TODO on fait quoi quand ca plante
			System.out.println("haaaaaaaaaaaa");
		}
	}
}
