package bg.methodia.task.problem2;

import java.util.*;
import java.util.stream.Collectors;

public class CountWords {
    private String sentence;
    private Map<String, Integer> dictionary;

    public CountWords(String sentence) {
        this.sentence = sentence;
        dictionary = new HashMap<>();
    }

    public Map<String, Integer> getSortedDictionaryByValueThenKey() {
        count();
        return dictionary.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    private void count() {
        String delimiter = "[ .,!?]+";
        String[] arr = sentence.split(delimiter);
        for (String s : arr) {
            String lower = s.toLowerCase();
            dictionary.put(lower, dictionary.getOrDefault(lower, 0) + 1);
        }

    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Map<String, Integer> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, Integer> dictionary) {
        this.dictionary = dictionary;
    }
}
