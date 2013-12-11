package fr.umlv.zip;

import java.util.ArrayList;

public abstract class ArchiveRunner {

	private String source;
	private String destination;
	
	public ArchiveRunner(String source, String destination) {
		this.source=source;
		this.destination=destination;
	}
	
	public abstract void extract();
	
	public abstract void extract(String pathAExtraire);
	
	public abstract void setPath();
	
	public abstract ArrayList<String> getPath();
}
