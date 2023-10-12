package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.Service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService ss;
	
	
	
	
	
	@GetMapping(value="/buycourses")
	public String buyCourses(Model model,Long id)
	{
	model.addAttribute("course",ss.buyCoures())	;
		return "buycourses";
	}

	@GetMapping(value="/viewlessons/{id}")
	public String viewLesson(Model model,@RequestParam("id") int id)
	{
		model.addAttribute("course",ss.getCourse(id));
		model.addAttribute("lessons",ss.viewLessons(id));
		return "coursedetails";
		
	}
	
	
	@GetMapping(value="/offer")
	public String buyoffer(Model model,Long id)
	{
	return "offer";
	}
	
	
}
