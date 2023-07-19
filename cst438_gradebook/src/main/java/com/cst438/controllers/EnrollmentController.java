<<<<<<< HEAD
package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;

@RestController
public class EnrollmentController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	/*
	 * endpoint used by registration service to add an enrollment to an existing
	 * course.
	 */
	@PostMapping("/enrollment")
	@Transactional // if anything goes wrong it will backtrack
	public EnrollmentDTO addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
		
		//TODO
		// Find the course using course_id from the enrollmentDTO
//        Course course = courseRepository.findById(enrollmentDTO.course_id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//
//        // Create a new Enrollment entity using attributes
//        Enrollment enrollment = new Enrollment();
//        enrollment.setCourse(course);
//        enrollment.setStudentName(enrollmentDTO.studentName);
//        enrollment.setStudentEmail(enrollmentDTO.studentEmail);
//
//        // Save the enrollment 
//        enrollmentRepository.save(enrollment);
//
//        // Convert the saved enrollment to EnrollmentDTO and return it
//        return convertToEnrollmentDTO(enrollment);
//    }
//
//    // Helper to convert Enrollment to EnrollmentDTO
//    private EnrollmentDTO convertToEnrollmentDTO(Enrollment enrollment) {
//        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
//        enrollmentDTO.course_id = enrollment.getCourse().getId();
//        enrollmentDTO.studentName = enrollment.getStudentName();
//        enrollmentDTO.studentEmail = enrollment.getStudentEmail();
//        return enrollmentDTO;
//    }
		
		
		//return null;
		
		// TODO USING PROF. CODE
		Enrollment e = new Enrollment();
		e.setStudentEmail(enrollmentDTO.studentEmail);
		e.setStudentName(enrollmentDTO.studentName);
		Course c = courseRepository.findById(enrollmentDTO.course_id).orElse(null);
		if (c==null) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Course id not found.");
		}
		e.setCourse(c);
		e = enrollmentRepository.save(e);
		enrollmentDTO.id = e.getId();
		return enrollmentDTO;
	}
		

}
=======
package com.cst438.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;

@RestController
public class EnrollmentController {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	/*
	 * endpoint used by registration service to add an enrollment to an existing
	 * course.
	 */
	@PostMapping("/enrollment")
	@Transactional // if anything goes wrong it will backtrack
	public EnrollmentDTO addEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
		
		//TODO
		// Find the course using course_id from the enrollmentDTO
//        Course course = courseRepository.findById(enrollmentDTO.course_id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
//
//        // Create a new Enrollment entity using attributes
//        Enrollment enrollment = new Enrollment();
//        enrollment.setCourse(course);
//        enrollment.setStudentName(enrollmentDTO.studentName);
//        enrollment.setStudentEmail(enrollmentDTO.studentEmail);
//
//        // Save the enrollment 
//        enrollmentRepository.save(enrollment);
//
//        // Convert the saved enrollment to EnrollmentDTO and return it
//        return convertToEnrollmentDTO(enrollment);
//    }
//
//    // Helper to convert Enrollment to EnrollmentDTO
//    private EnrollmentDTO convertToEnrollmentDTO(Enrollment enrollment) {
//        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
//        enrollmentDTO.course_id = enrollment.getCourse().getId();
//        enrollmentDTO.studentName = enrollment.getStudentName();
//        enrollmentDTO.studentEmail = enrollment.getStudentEmail();
//        return enrollmentDTO;
//    }
		
		
		//return null;
		
		// TODO USING PROF. CODE
		Enrollment e = new Enrollment();
		e.setStudentEmail(enrollmentDTO.studentEmail);
		e.setStudentName(enrollmentDTO.studentName);
		Course c = courseRepository.findById(enrollmentDTO.course_id).orElse(null);
		if (c==null) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Course id not found.");
		}
		e.setCourse(c);
		e = enrollmentRepository.save(e);
		enrollmentDTO.id = e.getId();
		return enrollmentDTO;
	}
		

}
>>>>>>> a9f6d8a600772c69533ec8aa3c36162d05eda249
