package homework.task1;

import java.util.StringJoiner;

public class User {
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) {this.username = username; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }

    public void setWebsite(String website) { this.website = website; }

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("\nid=" + id)
                .add("\nname='" + name + "'")
                .add("\nusername='" + username + "'")
                .add("\nemail='" + email + "'")
                .add("\naddress=" + address)
                .add("\nphone='" + phone + "'")
                .add("\nwebsite='" + website + "'")
                .add("\ncompany=" + company)
                .toString();
    }
}
class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getSuite() { return suite; }

    public void setSuite(String suite) { this.suite = suite; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getZipcode() { return zipcode; }

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public Geo getGeo() { return geo; }

    public void setGeo(Geo geo) { this.geo = geo; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("\nstreet='").append(street).append('\'');
        sb.append(", \nsuite='").append(suite).append('\'');
        sb.append(", \ncity='").append(city).append('\'');
        sb.append(", \nzipcode='").append(zipcode).append('\'');
        sb.append(", \ngeo=").append(geo);
        sb.append('}');
        return sb.toString();
    }
}
class Geo {
    private String lat;
    private String lng;

    public String getLat() { return lat;}

    public void setLat(String lat) { this.lat = lat; }

    public String getLng() { return lng; }

    public void setLng(String lng) { this.lng = lng; }

    @Override
    public String toString() {
        return new StringJoiner(", ",  "[", "]")
                .add("lat='" + lat + "'")
                .add("lng='" + lng + "'")
                .toString();
    }
}

class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCatchPhrase() { return catchPhrase; }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() { return bs; }

    public void setBs(String bs) { this.bs = bs; }

    @Override
    public String toString() {
        return new StringJoiner(", ",  "[", "]")
                .add("\nname='" + name + "'")
                .add("\ncatchPhrase='" + catchPhrase + "'")
                .add("\nbs='" + bs + "'\n")
                .toString();
    }
}
