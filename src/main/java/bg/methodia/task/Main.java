package bg.methodia.task;

import bg.methodia.task.problem1.ReverseString;
import bg.methodia.task.problem2.CountWords;
import bg.methodia.task.problem3.ArrayIteration;
import bg.methodia.task.problem4.FindDuplicates;
import bg.methodia.task.problem5.ExcelReader;
import bg.methodia.task.problem5.ExcelWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //1
        String stringToReverse = "Hello world!😍😣😑😙";
        ReverseString reverseString = new ReverseString(stringToReverse);
        System.out.println(reverseString.reverseString());
        System.out.println();

        //2
        CountWords countWords = new CountWords("Az Az Ne Ne,,,,,... ne 1 !1 1 1?? a...s?Dsad??A...!!!!D!!!S?");
        System.out.println(countWords.getSortedDictionaryByValueThenKey());
        System.out.println();

        //3
        ArrayIteration arrayIteration = new ArrayIteration();
        System.out.println(arrayIteration.result());

        //4
        FindDuplicates findDuplicates = new FindDuplicates();
        System.out.println(findDuplicates.findDuplicates());
        System.out.println();

        //5
        File excelFile = new File("src/main/resources/file.xlsx");
        File excelFileResult = new File("src/main/resources/fileRes.xlsx");
        try {
            ExcelReader reader = new ExcelReader(excelFile);
            reader.readFromExcelFile();
            System.out.println("Original table");
            reader.printTable(reader.getTable());
            ExcelWriter excelWriter = new ExcelWriter(excelFileResult);
            excelWriter.writeResult(reader.getFinalResult("ИК"));
        } catch (IOException | InvalidFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}