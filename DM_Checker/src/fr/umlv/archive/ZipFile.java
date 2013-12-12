package fr.umlv.archive;

import java.io.IOException;

public class ZipFile implements ArchiveOptionChecker{

	@Override
	public boolean endsWith(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(String s) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interdit(String s) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean beginsWith(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVerbose(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean oneTop() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void extract() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extract(String destination) {
		// TODO Auto-generated method stub
		
	}

}
