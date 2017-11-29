package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "course")
public class Course {
    @Id
    @JsonProperty("courseCode")
    private String courseCode;

    @JsonProperty("name")
    private String name;

    private Set<String> users;

    public Course() {}

    public Course(String courseCode, String name, Set<String> users) {
        this.courseCode = courseCode.toLowerCase();
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode.toLowerCase();
    }

    public Set<String> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseCode != null ? courseCode.equals(course.courseCode) : course.courseCode == null;
    }

    public String toString() {
        return "name: " + name + ", courseCode: " + courseCode;
    }
}
