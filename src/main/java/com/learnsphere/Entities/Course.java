package com.learnsphere.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;

String coursename;

int price;

@ManyToOne
User createdBy;

@OneToMany
List<Lesson> lessons;

public Course(int id, String coursename, int price, List<Lesson> lessons) {
	super();
	this.id = id;
	this.coursename = coursename;
	this.price = price;
	this.lessons = lessons;
}

public Course() {
	super();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCoursename() {
	return coursename;
}

public void setCoursename(String coursename) {
	this.coursename = coursename;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public void createdBy(User user) {
	this.createdBy = user;
}

public List<Lesson> getLessons() {
	return lessons;
}

public void setLessons(List<Lesson> lessons) {
	this.lessons = lessons;
}

@Override
public String toString() {
	return "Course [id=" + id + ", coursename=" + coursename + ", price=" + price + ", lessons=" + lessons + "]";
}



}
