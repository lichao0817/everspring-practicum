package web;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllClasses() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String addClass(@RequestBody User user) {
        if (userService.addUser(user)) {
            return "USER CREATE SUCCEEDED";
        }
        return "USER CREATE FAILED";
    }

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteUser(@PathVariable("username") String username) {
        if (userService.deleteUser(username)) {
            return "USER DELETE SUCCEEDED";
        }
        return "USER DELETE FAILED";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateClass(@PathVariable("username") String username, @RequestBody User user) {
        if (userService.updateUser(user)) {
            return "USER UPDATE SUCCEEDED";
        }
        return "USER UPDATE FAILED";
    }
}
