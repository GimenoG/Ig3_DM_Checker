package fr.umlv.junit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import Naze.test.ListsTest;

public class Junit {

	private static void execute(){

		JUnitCore runner = new JUnitCore();
		runner.addListener(new ExecuteJUnit());
		@SuppressWarnings("unused")
		Result result = runner.run(ListsTest.class); //nom de la classe où il y a les JUnit
	}

	
	public void execute(String pathSrc){
		
	}
	
	public static void main(String[] args) {
		execute();
	}
	 	
}
