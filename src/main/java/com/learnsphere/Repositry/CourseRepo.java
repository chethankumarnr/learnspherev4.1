package com.learnsphere.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnsphere.Entities.Course;

import java.util.List;
import com.learnsphere.Entities.*;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>
{
	List<Course> findByCreatedBy(User createdBy);
}
