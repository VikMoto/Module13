package homework.task2;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class Comments {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("postId=" + postId)
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("body='" + body + "'")
                .toString();
    }
}
