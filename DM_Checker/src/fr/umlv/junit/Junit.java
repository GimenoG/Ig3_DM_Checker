package fr.umlv.junit;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import Naze.test.ListsTest;

public class Junit {

	public static void execute(){

		JUnitCore runner = new JUnitCore();
		runner.addListener(new ExecuteJUnit());
		@SuppressWarnings("unused")
		Result result = runner.run(ListsTest.class);
	}

	public static void main(String[] args) {
		execute();
	}
	 	
}
