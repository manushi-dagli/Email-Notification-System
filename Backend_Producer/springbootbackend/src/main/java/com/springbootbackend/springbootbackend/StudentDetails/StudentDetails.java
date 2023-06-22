package com.springbootbackend.springbootbackend.StudentDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "student_details")        //Creates Table in database
public class StudentDetails {
	
		// TODO Auto-generated method stub
		//Primary Key Annotation
		@Id
		@Column(name="student_email")
		private String studentEmail;

		@Column(name="student_name")
		private String studentName;
	    
	    @Column(name="student_contact_number")
		private long studentContactNumber;

		public StudentDetails() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public long getStudentContactNumber() {
			return studentContactNumber;
		}

		public void setStudentContactNumber(long studentContactNumber) {
			this.studentContactNumber = studentContactNumber;
		}

		public String getStudentEmail() {
			return studentEmail;
		}

		public void setStudentEmail(String studentEmail) {
			this.studentEmail = studentEmail;
		}

		public StudentDetails(String studentName, long studentContactNumber, String studentEmail) {
			super();
			this.studentName = studentName;
			this.studentContactNumber = studentContactNumber;
			this.studentEmail = studentEmail;
		}
}
