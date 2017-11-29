package com.tinylms.service;

import com.tinylms.domain.Course;
import com.tinylms.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public boolean addCourse(Course c) {
        if (courseRepo.exists(c.getCourseCode())) {
            return false;
        }
        Course resp = courseRepo.insert(c);
        return resp != null;
    }

    public boolean updateCourse(Course c) {
        if (!courseRepo.exists(c.getCourseCode())) {
            return false;
        }
        Course resp = save(c);
        return resp != null;
    }

    public boolean deleteCourse(String courseCode) {
        if (!courseRepo.exists(courseCode)) {
            return false;
        }
        courseRepo.delete(courseCode);
        return true;
    }

    public Course save(Course course) {
        return courseRepo.save(course);
    }

    public Course getCourse(String courseCode) {
        return courseRepo.findOne(courseCode);
    }
}
