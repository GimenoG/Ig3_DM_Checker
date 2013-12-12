package fr.umlv.archive;

import java.io.IOException;
import java.util.ArrayList;

public interface ArchiveOptionChecker {

	public boolean endsWith(String src, String ends);

	public boolean existe(String src, String existe) throws IOException;

	public boolean beginsWith(String src, String begin);

	public void setVerbose(boolean b);

	public boolean oneTop(String src);

	public void extract(String src, String destination); // destination/src !!!!
															// si vide ./src

	public ArrayList<String> getPathArchive(String src);

	public String isValid(String src);

	public void isForce(boolean b);

}
