package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	public static XSSFSheet getSheet(String sheetName, String dataFilePath) {
		try {

			FileInputStream fileData = new FileInputStream(new File(System.getProperty("user.dir") + dataFilePath));
			XSSFWorkbook wb = new XSSFWorkbook(fileData);
			XSSFSheet sht;
			//Sheet sheet variable declare
            for(Sheet sheet: wb) {
                System.out.println("=> " + sheet.getSheetName());
                if (sheet.getSheetName().equals(sheetName)) {
                    sht = wb.getSheet(sheet.getSheetName());
                    System.out.println(sht.getLastRowNum());
                    return sht;
                }
            }
            return null;

		} catch (Exception e) {
			return null;
		}
	}

	public static String[][] getData(String sheetName, int rowNum, int colNum, String dataFilePath) {
		XSSFSheet sheet = getSheet(sheetName, dataFilePath);
		// String[][] data = new String[2][2]

		String[][] data = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getLastRowNum());
		System.out.println(sheet.getRow(0).getLastCellNum());
		for (int i = rowNum; i < sheet.getLastRowNum(); i++) {
			for (int j = colNum; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}

		return data;
	}

	public static String[][] primaryKeyGetData(String primaryKey, String sheetName, String filePath) {

		XSSFSheet sheet = getSheet(sheetName, filePath);
		// String[][] = new String[sheet.get]

		// creating 2D array object of dynamic size
		//data = [[1, 2, 4]]
		String[][] data = new String[1][sheet.getRow(0).getLastCellNum()];
		System.out.println(sheet.getRow(0).getLastCellNum());
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equals(primaryKey)) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					// System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
					data[0][j] = sheet.getRow(i).getCell(j).getStringCellValue();

				}

			}
		}

		return data;
	}
}