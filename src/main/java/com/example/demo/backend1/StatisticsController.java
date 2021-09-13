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
	public HashMap<String, Double> activitiesWeek(){
		HashMap<String, Double> toReturn = new HashMap<String,Double>();
		int min = 1;
		int max = 50;
		toReturn.put("totalTime", Math.random()*(100)+max);
		toReturn.put("activityTime", Math.random()*(max-min+1)+min);
		return toReturn;
	}
	
	@GetMapping("getactivitiespermonth")
	public HashMap<String, Double> activitiesMonth(){
		HashMap<String, Double> toReturn = new HashMap<String,Double>();
		int min = 50;
		int max = 100;
		toReturn.put("totalTime",Math.random()*(100)+max);
		toReturn.put("activityTime", Math.random()*(max-min+1)+min);
		return toReturn;
	}
	@GetMapping("getactivitiesperyear")
	public HashMap<String, Double> activitiesYear(){
		HashMap<String, Double> toReturn = new HashMap<String,Double>();
		int min = 100;
		int max = 200;
		toReturn.put("totalTime",Math.random()*(100)+max);
		toReturn.put("activityTime", Math.random()*(max-min+1)+min);
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
		return categoryRepo.getKidsCountByCategory(1);
		}		
	
	@GetMapping("getlistofactivekidspercategorypermonth")	
	public HashMap<String, Integer> activeKidsCategMonth(){		
		return categoryRepo.getKidsCountByCategory(2);	
		}		
	@GetMapping("getlistofactivekidspercategoryperyear")	
	public HashMap<String, Integer> activeKidsCategYear(){	
		return categoryRepo.getKidsCountByCategory(3);	
		}	
	@GetMapping("getkidsbycategorymonth/{category}")	
	public TreeMap<Integer,Integer> kidsByCategoryMonth(@PathVariable String category){	
		return kidRepo.getKidsCategoryMonth(category);	
		}
}
