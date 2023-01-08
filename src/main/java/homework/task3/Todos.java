package homework.task3;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class Todos {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("userId=" + userId)
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("completed=" + completed)
                .toString();
    }
}
