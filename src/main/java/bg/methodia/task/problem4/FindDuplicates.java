package bg.methodia.task.problem4;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {
    private static final String chars = "abcdefghijklmnopqrstuvwxyzабвгдежзийклмнопрстуфхцчшщъьюя!? .,1234567890";

    public Map<Character, Integer> findDuplicates() {
        String string = generateRandomString();
        System.out.println("Current string: " + string);
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            Character c = string.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map.entrySet()
                .stream()
                .filter(item->item.getValue()>1)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
