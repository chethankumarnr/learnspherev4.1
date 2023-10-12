package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.learnsphere.Entities.*;
import com.learnsphere.Service.ServiceImpl;
import com.learnsphere.session.*;

@Controller
public class MainController {

	@Autowired
	ServiceImpl ss;

	@GetMapping(value ="/register")
	public String registerPage()
	{
		try {
			return "register";

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "register";
	}


	@PostMapping(value="/adduser")
	public String adduser(@ModelAttribute  User user, Model model)
	{
		if(ss.emailExists(user.getEmail())) {
			String s = "User with the same email already exists";
			model.addAttribute("msg",s);
			return "register";
		}
		model.addAttribute("user",user);
		ss.save(user);
		return "welcome";
	}

	@GetMapping(value="/login")
	public String loginPage(Model model)
	{
//		if(Session.check()==true)
//		{
//			String username=Session.cuser.getFname()+" "+Session.cuser.getLname();
//			model.addAttribute("username",username);
//		if(Session.cuser.role=="student")
//			return"student";
//			return "trainer";
//		}
		return"login";
	}
	@GetMapping(value="/aboutme")
	public String about()
	{
		return"resume";
	}

	@PostMapping(value="/verify")
	public String verify(@Param("email") String email, @Param("password") String password,Model model) {
		
		if(Session.check()==true)
		{
			String username=Session.cuser.getFname()+" "+Session.cuser.getLname();
			model.addAttribute("username",username);
		if(Session.cuser.role=="student")
			return"student";
			return "trainer";
		}
		
		if(ss.emailExists(email))
		{
			User usr = ss.getByEmail(email);
			if(password.equals(usr.getPassword()))
			{
				String username=usr.getFname()+" "+usr.getLname();
				model.addAttribute("username",username);
				Session.setSession(usr.id,usr.role,usr.getFname(),usr.getLname());
				if(usr.getRole().equals("Student"))
					return "student";
				return "trainer";
			}
		}
		else {
			model.addAttribute("msg","User with the above details doesn't exist");
			return "login";
		}
		model.addAttribute("msg","Wrong Credentials...!!");
		return "login";
	}
	@GetMapping(value="/bk")
	public String back(Model model) {
		if(Session.check()==true)
		{
			String username=Session.cuser.getFname()+" "+Session.cuser.getLname();
			model.addAttribute("username",username);
		if(Session.cuser.role=="student")
			return"student";
			return "trainer";
		}
		return "login";
	}
	
	@GetMapping(value="/logout")
	public String logout()
	{
		Session.cuser=null;
		return "/login";
		
	}
	
}
