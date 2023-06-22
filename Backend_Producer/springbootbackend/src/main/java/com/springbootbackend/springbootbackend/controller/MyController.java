package com.springbootbackend.springbootbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.exceptions.EmailAlreadyRegistered;
import com.springbootbackend.springbootbackend.service.StudentService;

@RestController
@CrossOrigin("http://localhost:3000")
public class MyController {

	@Autowired
	private StudentService studentService;

	/**
	 *
	 Endpoint for adding new student details.
	 It takes in a StudentDetails object as a request body and checks if the email already exists in the database.
	 If it exists, it returns a response with a message "Email is already in use" and a status code of NOT_ACCEPTABLE.
	 If not, it adds the student details and returns a response with the added student details and a status code of CREATED.
	 This endpoint throws an exception EmailAlreadyRegistered if the email is already in use.
	 */
	@PostMapping("/studentdetails")
	public ResponseEntity<Object> addStudent(@RequestBody StudentDetails student) throws EmailAlreadyRegistered{
		if (studentService.emailExists(student.getStudentEmail())) {
			return new ResponseEntity<>("Email is already in use",HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(studentService.addStudent(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/home")
	public String home() {
		return "This is home";
	}
	
	@GetMapping("/studentdetails")
	public List<StudentDetails> getAllStudentDetails() {
		return this.studentService.getAllStudentDetails();
		
	}
	
	@GetMapping("/studentdetails/{studentEmail}")
	public Optional<StudentDetails> getStudent(@PathVariable String studentEmail) {
		return this.studentService.getStudent(studentEmail);
	}
	
	@PutMapping("/studentdetails")
	public StudentDetails updateStudentDetails(@RequestBody StudentDetails student) {
		return this.studentService.updateStudent(student);
	}
	
	
	@DeleteMapping("/studentdetails/{studentEmail}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable String studentEmail) {
		try {
			this.studentService.deleteStudent(studentEmail);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
