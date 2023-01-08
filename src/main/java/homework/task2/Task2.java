package homework.task2;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task 2
 * Complete the program with a method that will display all comments on the last
 * post of a certain user and write them to a file. *
 * https://jsonplaceholder.typicode.com/users/1/posts We consider the last post
 * with the largest id. *
 * https://jsonplaceholder.typicode.com/posts/10/comments *
 * The file should be called user-X-post-Y-comments.json, where X is the user id,
 * Y is the post number.
 * */
@Data
public class Task2 implements Task {


    private String urlPosts;
    private String urlComments;

    public Task2(String urlPosts, String urlComments) {
        this.urlPosts = urlPosts;
        this.urlComments = urlComments;
    }

    Gson gson = new Gson();

    @Override
    public void proceed() throws IOException, InterruptedException {
        Comments comments = new Comments();
        Posts posts = new Posts();

        final String responsePosts = Jsoup
                .connect(urlPosts)
                .ignoreContentType(true)
                .get()
                .body()
                .text();


        Type collectionType = new TypeToken<List<Posts>>() {
        }.getType();
        List<Posts> postsList = gson.fromJson(responsePosts, collectionType);

// find post with max id
        Posts postsWithMaxId = postsList
                .stream()
                .sorted((a, b) -> b.getId() - a.getId())
                .findFirst()
                .get();
        System.out.println("first = " + postsWithMaxId.getId());


        String responseComments = Jsoup
                .connect(urlComments)
                .ignoreContentType(true)
                .get()
                .body()
                .text();


        collectionType = new TypeToken<List<Comments>>() {
        }.getType();
        List<Comments> commentsList = gson.fromJson(responseComments, collectionType);
//find all post with maxId

        List<Comments> allCommentsWithMaxId = commentsList
                .stream()
                .filter(it -> it.getPostId() == postsWithMaxId.getId())
                .collect(Collectors.toList());

//        for (Posts ps : postsList) {
//            System.out.println(ps);
//        }
        System.out.println("__________________________________________________________________");

        for (Comments com : commentsList) {
            System.out.println(com);
        }
        System.out.println("__________________________________________________________________");
        System.out.println();
        for (Comments com : allCommentsWithMaxId) {
            System.out.println(com);
        }
        final String jsonAllCommentsMaxId = gson.toJson(allCommentsWithMaxId);
       // System.out.println("jsonAllCommentsMaxId = " + jsonAllCommentsMaxId);

        String file = "user-X-post-Y-comments.json";
        final String replace = file
                .replace("X", Integer.toString(postsWithMaxId.getUserId()))
                .replace("Y", Integer.toString(postsWithMaxId.getId()));
        System.out.println("file = " + replace);
        FileWriter fileWriter = new FileWriter(replace);
        fileWriter.write(jsonAllCommentsMaxId);
        fileWriter.flush();
        fileWriter.close();

    }
}



