package com.learnsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learnsphere.Entities.User;
import com.learnsphere.Service.EmailService;
import com.learnsphere.Service.ServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession; // Import the standard HttpSession class

@Controller
public class MainController {

    @Autowired
    ServiceImpl ss;
    @Autowired
	private EmailService emailService;

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
    public String adduser(@ModelAttribute User user, Model model,HttpSession session) {
    	
        if (ss.emailExists(user.getEmail())) {
            String s = "User with the same email already exists";
            model.addAttribute("msg", s);
            return "register";
        }
        
        model.addAttribute("user", user);

        int min = 10000; 
        int max = 99999; 

        int otp = (int) (Math.random() * (max - min + 1)) + min;
       session.setAttribute("otp", otp);
       session.setAttribute("user",user);
        emailService.sendEmail(user.getEmail(), "OTP from LearnSphere","Your One Time Password is "+otp);
////        ss.save(user);
        return "otp";
    }

    @PostMapping(value = "/verifyotp")
    public String verifyOtp(@RequestParam("otp") int enteredOtp, HttpSession session, Model model) {
    	if (session.getAttribute("otp")==null){
    		System.out.println("enterted Otp ="+enteredOtp);
    		return "register";
    	}
        int storedOtp = (int) session.getAttribute("otp");
        User user = (User) session.getAttribute("user");
System.out.println("enterted Otp ="+enteredOtp+" "+storedOtp);
        if (enteredOtp == storedOtp) {
            ss.save(user); // User is allowed to register
            model.addAttribute("user", user);
            return "welcome"; // Redirect to the welcome page
        } else {
            String s = "Wrong OTP. Please try again.";
            model.addAttribute("msg", s);
            return "enterotp"; // Redirect back to the OTP entry page
        }
    }
    
    @GetMapping(value="/resetpassword")
    public String resetPassword(Model model) {
    	return "resetpassword";
    }
    
    @PostMapping(value="/resetotp")
    public String resetPassword(@RequestParam("email")String email,Model model,HttpSession session) {
    	System.out.println(email);
    	if (!ss.emailExists(email)) {
            String s = "User with above Email does not exist..Enter Correct Email";
            model.addAttribute("msg", s);
            return "resetpassword";
        }
    	
    	 int min = 10000; 
         int max = 99999; 

         int otp = (int) (Math.random() * (max - min + 1)) + min;
        session.setAttribute("otp2", otp);
        session.setAttribute("user",ss.findByEmail(email));
        emailService.sendEmail(email, "OTP from LearnSphere to Reset your Password","Your One Time Password is "+otp);
         model.addAttribute("email",email);
    	return "resetotp";
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
    
    @PostMapping(value="/verifyotp2")
    public String verify2(@RequestParam("otp")int eotp,HttpSession session, Model model) {
    	int otp2 = (int) session.getAttribute("otp2");
    
    	if(otp2==eotp ) {
    		session.setAttribute("flag","true");
    	return "passwordupdate";
    	}
    	
    	model.addAttribute("msg","WRONG OTP...!! ENTER CORRECT OTP");
    	return "resetotp";
    }
    
    @PostMapping(value="/updatepassword")
    public String verify2(@Param("newpassword") String newPassword, @Param("confirmpassword") String confirmPassword, Model model, HttpSession session) {
        // Retrieve the HttpSession object
    	if(session.getAttribute("flag")==null) {
    		return "resetpassword";
    	}

    	User u = (User) session.getAttribute("user");
    	u.setPassword(confirmPassword);
    	model.addAttribute("msg","Password Upadate Sucess Login with new Credentials");
    	return "login";
    	
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
    
    @GetMapping(value="/sendmail")
    public String sendMail()
    {
    	emailService.sendEmail("chethanchethancng@gmail.com", "Subject", "email-template");
		return "Sent";
    }
    
}
