package fr.umlv.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//TODO test unitaire

public class TxtExploreur {
	private TxtExploreur(){}
	/**
	 * parsh the txt of the critérion
	 * 
	 * @param pathFile
	 * @return
	 */
	public static ArrayList<String> getCriterions(String pathFile){
		ArrayList<String> criterions= new ArrayList<String>();
		try{
			InputStream ips=new FileInputStream(pathFile); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String criterion="";
			String [] tmp;
			while ((ligne=br.readLine())!=null){
				tmp= ligne.split(":");
				//mise en forme du critére
				for(int i=1 ; i<tmp.length; i++){
					criterion = criterion+tmp[i]+"\n";
				}
				//ajout dans la map
				criterions.add(criterion);
				criterion ="";
			}
			br.close(); 
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		return criterions;
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
			System.out.println(e.toString());
		}
		//TODO c'est moche
		return null;
	}
	/**
	 * save the report or rewrite il he existe
	 * 
	 * @param report
	 */
	public static void saveReport(String reportPath, String[] report){
		String r="";
		for (String s : report){
			r=r+s+"|";
		}
		r=r.substring(0, r.length()-1);
		
			//TODO pas très propre ....
			try{
				InputStream ips=new FileInputStream(reportPath); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				FileWriter fw = new FileWriter (reportPath);
				BufferedWriter bw = new BufferedWriter (fw);
				String ligne;
				while ((ligne=br.readLine())!=null){
					 
					if (ligne.split("|")[0].compareTo(report[0])==0){
						bw.write(r);
						bw.flush();
						bw.close();
					}
					else{
						bw.write(ligne);
						bw.flush();
						bw.close();
					}
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
		
		
	}
}
