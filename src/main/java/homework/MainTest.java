package homework;


import homework.task1.Task1;
import homework.task2.Task2;
import homework.task3.Task3;


public class MainTest {

    private static final String GET_USER = "https://jsonplaceholder.typicode.com/users";
    private static final String GET_COMMENTS = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String GET_POSTS = "https://jsonplaceholder.typicode.com/users/1/posts";
    private static final String GET_TODOS = "https://jsonplaceholder.typicode.com/users/1/todos";

    public static void main(String[] args) throws Exception {
        Task1 task1 = new Task1(GET_USER);
        Task2 task2 = new Task2(GET_POSTS,GET_COMMENTS);
        Task3 task3 = new Task3(GET_TODOS,1);
        task1.proceed();
        System.out.println("*********************************************************************");

        task2.proceed();
        System.out.println("*********************************************************************");

        task3.proceed();
    }
}
