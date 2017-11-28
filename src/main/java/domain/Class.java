package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class Class {
    @JsonProperty("name")
    private String name;

    @Id
    @JsonProperty("courseCode")
    private String courseCode;

    public Class() {}

    public Class(String name, String courseCode) {
        this.name = name;
        this.courseCode = courseCode;
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
        this.courseCode = courseCode;
    }

    public String toString() {
        return "name: " + name + ", courseCode: " + courseCode;
    }
}
