package homework.task2;

import lombok.Data;

import java.util.StringJoiner;

@Data
class Posts {
    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("userId=" + userId)
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("body='" + body + "'")
                .toString();
    }

}