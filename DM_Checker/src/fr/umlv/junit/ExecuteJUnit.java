package fr.umlv.junit;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import fr.umlv.util.Log;

/**
 * This class overrides the class RunListener
 * 
 * @author Gimeno and Bourgain
 * 
 */
public class ExecuteJUnit extends RunListener {
	StringBuilder sb;
	boolean fail = false;
	String msgFail = "";
	String pathToWrite;

	public ExecuteJUnit(String pathToWrite) {
		this.pathToWrite = pathToWrite;
	}

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
		sb.deleteCharAt(sb.length() - 1);
		Log.writeText(pathToWrite, sb.toString());
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
		sb.append("</test>\n");

	}

	/**
	 * Called when an atomic test fails.
	 * */
	public void testFailure(Failure failure) throws java.lang.Exception {
		fail = true;
		msgFail = failure.getMessage();
	}

}
