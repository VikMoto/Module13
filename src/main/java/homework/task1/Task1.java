package homework.task1;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import homework.task2.Task;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Task 1
 * The program must contain methods to implement the following functionality: *
 * creating a new object at https://jsonplaceholder.typicode.com/users.
 *
 * You may not see the changes on the site right away. The method works correctly
 * if in response to the JSON with the object, the same JSON was returned, but
 * with an id value greater than 1, than the largest id on the site.
 * *
 * updating the object at https://jsonplaceholder.typicode.com/users. You may not
 * see the changes on the site right away. We assume that the method is working
 * correctly if you get an updated JSON in response (it should be the same as what
 * you sent). *
 *
 * deleting an object from https://jsonplaceholder.typicode.com/users. Here we
 * will consider the correct result - the response status from group 2xx (for
 * example, 200).
 *
 * getting information about all users https://jsonplaceholder.typicode.com/users
 *
 * getting user information by id https://jsonplaceholder.typicode.com/users/{id}
 *
 * getting information about the user by username -
 * https://jsonplaceholder.typicode.com/users?username={username}
 * */
public class Task1 implements Task {
    private String url;

    public Task1(String url) {
        this.url = url;
    }

    Gson gson = new Gson();

    //        Root root = parser.parse();
//
//        System.out.println("root = " + root.toString());





    private static User createDefaultUser() {
        User user = new User();
        Address address = new Address();
        Geo geo = new Geo();
        Company company = new Company();

        company.setName("Bula-Zgadala");
        company.setCatchPhrase("Gera-jala-myla dilo-prave-jive");
        company.setBs("zavgdy peremagay moskalya");

        geo.setLat("32.3241");
        geo.setLng("75.7128");

        address.setStreet("Franka");
        address.setSuite("Apt. 23");
        address.setCity("Lviv");
        address.setZipcode("32765");
        address.setGeo(geo);

        user.setId(1);
        user.setName("Big Ukraine");
        user.setUsername("BUkr");
        user.setEmail("ger@google.com");
        user.setAddress(address);
        user.setPhone("+38-093-421-00-12");
        user.setWebsite("werti.ua");
        user.setCompany(company);

        return user;
    }

    @Override
    public void proceed() throws IOException, InterruptedException {
        final User defaultUser = createDefaultUser();
        System.out.println("************************************");
        System.out.println(defaultUser);
        //TASK #1
        final User userPost = HttpUtil.sendPost(URI.create(url), defaultUser);


        //TASK #2
        final String format = String.format("%s?userId=%d", url, 1);

//
//
//        //TASK #3 1.create user, 2.get users List, 3. remove user, 4.get users List and verify that user has removed
//        final User user3 = HttpUtil.sendPost(URI.create(url), defaultUser);
//        System.out.println("************************************");
//        final List<User> users1 = HttpUtil.sendGet(URI.create(GET_USER_BY_ID));
//        System.out.println("Users before delete  " +users1);
//        System.out.println("**********************************************************");
//        HttpUtil.sendDeleteBody(URI.create(GET_USER_BY_ID), defaultUser);
//        final List<User> users2 = HttpUtil.sendGet(URI.create(GET_USER_BY_ID));
//        System.out.println("Users after delete  " + users2);


        System.out.println("**********************************************************");
        System.out.println();
        final User defaultUser2 = createDefaultUser();
        final String userJson = gson.toJson(defaultUser2);
        Connection.Response execute = Jsoup
                .connect(url)
                .timeout(6000)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .method(Connection.Method.POST)
                .requestBody(userJson)
                .execute();

        int statusCode = execute.statusCode();
        System.out.println("statusCode: " + statusCode);
        String responsePost = String.valueOf(execute);
        System.out.println("************************************");

        final String response = Jsoup
                .connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .text();

        List<User> items = new ArrayList<>();
/** getParameterized getType*/
//        final TypeToken<?> typeToken = TypeToken
//                .getParameterized(List.class, CurrencyItem.class);
//        List<CurrencyItem> result = (List<CurrencyItem>) gson.fromJson(response, typeToken);
//        for (CurrencyItem item : result) {
//            System.out.println("item = " + item);
//        }

        /** one more  getType and convert Json => Java Object */
        Type collectionType = new TypeToken<List<User>>() {
        }.getType();
        List<User> enums = gson.fromJson(response, collectionType);

        for (User anEnum : enums) {
            System.out.println(anEnum);
        }
        final Optional<User> id = enums
                .stream()
                .filter(it -> it.getId() == 2)
                .findFirst();
        System.out.println("**************************************\n");
        System.out.println("id = 2  " + id.get());

        final Optional<User> userName = enums
                .stream()
                .filter(it -> it.getUsername().equals("Kamren"))
                .findFirst();
        System.out.println("**************************************\n");
        System.out.println("Username Kamren = " + userName.get());
    }
}
