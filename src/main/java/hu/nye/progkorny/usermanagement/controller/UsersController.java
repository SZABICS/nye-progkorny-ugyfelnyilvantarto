package hu.nye.progkorny.usermanagement.controller;

import hu.nye.progkorny.usermanagement.model.entities.Users;
import org.springframework.core.convert.converter.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping(path = "/create")
    public String newUserForm(final Model model) {
        return REDIRECT_URL_FOR_USER_CREATE;
    }

    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addUser(final Model model,
                          @ModelAttribute("user") Users usersRequest) {
        usersRequest.setId(users.size() + 1);
        users.add(usersRequest);
        model.addAttribute("users", users);
        return "redirect:/"+REDIRECT_URL_FOR_ALL_USER;
    }

    @GetMapping(value = "/alluser")
    public String getAll(final Model model) {
        model.addAttribute("users", users);
        return REDIRECT_URL_FOR_ALL_USER;
    }

    @ResponseBody
    @GetMapping(value = "/load/{id}")
    public ModelAndView loadExistsUser(final @PathVariable Integer id) {
        final ModelAndView userView = new ModelAndView(REDIRECT_URL_FOR_USER_EDIT);
        final Users user = Users.streamUsersAndGiveBackResultById(users, id);
        userView.addObject("user", user);
        return userView;
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateUser(@ModelAttribute("user") Users usersRequest,
                             final @RequestParam(value = "id", required = false) Integer id, Model model) {
        Users user = Users.streamUsersAndGiveBackResultById(users, id);
        users.set(users.indexOf(user), usersRequest);
        model.addAttribute("users", users);
        return "redirect:/"+REDIRECT_URL_FOR_ALL_USER;
    }

    @GetMapping(value = "/remove/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) Integer id, Model model) {
        Users user = Users.streamUsersAndGiveBackResultById(users, id);
        users.remove(user);
        model.addAttribute("users", users);
        return "redirect:/"+REDIRECT_URL_FOR_ALL_USER;
    }
}
