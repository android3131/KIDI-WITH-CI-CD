package com.example.demo.backend2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Admin;
import com.example.demo.Admin_Repository;
import com.example.demo.Category;
import com.example.demo.CategoryRepository;
import com.example.demo.Course;
import com.example.demo.CourseRepository;
import com.example.demo.Leader;
import com.example.demo.LeaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class AdminController {
    @Autowired
    LeaderRepository LeaderRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    Admin_Repository adminRepo;

    /**
     * @param categoryID
     * @return array list of leader in given category
     * */

    @GetMapping("/getLeaderByCategory/{categoryID}")
    public ArrayList<Leader> getLeadersByCategoryID(@PathVariable String categoryID){
    	return LeaderRepository.getCategoryLeaders(categoryID);
    }

    /**
     * @param leaderID
     * @param courseID
     * @return boolean variable if leader was added to given
     * course of id or not
     * */
    @PutMapping("/LeaderCourse/{courseID}/{leaderID}")
    public ResponseEntity<Boolean> AddLeaderToCourse(@PathVariable String courseID , @PathVariable String leaderID){
    	List<Course> allCourses = courseRepository.getAllCourses();
    	Course course = courseRepository.getASpecificCourse(courseID);
    	List<Leader> allLeaders = LeaderRepository.getAllLeaders();
    	Optional<Leader> leader1 = LeaderRepository.getASpecificLeader(leaderID);
    	Leader leader2 = leader1.get();
        if(leaderID.isEmpty() || courseID.isEmpty() || !allCourses.contains(course) || !allLeaders.contains(leader2)) {
        	return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        Boolean b = courseRepository.addLeaderToCourse(courseID,leaderID);
        if(!b)
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);

        return new ResponseEntity<Boolean>(b, HttpStatus.OK);
    }

    /**
     * @param courseID
     * @param leaderID
     * @return response if leader was removed from given course
     * */
    @PutMapping("/LeaderCourseRemove/{courseID}/{leaderID}")
    public ResponseEntity<Boolean> RemoveLeaderFromCourse(@PathVariable String courseID , @PathVariable String leaderID){
    	List<Course> allCourses = courseRepository.getAllCourses();
    	Course course = courseRepository.getASpecificCourse(courseID);
    	List<Leader> allLeaders = LeaderRepository.getAllLeaders();
    	Optional<Leader> leader1 = LeaderRepository.getASpecificLeader(leaderID);
    	Leader leader2 = leader1.get();
        if(leaderID.isEmpty() || courseID.isEmpty() || !allCourses.contains(course) || !allLeaders.contains(leader2))
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);

        Boolean b = courseRepository.removeLeaderCourse(courseID,leaderID);
        if(!b)
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);

        return new ResponseEntity<Boolean>(b, HttpStatus.OK);
    }


    /**
     * @param courseName
     * @return list of leaders of given course name
     * */
    @GetMapping("/getLeadersBysCourseName/{courseName}")
    public Object getLeadersByCourseName(@PathVariable String courseName) {
        if(courseName==null)
            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_ACCEPTABLE);
        courseName = courseName.toLowerCase();
        ArrayList<String> my_leaders = courseRepository.getCourseLeadersByName(courseName);
        if(my_leaders.isEmpty())
            return new ResponseEntity<Leader>((Leader) null, HttpStatus.NOT_FOUND);
        else
            return my_leaders;
    }

    /**
     * @return array list of all categories
     * */
    @GetMapping("/categories/getCategories")
    public ArrayList<Category> getAllCategories(){
        return categoryRepository.getAllCategories();
    }


    @GetMapping("/sendEmail")
    public static ResponseEntity<String> sendEmail(){
        try{
            SendEmailToClient.setEmail("ahmed.jabareen1@gmail.com");
            System.out.println("Hello!");
            return new ResponseEntity<String>("mail sent successfully",HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error! :(");
            return new ResponseEntity<String>("and error has occured",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/getAllAdmins")
    public List<Admin> getAllActiveadmins(){
    	return adminRepo.getAllActiveadmins();
    }
   @PostMapping("/addAdmin")
    public void addNewAdmin(Admin admin){
	   adminRepo.addNewAdmin(admin);
    }

}
