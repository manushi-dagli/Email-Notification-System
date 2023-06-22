package com.springbootbackend.springbootbackend.service;

import java.util.List;
import java.util.Optional;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;
import com.springbootbackend.springbootbackend.exceptions.EmailAlreadyRegistered;


public interface StudentService {
	public List<StudentDetails> getAllStudentDetails();

	public Optional<StudentDetails> getStudent(String studentEmail);

	public StudentDetails addStudent(StudentDetails student) throws EmailAlreadyRegistered;

	public StudentDetails updateStudent(StudentDetails student);

	public void deleteStudent(String studentEmail);
	boolean emailExists(String studentEmail);

}
