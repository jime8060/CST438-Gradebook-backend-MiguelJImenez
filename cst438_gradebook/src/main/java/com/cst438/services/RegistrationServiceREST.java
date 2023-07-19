package com.cst438.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.CourseDTOG;

public class RegistrationServiceREST extends RegistrationService {

	
	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${registration.url}") // ADD Registration URL
	String registration_url;
	
	public RegistrationServiceREST() {
		System.out.println("REST registration service ");
	}
	
	@Override
	public void sendFinalGrades(int course_id , CourseDTOG courseDTO) { 
		
		//TODO  complete this method in homework 4
		
		// Use the RestTemplate to send the final grades to the Registration backend
		System.out.println("Setting final grades for " + course_id + courseDTO);
        restTemplate.put(registration_url + "/course/" + course_id, courseDTO);
        System.out.println("Final grades submitted for course_id: " + course_id);
    }
		
		
}


