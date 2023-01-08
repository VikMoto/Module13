package homework.task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import homework.task2.Task;
import homework.task2.Task;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task 3
 * Complete the program with a method that will output all open tasks for the
 * user with ID X.
 * https://jsonplaceholder.typicode.com/users/1/todos.
 * All tasks in which completed = false are considered open.
 * */
public class Task3 implements Task {
    private String url;
    private int userId;

    public Task3(String url, int userId) {
        this.url = url;
        this.userId = userId;
    }

    Gson gson = new Gson();

    @Override
    public void proceed() throws IOException, InterruptedException {
        Todos todos = new Todos();

        final String responseTodos = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();


        Type collectionType = new TypeToken<List<Todos>>() {
        }.getType();
        List<Todos> postsList = gson.fromJson(responseTodos, collectionType);

// find post with max id
        final List<Todos> notComplete = postsList
                .stream()
                .filter(it -> it.getUserId() == userId)
                .filter(it -> it.isCompleted() == false)
                .collect(Collectors.toList());

        for (Todos todos1 : notComplete) {
            System.out.println(todos1);
        }


    }
}
