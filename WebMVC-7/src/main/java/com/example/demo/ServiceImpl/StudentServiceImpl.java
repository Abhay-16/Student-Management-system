 package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;
import com.example.demo.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepository;
	
	@Override
	public Integer saveStudent(Student e) {
		// TODO Auto-generated method stub
		return studentRepository.save(e).getStudentId();
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student getOneStudent(Integer id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get();
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}

	@Override
	public void updateStudent(Student e) {
		// TODO Auto-generated method stub
		studentRepository.save(e);
	}

}
