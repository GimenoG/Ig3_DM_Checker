package fr.umlv.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//TODO test unitaire

public class TxtExploreur {
	private TxtExploreur(){}
	/**
	 * parsh the txt of the crit�rion
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
}
