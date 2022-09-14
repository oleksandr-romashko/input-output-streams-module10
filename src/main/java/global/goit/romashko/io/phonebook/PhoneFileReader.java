package global.goit.romashko.io.phonebook;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneFileReader {
    public void printPhones(String path) {
        try (FileReader reader = new FileReader(path);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.strip();
                if (isValidPhoneNumber(line)) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private boolean isValidPhoneNumber(String line) {
        String regex = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line.strip());
        return matcher.find();
    }
}
