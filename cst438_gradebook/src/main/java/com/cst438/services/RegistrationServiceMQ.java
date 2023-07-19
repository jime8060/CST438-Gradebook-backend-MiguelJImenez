package com.cst438.services;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cst438.domain.Course;
import com.cst438.domain.CourseDTOG;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;


public class RegistrationServiceMQ extends RegistrationService {

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public RegistrationServiceMQ() {
		System.out.println("MQ registration service ");
	}

	// ----- configuration of message queues

	@Autowired
	Queue registrationQueue;


	// ----- end of configuration of message queue

	// receiver of messages from Registration service
	
	@RabbitListener(queues = "gradebook-queue")
	@Transactional
	public void receive(EnrollmentDTO enrollmentDTO) {
		
		//TODO 
		// Check given course_id exists in the database.
	    Course course = courseRepository.findById(enrollmentDTO.getCourse_id())
	            .orElseThrow(() -> new IllegalArgumentException("Course not found"));

	    // Create a new Enrollment based on received EnrollmentDTO.
	    Enrollment enrollment = new Enrollment();
	    enrollment.setCourse(course);
	    //enrollment.setStudentName(enrollmentDTO.getStudentName());
	    //enrollment.setStudentEmail(enrollmentDTO.getStudentEmail());

	    // Save new enrollment 
	    enrollmentRepository.save(enrollment);
		
	}

	// sender of messages to Registration Service
	@Override
	public void sendFinalGrades(int course_id, CourseDTOG courseDTO) {
		 
		//TODO  
		// Get course with given course_id.
	    Course course = courseRepository.findById(course_id)
	            .orElseThrow(() -> new IllegalArgumentException("Course not found"));

	    // Prepare the EnrollmentDTO containing final grades.
	    EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
	    enrollmentDTO.setCourse_id(course.getCourse_id());
	    enrollmentDTO.setCourse_name(course.getName());
	    enrollmentDTO.setInstructor(course.getInstructor());
	    enrollmentDTO.setStudentName(courseDTO.getStudentName());
	    enrollmentDTO.setStudentEmail(courseDTO.getStudentEmail());
	    enrollmentDTO.setGrade(courseDTO.getGrade());

	    // Send the final grades to the registration backend using RabbitTemplate.
	    rabbitTemplate.convertAndSend(registrationQueue.getName(), enrollmentDTO);
	}
		

}
