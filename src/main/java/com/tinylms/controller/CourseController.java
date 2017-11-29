package com.tinylms.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinylms.domain.Course;
import com.tinylms.domain.User;
import com.tinylms.service.CourseService;
import com.tinylms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tinylms.util.Utils;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/classes")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllCourses() throws JsonProcessingException {
        List<Course> data = courseService.getAllCourses();
        if (data == null || data.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Utils.getJsonBody(data, "courses"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addCourse(@RequestBody Course c) {
        if (courseService.addCourse(c)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/{courseCode}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCourse(@PathVariable("courseCode") String courseCode) {
        Course c = courseService.getCourse(courseCode);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Utils.getJsonBody(c, "course"), HttpStatus.OK);
    }

    @RequestMapping(value = "/{courseCode}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCourse(@PathVariable("courseCode") String courseCode) {
        if (courseService.deleteCourse(courseCode)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{courseCode}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCourse(@PathVariable("courseCode") String courseCode, @RequestBody Course c) {
        if (c.getCourseCode().equals(courseCode) && courseService.updateCourse(c)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{courseCode}/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<String> getEnrollment() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{courseCode}/users/{username}", method = { RequestMethod.PUT, RequestMethod.POST })
    public ResponseEntity<String> enrollUser(@PathVariable("courseCode") String courseCode,
                                             @PathVariable("username") String username) {
        Course course = courseService.getCourse(courseCode);
        User user = userService.getUser(username);

        if (course == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Set<String> users = course.getUsers();
        Set<String> courses = user.getCourses();
        if (!users.contains(user)) {
            users.add(username);
            courses.add(courseCode);
            userService.save(user);
            courseService.save(course);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }

    @RequestMapping(value = "{courseCode}/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> unenrollUser(@PathVariable("courseCode") String courseCode,
                                               @PathVariable("username") String username) {
        Course course = courseService.getCourse(courseCode);
        User user = userService.getUser(username);

        if (course == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Set<String> users = course.getUsers();
        Set<String> courses = user.getCourses();
        if (users.contains(user)) {
            users.remove(username);
            courses.remove(courseCode);
            userService.save(user);
            courseService.save(course);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}