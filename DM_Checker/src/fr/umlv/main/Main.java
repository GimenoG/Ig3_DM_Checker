package fr.umlv.main;

import com.martiansoftware.jsap.JSAPException;

import fr.umlv.util.Options;
import fr.umlv.util.Reports;

public class Main {

	public static void main(String[] args) throws JSAPException {
		Options opt = new Options();
		Reports report = new Reports("path ou on enregistre le rapport ?");
		Scenario sc = new Scenario(opt, report);
		opt.createOptions();
		opt.checkOptions(args);
		opt.Launch();
		sc.start();
	}
}