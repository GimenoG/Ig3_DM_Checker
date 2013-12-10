package fr.umlv.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.umlv.IHM.IHM;
import fr.umlv.util.Options;
import fr.umlv.util.Regex;
import fr.umlv.zip.Option;
import fr.umlv.zip.ZipFileFormat;

public class Scenario {
	private final Options options;
	private Option opt;
	
	
	public Scenario(Options opt){
		options=opt;
	}
	//TODO add tri Force
	//On ne dezip pas sur l'option -1
	private void scenario1() throws IOException{
		//set verbose
		opt = new ZipFileFormat(options.getSource());
		opt.setVerbose(options.isVerbose());
		//check si un fichier existe
		for(String s : options.getBe()){
			if (!opt.existe(s))
				System.out.println("Le fichier "+s+" n'existe pas");
			System.out.println(s+" existe");
		}
		//endwith
		for(String s : options.getEndWith()){
			if (!opt.endsWith(s))
				System.out.println("EndsWith "+s+" NOK");
			System.out.println("EndsWith "+s+" OK");
		}
		
		//startwiths
		for(String s : options.getEndWith()){
			if (!opt.beginsWith(s))
				System.out.println("StartWith "+s+" NOK");
			System.out.println("StartWith "+s+" OK");
		}
		//onetop
		if(options.isOneTop()){
			//TODO c'est moche
			if (new ZipFileFormat(options.getSource()).isFolderAtTop())
				System.out.println("ONE TOP OK");
			System.out.println("ONE TOP NOK");
		}
	}
		
	private boolean scenario2() throws IOException{
		//recup des dossier du projet
		opt = new ZipFileFormat(options.getSource());
		ArrayList<String> paths = opt.;
		for (String s : paths){
			//check les options
			scenario1();
			//TODO path de destination :o
			ra.unzip(s);
		}
		return false;
	}
	
	private boolean scenario4(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame fenetre = new IHM(options, ra);
				fenetre.setVisible(true);
			}
		});
		return false;
	}
	public void start(){
		try{
			switch(options.getMode()){
				case 1 : scenario1();break;
				case 2 : scenario2();break;
				case 3 : System.out.println("todo todo scenario 3");break;
				case 4 : scenario4();break;
				default : System.err.println("Erreur : pas de scenario associe");
			}
		}catch(IOException e){
			//TODO on fait quoi quand ca plante
			System.out.println("haaaaaaaaaaaa");
		}
	}
}
