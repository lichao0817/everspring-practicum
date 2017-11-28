package web;


import com.fasterxml.jackson.core.JsonProcessingException;
import domain.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.Utils;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getAllClasses() throws JsonProcessingException {
        List<Class> data = classService.getAllClasses();
        if (data == null || data.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Utils.getJsonBody(data), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addClass(@RequestBody Class c) {
        if (classService.addClass(c)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<String> getClass(@PathVariable("id") String id) {
        Class c = classService.getClass(id);
        if (c == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Utils.getJsonBody(c), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClass(@PathVariable("id") String id) {
        if (classService.deleteClass(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateClass(@PathVariable("id") String id, @RequestBody Class c) {
        if (c.getCourseCode().equals(id) && classService.updateClass(c)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUsersForClass(@PathVariable("id") String id) {
        return new ResponseEntity<>("GET ALL USERS FOR CLASS", HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/users/{username}", method = RequestMethod.PUT)
    public ResponseEntity<String> enrollUser(@PathVariable("id") String id, @PathVariable("username") String username) {
        return new ResponseEntity<>("ADD USER TO CLASS", HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> unenrollUser(@PathVariable("id") String id, @PathVariable("username") String username) {
        return new ResponseEntity<>("REMOVE USER FROM CLASS", HttpStatus.OK);
    }
}