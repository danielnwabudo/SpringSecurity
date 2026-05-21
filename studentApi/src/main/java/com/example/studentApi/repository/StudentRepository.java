package com.example.studentApi.repository;

import com.example.studentApi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameStartingWith(String prefix);
    List<Student> findByNameStartingWithAndAgeGreaterThan(String prefix, int age);
}
