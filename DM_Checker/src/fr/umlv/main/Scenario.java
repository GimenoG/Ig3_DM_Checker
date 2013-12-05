package fr.umlv.main;

import java.io.IOException;

import fr.umlv.util.Options;
import fr.umlv.zip.ReadingArchive;

public class Scenario {
	private final Options options;
	private final ReadingArchive ra;
	
	
	public Scenario(Options opt){
		options=opt;
		ra = new ReadingArchive(options.getDestination());
	}
	
	private void scenario1() throws IOException{
		//set verbose
		ra.setVerbose(options.isVerbose());
		//check si un fichier existe
		for(String s : options.getBe()){
			ra.checkFileExiste(options.getDestination(), s);
		}
	}
	
	public void start(){
		try{
			switch(options.getMode()){
				case 1 : scenario1();break;
				case 2 : System.out.println("scenario 2");break;
				case 3 : System.out.println("scenario 3");break;
				case 4 : System.out.println("scenario 4");break;
				default : System.err.println("Erreur argurment du mode d'action");
			}
		}catch(IOException e){
			//TODO on fait quoi quand ca plante
		}
	}
}
