package Module13;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WebTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static void main (String[] args) throws IOException, InterruptedException {

        //TASK 1 calling methods
        task1SendPost("userAdd.json");
        task1SendPut(9, "user9Change.json");
        task1SendDelete(11);
        System.out.println("Task 1.4 displaying information on all objects...");
        task1SendGet(0);
        System.out.println("Task 1.5 displaying information on object 7...");
        task1SendGet(7);
        task1SendGetUsername("Kamren");

        //TASK 2 calling method
        task2SendGetCommentsToLastPostByUser(1);

        //TASK 3 calling method
        task3displayingOpenUserTasksById(1);
    }
    // TASK1 methods
    private static void task1SendPost(String pathFile) throws IOException, InterruptedException {
        System.out.println("Task 1.1 create new object...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(pathFile)))
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    private static void task1SendPut(int userId, String pathJsonFile) throws IOException, InterruptedException {
        System.out.println("Task 1.2 update exist object...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId))
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get(pathJsonFile)))
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    private static void task1SendDelete(int userId) throws IOException, InterruptedException {
        System.out.println("Task 1.3 delete exist object...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId))
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    private static void task1SendGet(int userId) throws IOException, InterruptedException {
        String stringID;
        if (userId > 0)
            stringID = "/" + userId;
        else stringID = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + stringID))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    private static void task1SendGetUsername(String userName) throws IOException, InterruptedException {
        System.out.println("Task 1.6 displaying information on object by username...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/?username=" + userName))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    // TASK2 methods
    private static void task2SendGetCommentsToLastPostByUser (int userId) throws IOException, InterruptedException {
        System.out.println("Task 2 writing all comments to a post to a file...");
        int latestPostUserId = getLatestPostId(userId);
        if (latestPostUserId == -1) {
            System.out.println("NO POSTS");
            return;
        }
        String fileName = "user-" + userId + "-post-" + latestPostUserId + "-comments.json";
        // writing to file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(getCommentsForPost(latestPostUserId));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getCommentsForPost(int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(POSTS_URL + "/" + postId + "/comments"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body().replace("\\","/"));
            // parsing a json string, collecting all comments into a delimited string
            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                Stream<JsonElement> jsonElementStream = StreamSupport.stream(jsonArray.spliterator(), false);
                return jsonElementStream
                        .filter(JsonElement::isJsonObject)
                        .map(JsonElement::getAsJsonObject)
                        .filter(jsonObject -> jsonObject.has("body") && jsonObject.get("body").isJsonPrimitive())
                        .map(jsonObject -> jsonObject.get("body").getAsString())
                        .collect(Collectors.joining("\", \n\"", "{\n\"", "\"\n}"));
            }
        }
        return "{\"UPS, NO COMMENTS\"}";
    }
    public static int getLatestPostId(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId + "/posts"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body());
            //search for latest post
            AtomicInteger maxId = new AtomicInteger(Integer.MIN_VALUE);
            if (jsonElement.isJsonArray()) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                Stream<JsonElement> jsonElementStream = StreamSupport.stream(jsonArray.spliterator(), false);
                jsonElementStream
                        .filter(JsonElement::isJsonObject)
                        .map(JsonElement::getAsJsonObject)
                        .filter(jsonObject -> jsonObject.has("id") && jsonObject.get("id").isJsonPrimitive())
                        .map(jsonObject -> jsonObject.get("id").getAsInt())
                        .forEach(id -> maxId.updateAndGet(currentMax -> Math.max(currentMax, id)));
            }
            return maxId.get();
        }
        return -1;
    }

    // TASK3 methods
    private static void task3displayingOpenUserTasksById(int userId) throws IOException, InterruptedException {
        System.out.println("Task 3 displaying open user ID tasks...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + userId + "/todos"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if (response.statusCode() == 200) {
            System.out.println(getOpenTasks(response.body()));
        }
    }
    private static String getOpenTasks(String jsonString) {
        JsonElement jsonElement = JsonParser.parseString(jsonString);
        // parsing a json string, collecting all not completed tasks into a delimited string
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Stream<JsonElement> jsonElementStream = StreamSupport.stream(jsonArray.spliterator(), false);
            return jsonElementStream
                    .filter(JsonElement::isJsonObject)
                    .map(JsonElement::getAsJsonObject)
                    .filter(jsonObject -> jsonObject.has("completed") && (jsonObject.get("completed").getAsString() == "false"))
                    .map(jsonObject -> jsonObject.get("title").getAsString())
                    .collect(Collectors.joining("\", \n\"", "{\n\"", "\"\n}"));
        }
        return "UPS, NO OPEN TASK";
    }
}
