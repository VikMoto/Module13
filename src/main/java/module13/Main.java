package module13;

import java.net.URI;
import java.util.List;

public class Main {

    private static final String GET_USER_BY_ID = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws Exception {
//        GsonParser parser = new GsonParser();
//        Root root = parser.parse();

//        System.out.println("root = " + root.toString());
        final User defaultUser = createDefaultUser();
        System.out.println("************************************");
        System.out.println(defaultUser);
        //TASK #1
        final User userPost = HttpUtil.sendPost(URI.create(GET_USER_BY_ID), defaultUser);
        System.out.println("************************************");
//
//        //TASK #2
//        final String format = String.format("%s?userId=%d", GET_USER_BY_ID, 1);
//        final List<User> user1 = HttpUtil.sendGet(URI.create(format));
//        for (User u : user1) {
//            System.out.println("TASK #2  " + u);

        //TASK #3 1.create user, 2.get users List, 3. remove user, 4.get users List and verify that user has removed
        final User user3 = HttpUtil.sendPost(URI.create(GET_USER_BY_ID), defaultUser);
        System.out.println("************************************");
        final List<User> users1 = HttpUtil.sendGet(URI.create(GET_USER_BY_ID));
        System.out.println("Users before delete  " +users1);
        System.out.println("**********************************************************");
        HttpUtil.sendDeleteBody(URI.create(GET_USER_BY_ID), defaultUser);
        final List<User> users2 = HttpUtil.sendGet(URI.create(GET_USER_BY_ID));
        System.out.println("Users after delete  " + users2);

    }


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
}
