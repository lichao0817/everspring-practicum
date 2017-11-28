package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Class {
    @JsonProperty("name")
    private String name;

    @JsonProperty("courseCode")
    private int courseCode;

    public Class() {}

    public Class(String name, int courseCode) {
        this.name = name;
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public String toString() {
        return "name: " + name + ", courseCode: " + courseCode;
    }
}
