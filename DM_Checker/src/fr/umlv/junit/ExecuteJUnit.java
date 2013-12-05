package fr.umlv.junit;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import fr.umlv.util.Log;

public class ExecuteJUnit extends RunListener {
	StringBuilder sb;
	boolean fail = false;
	String msgFail = "";

	/**
	 * Called before any tests have been run.
	 */
	public void testRunStarted(Description description)
			throws java.lang.Exception {
		sb = new StringBuilder();
	}

	/**
	 * Called when all tests have finished
	 * */
	public void testRunFinished(Result result) throws java.lang.Exception {
		Log.writeText("/Users/Gui/Documents/Lambda/prout.txt", sb.toString());
	}

	/**
	 * Called when an atomic test is about to be started.
	 * */
	public void testStarted(Description description) throws java.lang.Exception {
		sb.append("<test name=\"" + description.getMethodName() + "\"");
	}

	/**
	 * Called when an atomic test has finished, whether the test succeeds or
	 * fails.
	 */
	public void testFinished(Description description)
			throws java.lang.Exception {
		if (!fail)
			sb.append(" result=True>\n");
		else {
			sb.append(" result=False>").append("\n" + msgFail + "\n");
			fail = false;
		}

	}

	/**
	 * Called when an atomic test fails.
	 * */
	public void testFailure(Failure failure) throws java.lang.Exception {
		fail = true;
		msgFail = failure.getMessage();
	}

	/**
	 * Called when a test will not be run, generally because a test method is
	 * annotated with Ignore.
	 * 
	 * public void testIgnored(Description description) throws
	 * java.lang.Exception {
	 * System.out.println("Execution of test case ignored : " +
	 * description.getMethodName()); }
	 */
}
