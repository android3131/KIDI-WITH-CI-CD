package com.example.demo.backend1;

import java.util.List;

import com.example.demo.Kid;
import com.example.demo.Meeting;

public class ResponseObjHomeLoggedIn {
	List<Kid> kidsList;
	List<Meeting> meetingList;
	
	public ResponseObjHomeLoggedIn(List<Kid> kidsList, List<Meeting> meetingList) {
		super();
		this.kidsList = kidsList;
		this.meetingList = meetingList;
	}
	
	
}
