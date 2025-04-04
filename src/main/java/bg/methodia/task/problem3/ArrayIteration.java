package bg.methodia.task.problem3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ArrayIteration {
    private List<Integer> integerList;

    public ArrayIteration() {
        this.integerList = new ArrayList<>();
    }

    public String result() {
        generateRandomArray();
        return "Traversing the integer list:\n" + "Classic for loop took: " + iterateForLoop() + " nano secs.\n" +
                "Classic while loop took: " + iterateWhileLoop() + " nano secs.\n" +
                "Iterator iteration took: " + iterateWithIterator() + " nano secs.\n" +
                "Secondary traverse of the integer list:\n" + "Classic for loop took: " + iterateForLoop() + " nano secs.\n" +
                "Classic while loop took: " + iterateWhileLoop() + " nano secs.\n" +
                "Iterator iteration took: " + iterateWithIterator() + " nano secs.\n";
    }


    private void generateRandomArray() {
        integerList.clear();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            integerList.add(random.nextInt());
        }
    }

    private Long iterateForLoop() {
        long start = System.nanoTime();
        Integer val;
        for (int i = 0; i < integerList.size(); i++) {
            val = integerList.get(i);
        }
        long end = System.nanoTime();
        return end - start;

    }

    private Long iterateWhileLoop() {
        long start = System.nanoTime();
        Integer val;
        int i = 0;
        while (i < integerList.size()) {
            val = integerList.get(i);
            i++;
        }
        long end = System.nanoTime();
        return end - start;
    }

    private Long iterateWithIterator() {
        long start = System.nanoTime();
        Integer val;
        for (Iterator<Integer> i = integerList.iterator(); i.hasNext(); ) {
            val = i.next();
        }
        long end = System.nanoTime();
        return end - start;

    }


}
