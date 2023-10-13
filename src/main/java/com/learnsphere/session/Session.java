package com.learnsphere.session;

import org.springframework.beans.factory.annotation.Autowired;

import com.learnsphere.Entities.*;

public class Session {
	@Autowired
	 public User cuser;
	
	public Session (long id,String role, String fname,String lname)
	{
	
		cuser = new User();
		cuser.setId(id);
		cuser.setRole(role);
		cuser.setFname(fname);
		cuser.setLname(lname);
		
	}

	public static Session setSession (long id,String role, String fname,String lname)
	{
	return new Session(id,role,fname,lname);
	
	}
	
	
	public  boolean check()
	{
		if(cuser==null)
		return false;
		return true;
		
	}

	
}
