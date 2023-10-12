package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.Entities.Course;
import com.learnsphere.Entities.Lesson;
import com.learnsphere.Entities.User;
import com.learnsphere.Repositry.UserRepo;
import com.learnsphere.Service.TraineerServiceImpl;
import com.learnsphere.session.Session;

@Controller
public class TraineerController {
@Autowired
TraineerServiceImpl cs;

@Autowired
UserRepo ur;

	@GetMapping(value="/addcourse")
	public String addCourse()
	{
		return "addcourse";
	}
	
	@PostMapping(value="/addcoursetodb")
	public String addcourseDB(@ModelAttribute Course course,Model model)
	{
		course.createdBy(Session.cuser)	;
		cs.addCourse(course);
		model.addAttribute("msg", "Course Added Successfully...");
		return "addcourse";
	}
	
	@PostMapping(value="/addcoursetodbviaview")
	public String addcourseDB2(@ModelAttribute Course course,Model model)
	{
		course.createdBy(Session.cuser)	;
		cs.addCourse(course);
		model.addAttribute("msg", "Course Added Successfully...");
		return "redirect:/viewcourse";
	}
	
	@GetMapping(value="/addlesson")
	public String addLesson(Model model)
	{
		User usr = ur.findById(Session.getId()).orElse(null);
		model.addAttribute("course",cs.getCourses(usr));
		return "addlesson";
	}
	
	
	@PostMapping(value="/addlessontodb")
	public String addlessonDB(@ModelAttribute Lesson lesson,Model model)
	{
		//lesson.course= cs.getCourseById(4);
		cs.addLessson(lesson);
		model.addAttribute("msg","Lesson Added Sucessfully..");
		return "redirect:/addlesson";	
	}

	@GetMapping(value = "/viewcourse")
	public String getAllCourses(Model model) {
		
		User usr = ur.findById(Session.getId()).orElse(null);
		model.addAttribute("course",cs.getCourses(usr));
	    return "viewcourse";
	}
	
	@GetMapping(value="update/changes/{id}")
	public String updateget(Model model,@RequestParam("id") int id)
	{
		System.out.println(id);
		Course course = cs.getCourseById(id);
		model.addAttribute("course", course);
		 return"update";
		
	}
	
	@PutMapping(value="/update")
	public String update(Model model,@ModelAttribute Course cr)
	{
		Course co = cs.getCourseById(cr.getId());
		co.setCoursename(cr.getCoursename());
		co.setId(cr.getId());
		co.setPrice(cr.getId());
		cs.addCourse(co);
		model.addAttribute("msg","Update Sucess..");
		return"update";
	}
	
	@DeleteMapping(value="/deletecourse")
	public String delete(@RequestParam("id")int id)
	{
		System.out.println("workim");
	
		cs.delete(id);
		return "redirect:viewcourse";
	}
	
	
}
