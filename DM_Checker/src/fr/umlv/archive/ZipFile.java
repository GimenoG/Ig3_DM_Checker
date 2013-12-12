package fr.umlv.archive;

import java.io.IOException;
import java.util.ArrayList;

public class ZipFile implements ArchiveOptionChecker{

	@Override
	public boolean endsWith(String src, String ends) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(String src, String existe) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean beginsWith(String src, String begin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVerbose(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean oneTop(String src) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void extract(String src, String destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getPathArchive(String src) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid(String src) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
