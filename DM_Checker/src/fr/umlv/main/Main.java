package fr.umlv.main;

import com.martiansoftware.jsap.JSAPException;

import fr.umlv.util.Options;
/**
 * Programme entry
 * 
 * @author Gimeno & Bourgain
 * 
 */
public class Main {

	public static void main(String[] args) throws JSAPException {
		Options opt = new Options();
		Scenario sc = new Scenario(opt);
		opt.createOptions();
		opt.checkOptions(args);
		opt.Launch();
		sc.start();
	}
}