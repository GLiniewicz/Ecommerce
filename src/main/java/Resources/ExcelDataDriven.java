package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {

			
	public ArrayList<String> getData(String sheetName, String testcaseName) throws IOException {
			
			ArrayList<String> a = new ArrayList<String>();

			FileInputStream fis = new FileInputStream(".\\src\\main\\java\\Resources\\TestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			int sheets = workbook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) { // get desired sheet name
					XSSFSheet sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row firstrow = rows.next();
					Iterator<Cell> ce = firstrow.cellIterator();
					int k = 0;
					int coloumn = 0;
					while (ce.hasNext()) {
						Cell value = ce.next();

						if (value.getStringCellValue().equalsIgnoreCase("TestCases")) { // get desired column
							coloumn = k;

						}

						k++;
					}
					
					while (rows.hasNext()) {

						Row r = rows.next();

						if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) { // get desired row of values for specific test case

						Iterator<Cell> cv = r.cellIterator();
							while (cv.hasNext()) {
								Cell c = cv.next();
								if (c.getCellType() == CellType.STRING) {

									a.add(c.getStringCellValue());
								} else {

									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

								}
							}
						}

					}
				}
			}
			workbook.close();
			return a;

		}

	}

