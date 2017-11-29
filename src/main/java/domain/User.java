package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
public class User {
    @Id
    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private int type;

    private Set<String> courses;

    public static final int TYPE_STUDENT = 1;
    public static final int TYPE_INSTRUCTOR = 2;

    public User() {}

    public User(String name, int type, String username, Set<String> courses) {
        this.name = name;
        this.type = type;
        this.username = username;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getCourses() {
        if (this.courses == null) {
            this.courses = new HashSet<>();
        }
        return courses;
    }

    public void setCourses(Set<String> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equals(user.username) : user.username == null;
    }
}
