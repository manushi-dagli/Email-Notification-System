package com.springbootbackend.springbootbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootbackend.springbootbackend.StudentDetails.StudentDetails;

/**
 * StudentDao interface for handling database operations
 * for StudentDetails entity
 */
@Repository
public interface StudentDao extends JpaRepository<StudentDetails, String>{
    /**
     * Custom query to fetch a student by their email
     */
    @Query("SELECT u FROM StudentDetails u WHERE u.studentEmail = :studentEmail")
    StudentDetails findByEmail(@Param("studentEmail") String studentEmail);
}
