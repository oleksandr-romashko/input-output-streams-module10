package global.goit.romashko.io.word_processor;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class FileWordCounter {

    private final String path;

    public FileWordCounter(String path) {
        this.path = path;
    }

    public String countWords() {
        TreeMap<String, Integer> map = new TreeMap<>();
        try (FileReader reader = new FileReader(path);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.strip();
                if (!line.isBlank()) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        if (map.get(word) == null) {
                            map.put(word, 1);
                        } else {
                            int occurrence = map.get(word) + 1;
                            map.put(word, occurrence);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Exception while reading users from file: " + path
                    + " " + e.getMessage());
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (Objects.equals(o1.getValue(), o2.getValue())) {
                    return o1.getKey().compareTo(o2.getKey());
                } else if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        return result.toString();
    }
}
