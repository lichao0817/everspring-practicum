package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class User {
    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private int type;

    @Id
    @JsonProperty("username")
    private String username;

    public static final int TYPE_STUDENT = 1;
    public static final int TYPE_INSTRUCTOR = 2;

    public User() {}

    public User(String name, int type, String username) {
        this.name = name;
        this.type = type;
        this.username = username;
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
}
