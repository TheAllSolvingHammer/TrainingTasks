package bg.methodia.task.problem5;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
    private Workbook workbook;
    private Sheet sheet;
    private File file;

    public ExcelWriter(File file) {
        this.file = file;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Sheet 1");
    }

    public void writeResult(List<List<Object>> matrix) throws IOException {
        ExcelWriter writer = new ExcelWriter(file);
        sheet.createRow(0);
        int counter = 0;
        for (List<Object> list : matrix) {
            Row row = sheet.createRow(counter);
            int counter2 = 0;
            for (Object o : list) {
                Cell cell = row.createCell(counter2, CellType.STRING);
                cell.setCellValue(o.toString());
                counter2++;
            }
            counter++;
        }
        sheet.setColumnWidth(0, 15 * 256);
        for (int i = 1; i < 8; i++) {
            sheet.setColumnWidth(i, 30 * 256);
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }
}
