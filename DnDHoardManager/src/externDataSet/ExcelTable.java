/**
 * 
 */
package externDataSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Gordon Galligher
 *
 */
public class ExcelTable extends ExternTable {
	
	private File xlFile = null;
	private Workbook wkBook = null;
	private Sheet wkSheet = null;
	private HashMap<String, Integer> colDataMap = new HashMap<String, Integer>();
	private int sheetRow = -1;
	private int sheetFirstRow = -1;
	private int sheetLastRow = -1;
	private boolean writing = false;

	/**
	 * Constructor for the Excel Table, just stuffs away the file type to use later
	 * 
	 * @param file	The file to be worked on
	 */
	protected ExcelTable(File file) {
		xlFile = file;
	}
	
	/* (non-Javadoc)
	 * @see externDataSet.ExternTable#openDB(java.io.File)
	 */
	@Override
	public void openDB() throws EncryptedDocumentException, InvalidFormatException, IOException {
		wkBook = WorkbookFactory.create(xlFile);
		wkSheet = wkBook.getSheetAt(0);
		initColumns();
		sheetRow = -1;
		sheetFirstRow = wkSheet.getFirstRowNum();
		sheetLastRow  = wkSheet.getLastRowNum();
		writing = false;
	}

	/**
	 * Open the database table to write out and then pick the type of workbook to create
	 * based on the file name we were passed in the constructor.  If the {@code toWrite}
	 * parameter is false (not writing), then just call the {@code openDB()} method without
	 * arguments as it will set things up for reading.
	 */
	@Override
	public void openDB(boolean toWrite) throws EncryptedDocumentException, InvalidFormatException, IOException {
		if ( ! toWrite )
			openDB();
		writing = true;
		sheetRow = -1;
		Pattern regexXLS = Pattern.compile("^.*.xls$");

		if ( regexXLS.matcher(inFile.getName()).matches() ) wkBook = new HSSFWorkbook();
		else wkBook = new XSSFWorkbook();
	}

	/* (non-Javadoc)
	 * @see externDataSet.ExternTable#nextRow()
	 */
	@Override
	public HashMap<String, Object> nextRow() {
		initColumns();
		Cell cellValue = null;
		HashMap<String, Object> rowData = new HashMap<String, Object>();
		
		if ( sheetRow == -1 )
			sheetRow = sheetFirstRow;
		if ( sheetRow == 0 && !colDataMap.isEmpty() )	// Have a data map, so skip first row
			sheetRow++;
		if ( sheetRow > sheetLastRow )
			return null;
		Row row = wkSheet.getRow(sheetRow++);

		for (int cellno = row.getFirstCellNum(); cellno <= row.getLastCellNum(); cellno++) {
			String colStr = null;
			cellValue = row.getCell(cellno, MissingCellPolicy.RETURN_NULL_AND_BLANK);
			if ( !colDataMap.isEmpty() && !colDataMap.containsValue(cellno) )
				continue;
			for (String key : colDataMap.keySet() ) {
				if ( cellno == colDataMap.get(key) )
					colStr = key;
			}
			int pos = -1;
			for (int i = 0; i < columns.length; i++)
				if ( columns[i] == colStr )
					pos = i;
			if ( pos > -1 && types[pos] == ColTypes.STRING )
				rowData.put(columns[pos], cellValue.getStringCellValue());
			else if ( pos > -1 ) {
				double val;
				try {
					val = cellValue.getNumericCellValue();
				} catch (Exception e) {
					val = -1;
				}
				if ( types[pos] == ColTypes.INTEGER )
					rowData.put(colStr, (int) Math.round(val));
				else
					rowData.put(colStr,  val);
			}
		}
		return rowData;
	}

	/* (non-Javadoc)
	 * @see externDataSet.ExternTable#closeDB()
	 */
	@Override
	public void closeDB() throws IOException {
		if ( ! writing )
			wkBook.close();
		else {
			FileOutputStream out = new FileOutputStream(this.inFile);
			wkBook.write(out);
			out.close();
			wkBook.close();
		}
	}


	/**
	 * Writes out the next row to the Workbook/Worksheet entry and adjusts all the counters
	 * for the current row and the next row.
	 */
	@Override
	public void writeNextRow(HashMap<String, Object> row) {
		if ( sheetRow == -1 ) {
			wkSheet = wkBook.createSheet();
			Row headerRow = wkSheet.createRow(0);
			sheetRow = 0;
			if ( !colDataMap.isEmpty() ) {
				for (int i = 0; i < columns.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(columns[i]);
				}
			}
		}
		Row newRow = wkSheet.createRow(++sheetRow);
		int cell = 0;
		for (String col : row.keySet() )
			newRow.createCell(cell++).setCellValue(row.get(col).toString());
	}

	/**
	 * Initialize the columns map {@code colDataMap} so it has the right values when
	 * we go to read it.
	 */
	private void initColumns() {
		if ( colDataMap.size() > 0 && columns.length > 0 ) {
			for (int i = 0; i < columns.length; i++) {
				colDataMap.put(columns[i], findColumn(wkSheet, columns[i]));
			}
		}
	}
	
	/**
	 * Find the specific position of the column containing the header label we want.
	 * 
	 * @param sheet
	 * 		The sheet to look in
	 * @param colSearch
	 * 		The string of the column we're looking for (-1 if not found)
	 * @return
	 * 		The column number where the column is found
	 */
	private int findColumn(Sheet sheet, String colSearch) {
		int column = -1;	
		
		Row row0 = sheet.getRow(0);
		for ( Cell cell : row0 ) {
			if ( cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().equals(colSearch) ) {
				column = cell.getColumnIndex();
				break;
			}
		}
		return column;
	}
}
