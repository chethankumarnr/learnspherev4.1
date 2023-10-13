package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.learnsphere.Entities.User;
import com.learnsphere.Service.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession; // Import the standard HttpSession class

@Controller
public class MainController {

    @Autowired
    ServiceImpl ss;

    @GetMapping(value ="/register")
    public String registerPage() {
        try {
            return "register";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "register";
    }

    @PostMapping(value="/adduser")
    public String adduser(@ModelAttribute User user, Model model) {
        if (ss.emailExists(user.getEmail())) {
            String s = "User with the same email already exists";
            model.addAttribute("msg", s);
            return "register";
        }
        model.addAttribute("user", user);
        ss.save(user);
        return "welcome";
    }

    @GetMapping(value="/login")
    public String loginPage(Model model, HttpServletRequest request) {
        // Retrieve the HttpSession object
        HttpSession session = request.getSession();

        // If a session is already established, handle it here
        if (session.getAttribute("user") != null) {
            User username = (User) session.getAttribute("user");
            model.addAttribute("username", username.toString());
            if ("student".equals(username.getRole())) {
                return "student";
            }
            return "trainer";
        }
        return "login";
    }

    @GetMapping(value="/aboutme")
    public String about() {
        return "resume";
    }

    @PostMapping(value="/verify")
    public String verify(@Param("email") String email, @Param("password") String password, Model model, HttpServletRequest request) {
        // Retrieve the HttpSession object
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            User username = (User) session.getAttribute("user");
            model.addAttribute("username", username.toString());
            if ("student".equals(username.getRole())) {
                return "student";
            }
            return "trainer";
        }
     
        

        if (ss.emailExists(email)) {
            User usr = ss.getByEmail(email);
            if (password.equals(usr.getPassword())) {
                String username = usr.getFname() + " " + usr.getLname();
                model.addAttribute("username", username);

                // Store user information in the session
                session.setAttribute("user", usr);

                if ("Student".equals(usr.getRole())) {
                    return "student";
                }
                return "trainer";
            }
        } else {
            model.addAttribute("msg", "User with the above details doesn't exist");
            return "login";
        }
        model.addAttribute("msg", "Wrong Credentials...!!");
        return "login";
    }

    @GetMapping(value="/bk")
    public String back(Model model, HttpServletRequest request) {
        // Retrieve the HttpSession object
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
           User username = (User)session.getAttribute("user");
            model.addAttribute("username", username);
            if ("Student".equals(username.getRole())) {
                return "student";
            }
            return "trainer";
        }
        return "login";
    }

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request) {
        // Retrieve the HttpSession object and invalidate the session
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }
}
