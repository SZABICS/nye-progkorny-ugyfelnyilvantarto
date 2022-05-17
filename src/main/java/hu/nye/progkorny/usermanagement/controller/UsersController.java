package hu.nye.progkorny.usermanagement.controller;

import hu.nye.progkorny.usermanagement.model.entities.Users;
import hu.nye.progkorny.usermanagement.model.repository.UsersRepository;
import hu.nye.progkorny.usermanagement.model.request.UsersRequest;
import hu.nye.progkorny.usermanagement.model.response.UsersResponse;
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

    @Autowired
    UsersRepository usersRepository;

    private List<Users> users = Users.getExists();

    @GetMapping(path = "/create")
    public String newUserForm(final Model model) {
        return REDIRECT_URL_FOR_USER_CREATE;
    }

    @PostMapping(path = "/adduser", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addUser(final Model model,
                          @ModelAttribute("user") Users usersRequest) {
        usersRequest.setId(users.get(users.size() - 1).getId() + 1);
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
        final Users user = users.stream()
                .filter(customer -> id.equals(customer.getId()))
                .findAny()
                .orElse(null);
        userView.addObject("user", user);
        return userView;
    }

    @PostMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateUser(@ModelAttribute("user") Users usersRequest,
                             final @RequestParam(value = "id", required = false) Integer id, Model model) {
        Users user = new Users();
        user = users.stream()
                .filter(customer -> id.equals(customer.getId()))
                .findAny()
                .orElse(null);
        users.set(users.indexOf(user), usersRequest);
        model.addAttribute("users", users);
        return "redirect:/"+REDIRECT_URL_FOR_ALL_USER;
    }

    @GetMapping(value = "/remove/{id}")
    public String deleteUser( final @RequestParam(value = "id", required = false) Integer id, Model model) {
        Users user = users.stream()
                .filter(customer -> id.equals(customer.getId()))
                .findAny()
                .orElse(null);
        if(user != null) {
            users.remove(user);
        }
        model.addAttribute("users", users);
        return "redirect:/"+REDIRECT_URL_FOR_ALL_USER;
    }
}
