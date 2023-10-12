package com.learnsphere.Repositry;



import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.Entities.User;


public interface UserRepo extends JpaRepository<User,Long> {
	public boolean existsByEmail(String email);
	public User findByEmail(String Email);
	public User getByEmail(String email);
}


