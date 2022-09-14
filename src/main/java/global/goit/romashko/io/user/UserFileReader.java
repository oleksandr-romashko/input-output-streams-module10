package global.goit.romashko.io.user;

import global.goit.romashko.io.user.User;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class UserFileReader {
    private final String[] HEADERS = {"name", "age"};

    private final String path;
    public UserFileReader(String path) {
        this.path = path;
    }
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (FileReader reader = new FileReader(path);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.strip();
                if (    !line.isBlank()
                     && !line.contains(HEADERS[0])
                     && !line.contains(HEADERS[1])) {
                    String[] values = line.split(" ");
                    User user = new User(values[0], Integer.parseInt(values[1]));
                    users.add(user);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Exception while reading users from file: " + path
                    + " " + e.getMessage());
        }
        return users;
    }
}
