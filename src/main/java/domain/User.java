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
    private String type;

    private Set<String> courses;

    public static final String TYPE_STUDENT = "student";
    public static final String TYPE_INSTRUCTOR = "instructor";

    public User() {}

    public User(String name, String type, String username, Set<String> courses) {
        this.name = name;
        this.type = type.toLowerCase().trim();
        this.username = username;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase().trim();
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

    public static boolean checkUser(User user) {
        if (user.getType().equals(TYPE_INSTRUCTOR) || user.getType().equals(TYPE_STUDENT)) {
            for (int i = 0; i < user.getUsername().length(); i++) {
                if (Character.isSpaceChar(user.getUsername().charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username != null ? username.equals(user.username) : user.username == null;
    }
}
