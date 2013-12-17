package fr.umlv.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.umlv.IHM.IHM;
import fr.umlv.archive.ArchiveOptionChecker;
import fr.umlv.archive.ZipFileFormat;
import fr.umlv.junit.Junit;
import fr.umlv.util.Messages;
import fr.umlv.util.Options;
import fr.umlv.util.Reports;
/**
 * 
 * 
 * 
 * 
 * @author Meriadoc
 *
 */
public class Scenario {
	
	private final Options options;
	private final ArchiveOptionChecker optCheck;
	private Junit junit;
	
	public Scenario(Options opt, Reports reports){
		options=opt;
		optCheck = new ZipFileFormat();
		junit = new Junit();
	}
	
	
	//echec option force : pas extratiction et archive refusé
		private boolean optionForceRefused(String option, String param){
			System.err.println(Messages.getOutputString(option, param));
			return false;
		}
		private void optionRefused(String option, String param){
			System.out.println(Messages.getOutputString(option, param));
		}
	
	
	//On ne dezip pas sur l'option -1
	/**
	 * Check les options sur une archive return booleen pour savoir si on dezip
	 * methode privé on ne fait pas de javadoc
	 * 
	 * @param path
	 */
	public boolean checkOptionsArchive(String path){
		//test one top
		if(options.isOneTop()||options.isForceOneTop()){
			if (!optCheck.oneTop(path))
				if (options.isForceOneTop()){
					return optionForceRefused("onetop", "");
					//sortie car option refusé
				}
				else{
					optionRefused("onetop", "");
				}
		}
		//check opt I et x
		try {
			for(String s : options.getForbidden()){
				if (optCheck.existe(path, s))
					optionRefused("i", s);
			}
			
			for(String s : options.getForceinterdit()){
				if (optCheck.existe(path,s))
					return optionForceRefused("i", s);
			}
			
			for(String s : options.getBe()){
				if (!optCheck.existe(path,s))
					optionRefused("i", s);
			}
			
			for(String s : options.getForceBe()){
				if (!optCheck.existe(path,s))
					return optionForceRefused("i", s);
			}
		} catch (IOException e) {
			System.err.println("arret innatendu du programme");
			e.printStackTrace();
		}
		//endswith
		for(String s : options.getEndWith()){
			if (!optCheck.endsWith(path, s)){
				optionRefused("e",s );
			}
		}
		
		for(String s : options.getForceEndsWith()){
			if (!optCheck.endsWith(path, s)){
				return optionForceRefused("e",s );
			}
		}
		
		
		//startwiths
		for(String s : options.getBeginWith()){
			if (!optCheck.beginsWith(path, s))
				optionRefused("b",s );
		}
		
		for(String s : options.getForceBeginsWith()){
			if (!optCheck.beginsWith(path, s)){
				return optionForceRefused("b",s );
			}
		}
		if (options.isVerbose()){
			System.out.println(path+" OK");
		}
		return true;
	}
	
	public void checkArchiveSerial(String path){
		//init : extrait l'archive d'archive
		ArrayList<String> paths;
		ArrayList<String> tmp = new ArrayList<>();
		Objects.requireNonNull(paths = optCheck.getPathArchive(path));
		if(paths.size()==0){
			System.err.println("pas d'archive d'archive !");
			return;
		}
		for(int i = 0; i<paths.size(); i++){
			tmp.add(paths.get(i).replace("/", File.separator));
			if(options.isVerbose()){
				System.out.println(tmp.get(i));
			}
		}
		optCheck.extract(path, options.getDestination());
		//paths.remove(0);
		//lance le traitement
		for(String p : tmp){
			if((checkOptionsArchive(options.getSource()+File.separator+p))&&(optCheck.isValid(p))){
				//TODO verif les path
				optCheck.extract(options.getSource()+File.separator+p, options.getDestination());
			}
		}
	}
	
	public ArrayList<String> initIHM(){
		String path=options.getSource();
		ArrayList<String> p = optCheck.getPathArchive(path);
		
		//verif que la liste ne soit pas vide.
		if (p.size()>0){
			optCheck.extract(path, p.get(0));
			p.remove(0);
			return p;
		}
		else{
			//pas top
			return null;
		}
	}
	
	public void jUnitTesting(String path){
		//on extrait les fichier valide
		checkArchiveSerial(path);
		//on lance les jUnits
		//TODO parametre ?
		try {
			junit.execute(options.getJUnitPath(), options.getSource(), /*cf  ?*/ path , true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private boolean ihmMode(){
		
		String[] param = options.getParam();
		//verfie qu'il y a bien 3 paramétres passé en option.
		System.out.println(param.length);
		for(String s : param)
			System.out.println(s);
		if(param.length!=3)
			return false;
		//lancement de l'ihm, puissance maximum M. Solo
		System.out.println("toto");
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame fenetre = new IHM(param,optCheck.getPathArchive(options.getSource()));
				fenetre.setVisible(true);
			}
		});
		return true;
	}
	/**
	 * suprimmer les zip a la fin !!
	 */
	public void start(){
		optCheck.setVerbose(options.isVerbose());
		//check si il y a bien une source
		if (options.getSource().compareTo("")==0){
			System.err.println("Dossier source invalide");
			return;
		}
		//System.out.println(options.getMode());
		//lance un scénation
		switch(options.getMode()){
			case 1 : checkOptionsArchive(options.getSource());break;
			case 2 : checkArchiveSerial(options.getSource());break;
			case 3 : jUnitTesting(options.getSource());break;
			case 4 : ihmMode();break;
			default : System.err.println("Erreur : pas de scenario associe");
		}
	}
}
