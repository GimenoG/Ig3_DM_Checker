package fr.umlv.junit;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.Runner;

import Naze.test.ListsTest;

public class Junit {

	public static void execute(){
//		Result result = JUnitCore.runClasses(AdditionTest.class);
//	    for (Failure failure : result.getFailures()) {
//	      System.out.println(failure.toString());
//	    }
		JUnitCore runner = new JUnitCore();
		runner.addListener(new TextListener(System.out));
		Result result = runner.run(ListsTest.class);	

		boolean wasSuccessful = result.wasSuccessful();
		System.out.println("All tests were successful: " + wasSuccessful);}

	public static void main(String[] args) {
		execute();
	}
	 

	 /* public static void main(String[] args) {
	    JUnitCore runner = new JUnitCore();
	    runner.addListener(new TextListener(System.out));
	    runner.run(MaClasseDeTest.class);
	  }*/
	
	
}
