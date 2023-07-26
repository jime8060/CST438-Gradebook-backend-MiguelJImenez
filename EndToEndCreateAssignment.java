package com.cst438;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

@SpringBootTest
public class EndToEndCreateAssignment {
	
	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/Users/Miguel/Desktop/CSUMB/chromedriver-win64/chromedriver.exe";

	public static final String URL = "http://localhost:3000";
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static final String TEST_ASSIGNMENT_NAME = "New Test Assignment";
	public static final int TEST_COURSE_ID = 40443;
	public static final int TEST_ASSIGNMENT_ID = 10;
	public static final String TEST_ASSIGNMENT_DUE = "07-25-2023";

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	AssignmentGradeRepository assignnmentGradeRepository;

	@Autowired
	AssignmentRepository assignmentRepository;

	@Test
	public void createAssignmentTest() throws Exception {
		
		Assignment a = assignmentRepository.findByCourseIdAndName(TEST_COURSE_ID, TEST_ASSIGNMENT_NAME);
	    if (a != null) {
	        assignmentRepository.delete(a);
	    }
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		
		// wait of 1 second
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		driver.get(URL);
		Thread.sleep(SLEEP_DURATION);

		try {
			
			// New assignment button
			driver.findElement(By.xpath("//a[last()]")).click();
			Thread.sleep(SLEEP_DURATION);
			
			// Text fields, enter data
			WebElement we = driver.findElement(By.xpath("//input[@name='courseName']"));
			we.sendKeys("40443");
			we = driver.findElement(By.xpath("//input[@name='assignmentName']"));
			we.sendKeys(TEST_ASSIGNMENT_NAME);
			we = driver.findElement(By.xpath("//input[@name='courseId']"));
			we.sendKeys(TEST_ASSIGNMENT_DUE);
			
			
			driver.findElement(By.xpath("//input[@name='submit']")).click();
			Thread.sleep(SLEEP_DURATION);

			//Verification
			Assignment createdAssignment = assignmentRepository.findByCourseIdAndName(TEST_COURSE_ID, TEST_ASSIGNMENT_NAME);
			assertNotNull(createdAssignment); 

		} catch (Exception ex) {
	        throw ex;
	    } finally {
	    	Assignment createdAssignment = assignmentRepository.findByCourseIdAndName(TEST_COURSE_ID, TEST_ASSIGNMENT_NAME);
	        if (createdAssignment != null) {
	            assignmentRepository.delete(createdAssignment);
	        }

	        driver.quit();
	    }

	}

}



