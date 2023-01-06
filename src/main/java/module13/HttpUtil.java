package module13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static List<User> sendGet(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        response.body();
        List<User> users = new ArrayList<>();
//        user = List.of(GSON.fromJson(response.body(), User[].class));
        users = (GSON.fromJson(response.body(), new TypeToken<List<User>>(){}.getType()));
        return users;
    }

    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() = " + response.statusCode());
        final User user1 = GSON.fromJson(response.body(), User.class);
        System.out.println("user1 = " + user1);
        return user1;

    }

    public static void sendDeleteBody(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
    }
}
