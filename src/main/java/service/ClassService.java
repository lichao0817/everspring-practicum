package service;

import domain.Class;

/**
 * Created by Chao on 2017/11/28.
 */
public class ClassService {
    public Class[] getAllClasses() {
        return null;
    }

    public boolean addClass(Class c) {
        return false;
    }

    public boolean updateClass(Class c) {
        return false;
    }

    public boolean deleteClass(int courseCode) {
        return false;
    }

    public Class getClass(int courseCode) {
        return new Class("Class" + courseCode, courseCode);
    }
}
