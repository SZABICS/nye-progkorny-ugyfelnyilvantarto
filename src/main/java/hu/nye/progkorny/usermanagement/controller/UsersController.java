package hu.nye.progkorny.usermanagement.controller;

import java.util.List;

import hu.nye.progkorny.usermanagement.model.entities.Users;
import org.springframework.core.convert.converter.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class for user manager application.
 *
 * @author Szabics
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UsersController {

    private static final String REDIRECT_URL_FOR_ALL_USER = "users/alluser";
    private static final String REDIRECT_URL_FOR_USER_EDIT = "users/edit";
    private static final String REDIRECT_URL_FOR_USER_CREATE = "users/create";

    /*@Autowired
    UsersRepository usersRepository;*/

    private List<Users> users = Users.getExists();

    /**
     * Prints the create form (redirect to there).
     */
    @GetMapping(path = "/create")
    public String newUserForm(final Model model) {
        return REDIRECT_URL_FOR_USER_CREATE;
    }

    /**
     * Add a user for an existed list (may be null).
     */
    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addUser(final Model model,
                          @ModelAttribute("user") Users usersRequest) {
        usersRequest.setId(users.size() + 1);
        users.add(usersRequest);
        model.addAttribute("users", users);
        return "redirect:/" + REDIRECT_URL_FOR_ALL_USER;
    }

    /**
     * Prints all the existed users.
     *
     */
    @GetMapping(value = "/alluser")
    public String getAll(final Model model) {
        model.addAttribute("users", users);
        return REDIRECT_URL_FOR_ALL_USER;
    }

    /**
     * Loads the existed user by id.
     *
     */
    @ResponseBody
    @GetMapping(value = "/load/{id}")
    public ModelAndView loadExistsUser(final @PathVariable Integer id) {
        final ModelAndView userView = new ModelAndView(REDIRECT_URL_FOR_USER_EDIT);
        final Users user = Users.streamUsersAndGiveBackResultById(users, id);
        userView.addObject("user", user);
        return userView;
    }

    /**
     * Returns a redirection for all users.
     */
    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateUser(@ModelAttribute("user") Users usersRequest,
                             final @RequestParam(value = "id", required = false) Integer id, Model model) {
        Users user = Users.streamUsersAndGiveBackResultById(users, id);
        users.set(users.indexOf(user), usersRequest);
        model.addAttribute("users", users);
        return "redirect:/" + REDIRECT_URL_FOR_ALL_USER;
    }

    /**
     * Removes a user from a list of users by a requested id value.
     */
    @GetMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) Integer id, Model model) {
        Users user = Users.streamUsersAndGiveBackResultById(users, id);
        users.remove(user);
        model.addAttribute("users", users);
        return "redirect:/" + REDIRECT_URL_FOR_ALL_USER;
    }
}
