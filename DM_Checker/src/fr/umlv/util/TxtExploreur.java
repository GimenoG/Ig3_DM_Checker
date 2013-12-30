package fr.umlv.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//TODO test unitaire

public class TxtExploreur {
	private TxtExploreur(){}
	/**
	 * parsh the txt of the critérion
	 * 
	 * @param pathFile
	 * @return
	 */
	public static List<String []> getCriterions(String pathFile){
		ArrayList<String []> criterions= new ArrayList<>();
		try{
			InputStream ips=new FileInputStream(pathFile); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String [] tmp;
			while ((ligne=br.readLine())!=null){
				tmp= ligne.split(":");
				for(int i = 1; i<tmp.length;i++){
					tmp[1]=" "+tmp[1]+"\n";
				}
				criterions.add(tmp);
			}
			br.close(); 
		}
		catch (Exception e){
			System.err.println("Error on the file "+pathFile+" in the fonction getCriterions");
		}
		return criterions;
	}
	
	public static List<String []> getDataFile(String pathFile){
		ArrayList<String []> data = new ArrayList<>();
		try{
			InputStream ips=new FileInputStream(pathFile); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String [] tmp;
			while ((ligne=br.readLine())!=null){
				tmp= ligne.split(":");
				data.add(tmp);
			}
			br.close(); 
		}
		catch (Exception e){
			System.err.println("Error on the file "+pathFile+" in the fonction getDataFile");
		}
		return data;
	}
	/**
	 * return all the student number which are stored in the output path in parameter
	 */
	public static List<String> getReportStored(String reportPath){
		ArrayList<String> numberStudents= new ArrayList<>();
		try{
			InputStream ips=new FileInputStream(reportPath); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String [] ligne;
			int i=0;
			while ((ligne=br.readLine().split(":"))!=null){
				 numberStudents.add(ligne[0]);
				 i++;
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.err.println("Error on the file "+reportPath+" in the fonction getReportStored");
		}
		return numberStudents;
	}
	/**
	 * 
	 * Take an id and return the report if he existe, else it's return null
	 * 
	 * @param id
	 * @return
	 */
	//TODO test on work
	public static List<String> getReport(String id, String pathNots){
		ArrayList<String> report = new ArrayList<>();
		try{
			InputStream ips=new FileInputStream(pathNots); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String [] ligne;
			while ((ligne=br.readLine().split(":"))!=null){
				//split la ligne poiur recup l'id
				if(ligne[0].compareTo(id)==0){
					//recup le report
					for(int i=2;i<ligne.length;i++){
						report.add(ligne[i]);
					}
					br.close();
					return report;
				}
				
			}
			br.close(); 
		}catch(IOException ioe){
			System.err.println("Lecture impossible dans "+pathNots+File.separator+"nots.txt");
		}
		return null;
	}
	
	public void saveReport(String path, String msg, String id){
		//TODO check msg
		try {
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String line;
			int i=0;
			while ((line=br.readLine())!=null){
				//Si la ligne existe deja on la remplace par elle meme sinon on recopie la ligne
				if(line.split(":")[0].compareTo(id)==0){
					Log.writeText(path	,  msg);
					return;
				}
				else{
					Log.writeText(path, line);
				}
				
			}
			br.close(); 
		} catch (IOException e) {
			System.err.println("Ecriture impossible dans "+path+File.separator+"nots.txt");
		}
	}
}
