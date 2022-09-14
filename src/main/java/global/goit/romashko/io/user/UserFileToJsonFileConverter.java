package global.goit.romashko.io.user;

import java.util.List;

public final class UserFileToJsonFileConverter {

    private final String pathFrom;
    private final String pathTo;

    public UserFileToJsonFileConverter(String pathFrom, String pathTo) {
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    public String convert() {
        UserFileReader reader = new UserFileReader(pathFrom);
        List<User> users = reader.readUsers();

        UserFileWriter writer = new UserFileWriter(pathTo);
        String json = writer.writeJson(users);

        return json;
    }
}
