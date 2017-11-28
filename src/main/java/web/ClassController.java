package web;


import domain.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addClass(@RequestBody Class c) {
        if (classService.addClass(c)) {
            return "CLASS CREATE SUCCEEDED";
        }
        return "CLASS CREATE FAILED";
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
            return "CLASS DELETE SUCCEEDED";
        }
        return "CLASS DELETE FAILED";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateClass(@PathVariable("id") int id, @RequestBody Class c) {
        if (classService.updateClass(c)) {
            return "CLASS UPDATE SUCCEEDED";
        }
        return "CLASS UPDATE FAILED";
    }
}