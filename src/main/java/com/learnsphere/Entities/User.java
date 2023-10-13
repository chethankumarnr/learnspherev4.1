package com.learnsphere.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "learnsphereusers")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long id;

@Column(name = "FirstName")
 String fname;

@Column(name="LastName")
String lname;

 String email;
public String mobileno;
public String role;
public String password;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobileno() {
	return mobileno;
}
public void setMobileno(String mobileno) {
	this.mobileno = mobileno;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public User(Long id, String fname, String lname, String email, String mobileno, String role, String password) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.email = email;
	this.mobileno = mobileno;
	this.role = role;
	this.password = password;
}

public User() {
	super();
}

@Override
public String toString() {
	return this.fname +" "+this.lname;
}

}
