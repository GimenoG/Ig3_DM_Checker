package fr.umlv.util;


/**
 * 
 * this class is able to manage a raport generate
 * @author Meriadoc
 *
 */
public class Reports {
	
	private final FileManager file;
	
	public Reports(FileManager file){
		this.file=file;
	}
	
	public void setReport(String s){
		//ajoute un rapport (binome + note + commentaire
	}
	
	public String getReport(String name){
		return "le rapport du binome si il exite chaine vide sinon";
	}
	
	public String getReports(){
		return "le string de tout les rapports, chaine vide si aucun rapport";
	}
}
