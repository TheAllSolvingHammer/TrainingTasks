package bg.methodia.task.problem5;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Function;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;
    private List<List<Object>> table;
    private static final EnumMap<CellType, Function<Cell, Object>> enumsCells;

    public ExcelReader(File file) throws IOException, InvalidFormatException {
        this.workbook = new XSSFWorkbook(file);
        this.sheet = workbook.getSheetAt(0);
        table = new ArrayList<>();
    }

    static {
        enumsCells = new EnumMap<>(CellType.class);
        enumsCells.put(CellType.STRING, Cell::getStringCellValue);
        enumsCells.put(CellType.BOOLEAN, Cell::getBooleanCellValue);
        enumsCells.put(CellType.NUMERIC, ExcelReader::getNumeric);
        enumsCells.put(CellType.BLANK, (cell) -> "");
        enumsCells.put(CellType.FORMULA, Cell::getCellFormula);
    }

    private static Object getNumeric(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return new SimpleDateFormat("M/d/yyyy h:mm").format(cell.getDateCellValue());
        } else {
            return cell.getNumericCellValue();
        }
    }

    public void readFromExcelFile() {
        table.clear();

        for (Row row : sheet) {
            List<Object> list = new ArrayList<>();
            for (Cell cell : row) {
                Function<Cell, Object> cellFunction = enumsCells.get(cell.getCellType());
                Object apply = cellFunction.apply(cell);
                list.add(apply);
            }
            table.add(list);
        }
    }

    public void printTable(List<List<Object>> tableList) {
        StringBuilder sb = new StringBuilder("\n");
        for (List<Object> list : tableList) {
            for (Object o : list) {
                sb.append(o.toString()).append("\t\t");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public List<List<Object>> sortTable(String criteria) {
        List<Object> firstRow = table.getFirst();
        Object object = firstRow.stream()
                .filter(o -> o.equals(criteria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No value \"" + criteria + "\" found"));
        int index = firstRow.indexOf(object);
        List<List<Object>> sortedTable = new ArrayList<>(table.stream()
                .skip(1)
                .filter(list -> list.size() >= index - 1)
                .filter(item -> {
                    double o1 = (double) item.get(index);
                    return o1 > 50 && o1 < 70;
                }).toList());
        sortedTable.addFirst(firstRow);
        System.out.println("Sorted table");
        printTable(sortedTable);

        getColumn(sortedTable, criteria);
        return sortedTable;
    }

    public List<List<Object>> getTable() {
        return table;
    }

    public List<Object> getColumn(List<List<Object>> matrix, String criteria) {
        List<Object> firstRow = matrix.getFirst();
        Object object = firstRow.stream()
                .filter(o -> o.equals(criteria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No value \"" + criteria + "\" found"));
        int index = firstRow.indexOf(object);
        Double avg = matrix.stream()
                .skip(1)
                .map(list -> list.get(index))
                .mapToDouble(item -> Double.parseDouble(item.toString()))
                .summaryStatistics()
                .getAverage();
        return List.of(criteria + " average is:", avg);
    }

    public List<List<Object>> getFinalResult(String criteria) {
        List<List<Object>> matrix = sortTable(criteria);
        List<Object> columAverage = getColumn(matrix, criteria);
        matrix.add(columAverage);
        return matrix;
    }

}
