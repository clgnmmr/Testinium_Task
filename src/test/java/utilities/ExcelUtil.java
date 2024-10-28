package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    private static Workbook workbook;
    private static Sheet sheet;

    public static void loadExcel(String filePath, int sheetIndex) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheetAt(sheetIndex);
        fileInputStream.close();
    }

    public static String getCellData(int row, int col) {
        return sheet.getRow(row).getCell(col).toString();
    }


}
