package com.learnsphere.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsphere.Entities.Course;
import com.learnsphere.Entities.Lesson;
import com.learnsphere.Entities.User;
import com.learnsphere.Repositry.CourseRepo;
import com.learnsphere.Repositry.LessonRepo;

@Service
public class TraineerServiceImpl implements TraineerService {
@Autowired
CourseRepo cr;

@Autowired
LessonRepo lr;




public void addCourse(Course course)
{
	cr.save(course);
	
}

public void addLessson(Lesson lesson) {
	
	lr.save(lesson);
}


public List<Course> getCourses(User user)
{
	
	return cr.findByCreatedBy(user);
	
}

public Course getCourseById(int courseid) {
	return cr.findById(courseid).get();
	
}

@Transactional
public void delete(int id) {
	try {
			lr.deleteByCourse(cr.findById(id).get());
			cr.delete(cr.findById(id).get());
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	
}





}
