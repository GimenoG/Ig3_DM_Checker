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
	 * check if a report of a binome exist. A empty string mean that there is no report
	 * report structure names|note|comment|note|comment
	 * 
	 * 
	 * @param reportPath
	 * @param names
	 * @return
	 */
	public static String [] getReport(String reportPath, String names){
		try{
			InputStream ips=new FileInputStream(reportPath); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String [] ligne;
			while ((ligne=br.readLine().split("|"))!=null){
				 
				if (ligne[0].compareTo("names")==0){
					return ligne;
				}
			}
			br.close(); 
		}		
		catch (Exception e){
			System.err.println("Error on the file "+reportPath+" in the fonction getReport");
		}
		//TODO c'est moche
		return null;
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
	
	public void saveReport(String path, String msg, String id){
		
		try {
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String [] ligne;
			int i=0;
			while ((ligne=br.readLine().split(":"))!=null){
				if(ligne[0].compareTo(id)==0)
					Log.writeText(path	,  msg);
				//else
					//Log.writeText(path, line.t);
				
			}
			br.close(); 
		} catch (IOException e) {
			System.err.println("Ecriture impossible dans "+path+File.separator+"nots.txt");
		}
	}
}
