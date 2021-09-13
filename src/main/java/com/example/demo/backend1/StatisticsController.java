package com.example.demo.backend1;

import java.util.HashMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CategoryRepository;
import com.example.demo.KidRepository;
import com.example.demo.MeetingRepository;
import com.example.demo.Parent_repository;

@RestController
public class StatisticsController {

	@Autowired
	MeetingRepository meetingRepo;
	@Autowired
	KidRepository kidRepo;
	@Autowired
	Parent_repository parentRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@GetMapping("getactivitiesperweek")
	public HashMap<String, Integer> activitiesWeek(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 1;
		int max = 50;
		toReturn.put("totalTime", (int)(Math.random()*(100)+max));
		toReturn.put("activityTime", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	
	@GetMapping("getactivitiespermonth")
	public HashMap<String, Integer> activitiesMonth(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 50;
		int max = 100;
		toReturn.put("totalTime", (int)(Math.random()*(100)+max));
		toReturn.put("activityTime", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	@GetMapping("getactivitiesperyear")
	public HashMap<String, Integer> activitiesYear(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 100;
		int max = 200;
		toReturn.put("totalTime", (int)(Math.random()*(100)+max));
		toReturn.put("activityTime", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}


	@GetMapping("getlistofactivekidsperweek")
	public HashMap<String, Integer> activeKidsWeek(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 1;
		int max = 50;
		toReturn.put("totalKids", (int)(Math.random()*(100)+max));
		toReturn.put("newKids",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	
	@GetMapping("getlistofactivekidspermonth")
	public HashMap<String, Integer> activeKidsMonth(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 50;
		int max = 100;
		toReturn.put("totalKids", (int)(Math.random()*(100)+max));
		toReturn.put("newKids",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	@GetMapping("getlistofactivekidsperyear")
	public HashMap<String, Integer> activeKidsYear(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 100;
		int max = 200;
		toReturn.put("totalKids", (int)(Math.random()*(100)+max));
		toReturn.put("newKids",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}

	@GetMapping("getallactiveparentsbyweek")
	public HashMap<String, Integer> activeParentsWeek(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 1;
		int max = 50;
		toReturn.put("totalParents", (int)(Math.random()*(100)+max));
		toReturn.put("newParents",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	
	@GetMapping("getallactiveparentsbymonth")
	public HashMap<String, Integer> activeParentsMonth(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 50;
		int max = 100;
		toReturn.put("totalParents", (int)(Math.random()*(100)+max));
		toReturn.put("newParents",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	
	@GetMapping("getallactiveparentsbyyear")
	public HashMap<String, Integer> activeParentsYear(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 100;
		int max = 200;
		toReturn.put("totalParents", (int)(Math.random()*(100)+max));
		toReturn.put("newParents",(int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}
	
	// -------- get active kids in category per week/month/year -------- 	
	@GetMapping("getlistofactivekidspercategoryperweek")	
	public HashMap<String, Integer> activeKidsCategWeek(){
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 1;
		int max = 5;
		toReturn.put("art", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("music", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("sience", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("animal", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("space", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("poetry", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
		}		
	
	@GetMapping("getlistofactivekidspercategorypermonth")	
	public HashMap<String, Integer> activeKidsCategMonth(){		
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 5;
		int max = 10;
		toReturn.put("art", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("music", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("sience", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("animal", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("space", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("poetry", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}		
	@GetMapping("getlistofactivekidspercategoryperyear")	
	public HashMap<String, Integer> activeKidsCategYear(){	
		HashMap<String, Integer> toReturn = new HashMap<String,Integer>();
		int min = 10;
		int max = 30;
		toReturn.put("art", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("music", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("sience", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("animal", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("space", (int)(Math.random()*(max-min+1)+min));
		toReturn.put("poetry", (int)(Math.random()*(max-min+1)+min));
		return toReturn;
	}	
	@GetMapping("getkidsbycategorymonth/{category}")	
	public TreeMap<Integer,Integer> kidsByCategoryMonth(@PathVariable String category){	
		return kidRepo.getKidsCategoryMonth(category);	
	}
}
