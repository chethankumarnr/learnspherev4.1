package com.learnsphere.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.Entities.Course;
import com.learnsphere.Entities.Lesson;
import com.learnsphere.Repositry.CourseRepo;
import com.learnsphere.Repositry.LessonRepo;

@Service
public class StudentService {

//	@Autowired
//	StudentRepo sr;
	
	@Autowired
	CourseRepo cr;
	
	@Autowired
	LessonRepo lr;
	
	
	@Autowired
	TraineerServiceImpl cs;
	
	public List<Course> buyCoures()
	{
		return cr.findAll();
	}

	public List<Lesson> viewLessons(int id) {
		
		
		return lr.findByCourse(cr.findById(id).get());
		
	}
	
	
	public Course getCourse(int id) {
		
		
		return cr.findById(id).get();
		
	}
}
