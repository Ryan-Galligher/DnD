/**
 * 
 */
package externDataSet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * This is a container class for all of the external table types.  The way to load the
 * information is to make a call to {@code tableFactory()} with a File object and it will
 * invoke the appropriate constructor and get an instance of the sub-class that implements
 * the type of file we're using (e.g., CSV, XLS, XLSX).
 * 
 * @author Gordon Galligher
 *
 */
public abstract class ExternTable {

	public enum ColTypes {
		INTEGER, DOUBLE, STRING
	};	

	protected static File inFile      = null;
	protected String[] columns = null;
	protected ColTypes[] types = null;

	/*
	 * First the abstract methods and then the concrete ones that we have.
	 */
	
	/**
	 * Factory method to create an appropriate ExternTable type (sub-class) based on the
	 * type of file that is passed as the argument (based on extension).
	 * 
	 * @param file		The file to read and parse / write out
	 * 
	 * @return
	 */
	public static ExternTable tableFactory(File file) {
		Pattern regexXLS = Pattern.compile("^.*.xls.*");
		ExternTable retVal = null;
		inFile = file;
		
		if ( regexXLS.matcher(file.getName()).matches() ) {
			retVal = new ExcelTable(file);
		}
		return retVal;
	}

	/**
	 * Set the data types of each of the columns in the table.
	 * 
	 * @param columns		The string array of each column's name
	 * @param types			The array of each column's type (e.g., STRING, NUMBER) 
	 */
	public void setDataTypes(String[] columns, ColTypes[] types) {
		this.columns = columns;
		this.types   = types;
	}
	
	/**
	 * Open the database table for reading.
	 * 
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	abstract public void openDB() throws EncryptedDocumentException, InvalidFormatException, IOException;
	
	/**
	 * Open the database table, specify it should be written to by passing {@code true}.
	 * 
	 * @param toWrite
	 * 		Whether we should open/create the file to write or not.
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	abstract public void openDB(boolean toWrite) throws EncryptedDocumentException, InvalidFormatException, IOException;
	
	/**
	 * Get the next row from the table, {@code null} when table is empty.
	 * 
	 * @return
	 */
	abstract public HashMap<String, Object> nextRow();

	/**
	 * Write the row out to the table opened via {@code openDB(true)}.
	 * 
	 * @param row
	 * 		The HashMap of all of the entries in the table for that row
	 */
	abstract public void writeNextRow(HashMap<String, Object> row);
	
	abstract public void closeDB() throws IOException;
}
