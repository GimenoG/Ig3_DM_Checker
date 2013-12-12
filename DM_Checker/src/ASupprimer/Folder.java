package ASupprimer;

import java.io.File;
import java.util.ArrayList;

public class Folder implements Option {

	private String path;
	private boolean verbose = false;

	public Folder(String path) {
		this.path = path;
	}

	@Override
	public boolean endsWith(String ends) {
		ArrayList<Boolean> result = new ArrayList<>();
		result = endsWith(path, ends,result);
		return result.contains(Boolean.TRUE);
	}

	private ArrayList<Boolean> endsWith(String path, String ends, ArrayList<Boolean> result) {
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				result.add(files[i].getName().endsWith(ends));
				if (files[i].isDirectory()) {
					endsWith(files[i].getAbsolutePath(), ends,result);
				}
			}
		}
		return result;
	}

	@Override
	public boolean existe(String regex) {
		ArrayList<Boolean> result = new ArrayList<>();
		result = existe(path, regex,result);
		return result.contains(Boolean.TRUE);
	}
	
	private ArrayList<Boolean> existe(String path, String regex, ArrayList<Boolean> result) {
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				result.add(files[i].getName().matches(regex));
				if (files[i].isDirectory()) {
					endsWith(files[i].getAbsolutePath(), regex,result);
				}
			}
		}
		return result;
	}

	@Override
	public boolean interdit(String regex) {
		return existe(regex);
	}

	@Override
	public boolean beginsWith(String start) {
		ArrayList<Boolean> result = new ArrayList<>();
		result = beginsWith(path, start,result);
		return result.contains(Boolean.TRUE);
	}
	
	private ArrayList<Boolean> beginsWith(String path, String start, ArrayList<Boolean> result) {
		File file = new File(path);
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				result.add(files[i].getName().startsWith(start));
				if (files[i].isDirectory()) {
					endsWith(files[i].getAbsolutePath(), start,result);
				}
			}
		}
		return result;
	}


	@Override
	public void setVerbose(boolean b) {
		this.verbose = b;
	}

}
