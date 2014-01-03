package fr.umlv.archive;

import java.util.ArrayList;

/**
 * This interface is for archive.
 * 
 * @author Gimeno and Bourgain
 * 
 */

public interface ArchiveOptionChecker {

	/**
	 * This method check if there is a file or directory that ends with ends
	 * 
	 * @param src
	 *            path
	 * @param ends
	 *            suffix
	 * @return True is there is a file or directory that ends with ends
	 *         otherwise false
	 */
	public boolean endsWith(String src, String ends);

	/**
	 * This method check if there is a file or directory that is named existe
	 * 
	 * @param src
	 *            path
	 * @param existe
	 *            existe
	 * @return True is there is a file or directory that is named existe
	 *         otherwise false
	 */
	public boolean existe(String src, String existe);

	/**
	 * This method check if there is a file or directory that start with prefix
	 * 
	 * @param src
	 *            path
	 * @param begin
	 *            prefix
	 * @return true is there is a file or directory that ends with ends
	 *         otherwise false
	 */
	public boolean beginsWith(String src, String begin);

	/**
	 * This method enables or disables the verbose mode
	 * 
	 * @param b
	 *            true or false
	 */
	public void setVerbose(boolean b);

	/**
	 * This method check if there is a single file to the root of the archive
	 * 
	 * @param src
	 *            path
	 * @return true is there is a single file
	 */
	public boolean oneTop(String src);

	/**
	 * This method will extract an archive
	 * 
	 * @param src
	 *            path
	 * @param destination
	 *            destination
	 * @return The destination path
	 */
	public String extract(String src, String destination);

	/**
	 * This method watch an archive if there are other archive
	 * 
	 * @param src
	 *            path
	 * @return an array containing the names of the archives
	 */
	public ArrayList<String> getPathArchive(String src);

	/**
	 * see if the file passed as a parameter is an archive
	 * 
	 * @param src
	 *            path
	 * @return true if the file is an archive
	 */
	public boolean isValid(String src);

}
