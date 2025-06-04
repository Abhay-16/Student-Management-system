package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Student;

public interface StudentService {

	Integer saveStudent(Student e);
	List<Student> getAllStudent();
	Student getOneStudent(Integer id);
	void deleteStudent(Integer id);
	void updateStudent(Student e);
	
}
