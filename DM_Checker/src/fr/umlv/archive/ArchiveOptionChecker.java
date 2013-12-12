package fr.umlv.archive;

import java.io.IOException;
import java.util.ArrayList;

public interface ArchiveOptionChecker {

	public boolean endsWith(String s);

	public boolean existe(String s) throws IOException;

	public boolean interdit(String s) throws IOException;

	public boolean beginsWith(String s);

	public void setVerbose(boolean b);

	public boolean oneTop();

	public void extract(String src,String destination); //destination/src !!!! si vide ./src
	
	public ArrayList<String> getPathArchive(String src);
}
