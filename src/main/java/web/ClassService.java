package web;

import domain.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepo;

    public List<Class> getAllClasses() {
        return classRepo.findAll();
    }

    public boolean addClass(Class c) {
        if (classRepo.exists(c.getCourseCode())) {
            return false;
        }
        Class resp = classRepo.insert(c);
        return resp != null;
    }

    public boolean updateClass(Class c) {
        if (!classRepo.exists(c.getCourseCode())) {
            return false;
        }
        Class resp = classRepo.save(c);
        return resp != null;
    }

    public boolean deleteClass(String courseCode) {
        if (!classRepo.exists(courseCode)) {
            return false;
        }
        classRepo.delete(courseCode);
        return true;
    }

    public Class getClass(String courseCode) {
        return classRepo.findOne(courseCode);
    }
}
