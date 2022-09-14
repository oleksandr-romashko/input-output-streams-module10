package global.goit.romashko.io.user;

import com.google.gson.*;
import global.goit.romashko.io.user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class UserFileWriter {

    private final String path;

    public UserFileWriter(String path) {
        this.path = path;
    }

    public String writeJson(List<User> users) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);

        try (FileWriter writer = new FileWriter(path))
        {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Exception while writing users to file: " + path
                    + " " + e.getMessage());
        }

        return json;
    }
}
