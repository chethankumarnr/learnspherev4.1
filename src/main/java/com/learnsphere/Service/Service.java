package com.learnsphere.Service;

import com.learnsphere.Entities.User;

public interface Service {
public void save(User u);
public boolean emailExists(String email);
public User findByEmail(String Email);
public User getByEmail(String email);
}
