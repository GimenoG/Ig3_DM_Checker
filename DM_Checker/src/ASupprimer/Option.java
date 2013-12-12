package ASupprimer;

import java.io.IOException;

public interface Option {

	public boolean endsWith(String s);

	public boolean existe(String s) throws IOException;

	public boolean interdit(String s) throws IOException;

	public boolean beginsWith(String s);
	
	public void setVerbose(boolean b);
	
	public boolean oneTop();
}
