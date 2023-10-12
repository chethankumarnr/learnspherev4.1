package com.learnsphere.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnsphere.Entities.Course;
import com.learnsphere.Entities.Lesson;
import java.util.List;


public  interface LessonRepo extends JpaRepository<Lesson,Integer> {
public void deleteByCourse(Course cr);
public List<Lesson> findByCourse(Course course);
}
