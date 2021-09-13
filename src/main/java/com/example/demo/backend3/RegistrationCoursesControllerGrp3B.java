package com.example.demo.backend3;


import java.sql.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Category;
import com.example.demo.CategoryRepository;
import com.example.demo.Course;
import com.example.demo.CourseRepository;
import com.example.demo.Kid;
import com.example.demo.KidRepository;
import com.example.demo.Parent_repository;
import com.example.demo.Validation;


@RestController
public class RegistrationCoursesControllerGrp3B {
	@Autowired
	private CategoryRepository catRepository;
	@Autowired
	private Parent_repository parRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private KidRepository kidRepository;
	@Field
	Validation validate;

	//For the screen: first registration
	/**
	 * Getting all the categories. 
	 * @return list of all categories
	 */
	@GetMapping("/getAllCategories")  
	public List<Category>  getAllAvailableCategories(){
		return catRepository.getAllCategories();
	}
	
	/**
	 * Getting all the courses that belong to the wanted category. 
	 * * @param parent id
	 * @return list of all courses that belong to the wanted category
	 */
	@GetMapping("/getCoursesOfCategory/{categoryid}") //CHECKED!!!
	public List<Course> retrieveCoursesOfCategory(@PathVariable String categoryid) {
		return courseRepository.getCategoryCourses(categoryid);
	}
	//For the screens: Add activity
	
	/**
	 * Getting all the kids registered under the user of the parent. 
	 * @param parentId
	 * @return list of all kids registered under the user of the parent. 
	 */
	@GetMapping("/getAllParentsChildren/{parentId}")  
	public List<Kid>  getAllParentsChildren(@PathVariable String parentId){
		return parRepository.GetAllKidsOfParent(parentId);
	}

	
	/**
	 * Getting all categories that the child is still not active at
	 * * * @param parent id, child id
	 * @return list of all categories with Available courses
	 */
	@GetMapping("/getCategoriesForNewActivity/{parentId}/{kidId}")  
	public List<Category>  getAvailableCategoriesForChild(@PathVariable String parentId, @PathVariable String kidId){
		return parRepository.getKidNotRegisteredCategories(parentId, kidId);
	}
	
	/**
	 * Getting all the courses that the kid not active at by category.
	 * * @param parent id, child id, category id
	 * @return all the courses that the kid not active at by category.
	 */
	@GetMapping("/getCoursesForNewActivity/{parentId}/{kidId}/{catId}")
	public List<Course> getAvailibleCoursesForChild(@PathVariable String parentId, @PathVariable String kidId, @PathVariable String catId) {
		return parRepository.getKidNotRegisteredCoursesByCategory(parentId, kidId, catId);
	}

	/**
	 * Adding a course to the list of active courses of the kid
	 * * @param parent id, kid id, course id
	 * @return the kid if found, or null
	 */
	@PostMapping("/addCourseToChild/{parentId}/{kidId}/{courseId}") 
	public Boolean updateChildsCourses(@PathVariable String parentId, @PathVariable String kidId, @PathVariable String courseId) {
		if(parRepository.getAllParents().contains(parRepository.getParentById(parentId)) 
				&& kidRepository.getAllKids().contains(kidRepository.getKidWithId(kidId))
				&& courseRepository.getAllCourses().contains(courseRepository.getASpecificCourse(courseId))) {
				System.out.println("yes");
				parRepository.addKidToCourse(parentId, kidId, courseId);
				return true;
		}
		return false;
	}

	
	//------courses : Admin------
	
	//For screen: New course
	/**
	 * crating a new course
	 * * @param course
	 * @return true id added, false if not
	 */
	@PostMapping("/createNewCourse")
	public String createCourse(@RequestBody Course course) {
		return courseValidations("add", course);
	}
	
	/**
	 * getting all the hours
	 * @return all hours of the day
	 */
	@GetMapping("/hours")
	public ResponseEntity<List<String>> getAllHours()
	{
		List<String> lst = new ArrayList<String>();
		String s="";
		for(int i=0;i<24;i++)
		{
			s=String.format("%02d:00",i);
			lst.add(s);
		}
		return new ResponseEntity<List<String>> (lst, HttpStatus.OK);
	}
	
	//For screen: Set courses
	/**
	 * getting all the existing courses
	 * @return all courses
	 */
	@GetMapping("/getAllCourses")
	public List<Course> getAllCourses() {
		return courseRepository.getAllCourses();
	}
	
	/**
	 * updating the finishDate to today's date, if the user clicks on delete
	 * * @param course id
	 * @return true if the update was successful. if not, return false
	 */
	//When deleting the course, we are actually just updating the finish date
	@PutMapping("/deleteCourse/{courseId}")
	public Boolean updateFinishDate(@PathVariable String courseId) {
		if(courseRepository.getAllCourses().contains(courseRepository.getASpecificCourse(courseId))) {
			return courseRepository.updateFinishedDateByDelete(courseId);
			
		}
		return false;
	}
	
	//For screen: Update course
	
	/**
	 * returns the zoom link of a specific course
	 * * @param course id
	 * @return zoom link of the course
	 */
	@GetMapping("/getCourseZoomLink/{CourseId}") 
	public String getCourseLink(@PathVariable String courseId) {
		return courseRepository.getASpecificCourse(courseId).getZoomMeetingLink();
	}
	
	@PutMapping("/updateCourse") 
	public String updateCourse(@RequestBody Course course) {
		return courseValidations("update", course);
	}
	
	public String courseValidations(String action, Course course) {
		String msg = "";
		if (action.equals("add")) {
			msg = "The course was " + action + "ed successfully";
		}
		else {
			msg = "The course was " + action + "d successfully";
		}
		Time tFinish = new Time(course.getFinishDateTime().getTime());
		Time tStart = new Time(course.getStartDateTime().getTime());
		if (!(catRepository.getAllCategories().contains(catRepository.getCategoryById(course.getCategoryId())))) {
			msg= "Failed to " + action + ". Category doesn't exist";
			return msg;
		}
		if(tFinish.before(tStart)) {
			msg= "Failed to " + action + ". The finish time is before the start time.";
			return msg;
		}
		if(course.getStartDateTime().after(course.getFinishDateTime())) {
			msg= "Failed to " + action + ". The end date is before the start date.";
			return msg;
		}
		if(course.getStartDateTime().before(new Date()) ) {
			msg= "Failed to " + action + ". The start date is in the past.";
			return msg;
		}
		if (action.equals("add")) {
			courseRepository.addANewCourse(course);
		}
		else {
			courseRepository.updateCourse(course);
		}
		if (!courseRepository.getAllCourses().contains(course)) {
			msg= "Failed to " + action + ". The course name already exists";
			return msg;
		}
		return msg;
	}
	
}