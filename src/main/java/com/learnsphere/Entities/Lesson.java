package com.learnsphere.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int LessonId;
	
	public String topics;
	
	public String link;
	
	@ManyToOne
	public Course course;

	@Override
	public String toString() {
		return "Lesson [LessonId=" + LessonId + ", topics=" + topics + ", link=" + link + ", course=" + course + "]";
	}

	public int getLessonId() {
		return LessonId;
	}

	public void setLessonId(int lessonId) {
		LessonId = lessonId;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Lesson(int lessonId, String topics, String link, Course course) {
		super();
		LessonId = lessonId;
		this.topics = topics;
		this.link = link;
		this.course = course;
	}

	public Lesson() {
		super();
	}
	
	
	

}
