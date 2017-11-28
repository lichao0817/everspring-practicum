package web;


import domain.Class;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.ClassService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private ClassService classService;

    public ClassController() {
        this.classService = new ClassService();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Class> getAllClasses() {
        return stubClasses();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addClass(@RequestBody Class c) {
        if (classService.addClass(c)) {
            return "CREATE SUCCEEDED";
        }
        return "CREATE FAILED";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Class getClass(@PathVariable("id") int id) {
        return classService.getClass(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteClass(@PathVariable("id") int id) {
        if (classService.deleteClass(id)) {
            return "DELETE SUCCEEDED";
        }
        return "DELETE FAILED";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String updateClass(@PathVariable("id") int id, @RequestBody Class c) {
        if (classService.updateClass(c)) {
            return "UPDATE SUCCEEDED";
        }
        return "UPDATE FAILED";
    }

    private static List<Class> stubClasses() {
        List<Class> classes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            classes.add(new Class("Class"+i, i));
        }
        return classes;
    }
}
