package Module10;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RebuildToJsonTest {

    public static void main(String[] args) {
        String filename = "file2.txt";
        String jsonFilename = "user.json";
        String[] nextDataLine;
        String dataLine;
        List<User> users = new ArrayList<User>();
        StringBuffer outputJson = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            int counter = 0;
            while ((dataLine = bufferedReader.readLine()) != null) {
                nextDataLine = dataLine.split(" ",2);
                if (counter != 0) {
                    users.add(new User(nextDataLine[0], Integer.valueOf(nextDataLine[1])));
                }
                counter++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        };
        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter out = new PrintWriter(jsonFilename)) {
               out.write(mapper.writeValueAsString(users));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
class User implements Serializable {
    private String name;
    private int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
