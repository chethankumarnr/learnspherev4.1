package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.learnsphere.Entities.Course;
import com.learnsphere.Entities.Lesson;
import com.learnsphere.Entities.User;
import com.learnsphere.Repositry.UserRepo;
import com.learnsphere.Service.TraineerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession; // Import the standard HttpSession class

@Controller
public class TraineerController {

    @Autowired
    TraineerServiceImpl cs;

    @Autowired
    UserRepo ur;

    @GetMapping(value = "/addcourse")
    public String addCourse() {
        return "addcourse";
    }

    @PostMapping(value = "/addcoursetodb")
    public String addcourseDB(@ModelAttribute Course course, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User username = (User) session.getAttribute("user");
        course.createdBy(username);
        cs.addCourse(course);
        model.addAttribute("msg", "Course Added Successfully...");
        return "addcourse";
    }

    @PostMapping(value = "/addcoursetodbviaview")
    public String addcourseDB2(@ModelAttribute Course course, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User username = (User) session.getAttribute("user");
        course.createdBy(username);
        cs.addCourse(course);
        model.addAttribute("msg", "Course Added Successfully...");
        return "redirect:/viewcourse";
    }

    @GetMapping(value = "/addlesson")
    public String addLesson(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        model.addAttribute("course", cs.getCourses(usr));
        return "addlesson";
    }

    @PostMapping(value = "/addlessontodb")
    public String addlessonDB(@ModelAttribute Lesson lesson, Model model) {
        cs.addLessson(lesson);
        model.addAttribute("msg", "Lesson Added Successfully..");
        return "addlesson";
    }

    @GetMapping(value = "/viewcourse")
    public String getAllCourses(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        model.addAttribute("course", cs.getCourses(usr));
        return "viewcourse";
    }

    @GetMapping(value = "update/changes/{id}")
    public String updateget(Model model, @RequestParam("id") int id) {
        System.out.println(id);
        Course course = cs.getCourseById(id);
        model.addAttribute("course", course);
        return "update";
    }

    @PostMapping(value = "/update")
    public String update(Model model, @ModelAttribute Course cr) {
    	
    	System.out.println(cr.getId()+cr.getCoursename()+" 1 Working----------------------------------------------------------");
        Course co = cs.getCourseById(cr.getId());
        System.out.println("2 Working");
        co.setCoursename(cr.getCoursename());
        System.out.println("3 Working");
       
        System.out.println("4 Working");
        co.setPrice(cr.getId());
        System.out.println("5 Working");
        cs.addCourse(co);
        System.out.println("6 Working");
        model.addAttribute("msg", "Update Success..");
        System.out.println("7 Working");
        return "update";
    }

    @DeleteMapping(value = "/deletecourse")
    public String delete(@RequestParam("id") int id) {
        System.out.println("working");
        cs.delete(id);
        return "redirect:viewcourse";
    }
}
