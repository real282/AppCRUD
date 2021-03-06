package ru.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.model.User;
import ru.service.UserService;


@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping (value = "/")
    public String printCars(Model model) {
        model.addAttribute("messages", userService.listUsers());
        return "index";
    }

    @RequestMapping(value = "/create")
    public String createUser(Model model) {
        model.addAttribute("User", new User());
        return "create";
    }

    @RequestMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        userService.add(user);
        model.addAttribute("User", new User());
        return "create";
    }

    @RequestMapping(value = "/delete={id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/update={id}")
    public String update(@PathVariable("id") long id, Model model) {
        model.addAttribute("User", userService.getUserId(id));
        return "update";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
