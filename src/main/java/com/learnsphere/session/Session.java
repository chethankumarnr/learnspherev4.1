package com.learnsphere.session;

import com.learnsphere.Entities.*;

public class Session {
	
	static public User cuser;
	
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
	
	
	public static boolean check()
	{
		if(cuser==null||cuser.id==null)
		return false;
		return true;
		
	}

	public static Long getId() {
		// TODO Auto-generated method stub
		return cuser.id;
	}
}
