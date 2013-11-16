package fr.umlv.util;

import java.io.FileWriter;
import java.io.IOException;

public class Log {

	
	public void writeText(String path,String text) throws IOException{
		FileWriter writer = null;
		try{
		     writer = new FileWriter(path, true);
		     writer.write(text,0,text.length());
		}catch(IOException ex){
		    ex.printStackTrace();
		}finally{
		  if(writer != null){
		     writer.close();
		  }
		}
	}
}
