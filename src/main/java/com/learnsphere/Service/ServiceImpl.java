package com.learnsphere.Service;

import org.springframework.beans.factory.annotation.Autowired;

//import com.learnsphere.Entities.Student;
import com.learnsphere.Entities.User;
//import com.learnsphere.Repositry.StudentRepo;
import com.learnsphere.Repositry.UserRepo;


@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	@Autowired
	UserRepo usr;
//	
//	@Autowired
//	StudentRepo sr;



	@Override
	public void save(User u) {
		System.out.println("Error "+ u.toString());
		usr.save(u);
		
//		if(u.getRole().equalsIgnoreCase("student")) {
//		Student s = null;
//		s.id=u.id;
//		sr.save(s);
//		}
	}

@Override
public boolean emailExists(String email) {
	if(usr.existsByEmail(email))
		return true;
return false;
}

@Override
public User findByEmail(String email) {
	User u = usr.findByEmail(email);
	return u;
}


@Override
	public User getByEmail(String email) {
		
		return findByEmail(email) ;
	}
	
}
