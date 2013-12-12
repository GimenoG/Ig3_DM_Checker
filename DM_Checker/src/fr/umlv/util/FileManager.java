package fr.umlv.util;


/**
 * manage the input & output betwen the file and the soft
 * 
 * @author Meriadoc
 *
 */
public class FileManager {
	
	public final String pathReport;
	
	public FileManager(String pathReport){
		this.pathReport = pathReport;
		//http://www.infoq.com/fr/news/2013/05/standard-java-api-for-json
	}
	
	public String readReport(String name){
		return "le rapport d'un binome contenue dans le fichier, prevoire valeur d'erreur si il n'existe pas";
	}
	
	public String readReports(){
		return "tout les rapport";
	}
	
	public void setReport(String report){
		//ajoute le rapport d'un binome dans le fichier ou le modifie si il existe
	}
	
	public boolean isReport(String name){
		//verifie si le rapport d'un binome est ecrit dans le fichier
		return false;
	}
}
