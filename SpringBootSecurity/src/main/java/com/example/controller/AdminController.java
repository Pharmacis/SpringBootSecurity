package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.model.User;
import com.example.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private com.example.service.UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsers(Model model){
        model.addAttribute ("list_users",userService.listUsersWithRoles ());

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model){
        model.addAttribute ("user",userService.getUserById (id));
        model.addAttribute ("ROLES",userService.getRoles ());
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editUser (@ModelAttribute("user") User user,@RequestParam List<String> rolesValues,Model model){
        userService.updateUserAndHisRoles (user,rolesValues);
        model.addAttribute ("ROLES", userService.getRoles ());
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String getAddForm(Model model){
        model.addAttribute("ROLES", userService.getRoles ());
        return "add";
    }

    @PostMapping ("/add")
    public String addUser (Model model, @ModelAttribute("user") User user,
                           @RequestParam List<String> rolesValues) {
            userService.addUserWithRoles (user, rolesValues);
            return "redirect:/admin";

    }

    @GetMapping("/delete/{id}")
    public String getDeleteForm (@PathVariable ("id") Long id){
        userService.deleteById (id);
        return "redirect:/admin";
   }
}
