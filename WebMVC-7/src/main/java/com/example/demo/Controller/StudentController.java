package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.Entity.Student;
import com.example.demo.Service.StudentService;
import org.springframework.web.bind.annotation.*;


// for postman use
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Add student
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public Student registerStudent(@RequestBody Student student) {
        Integer id = studentService.saveStudent(student);
        student.setStudentId(id); // Set the ID for response
        return student;
    }

    // Get all students
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    // Get single student by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Student getStudentById(@PathVariable Integer id) {
        return studentService.getOneStudent(id);
    }

    // Update student
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return student;
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "Student with ID " + id + " deleted successfully.";
    }
}

//@Controller
//@RequestMapping("/student")
//public class StudentController {
//
//	@Autowired
//	private StudentService studentService;
//	
//	@GetMapping("/register")
//	public String RegisterPage() {
//		return "StudentRegister";
//	}
//	
//	//add Student
//	@PostMapping("/save")
//	public String saveData(@ModelAttribute Student emp, Model m) {
//		
//		Integer id = studentService.saveStudent(emp);
//		String message = "Student " +id+ " Created";
//		m.addAttribute("msg",message);
//		return "StudentRegister";
//	}
//	
//	//view all Student
//	@GetMapping("/all")
//	public String getAllData(@RequestParam(required = false) String message, Model m) {
//		
//		List<Student> lst = studentService.getAllStudent();
//		m.addAttribute("list",lst);
//		m.addAttribute("message", message);
//		return "StudentData";
//	}
//	
//	//edit data
//	@GetMapping("/edit")
//	public String editData(@RequestParam Integer id, Model m) {
//		Student std = studentService.getOneStudent(id);
//		m.addAttribute("student", std);
//		return "StudentEdit";
//	}
//	
//	@PostMapping("/update")
//	public String updateData(@ModelAttribute Student std, RedirectAttributes ra) {
//		studentService.updateStudent(std);
//		ra.addAttribute("message", "Student " +std.getStudentId()+ " Updated!");
//		return "redirect:all";
//	}
//	
////	//delete data
//	@GetMapping("/delete")
//	public String deleteData(@RequestParam Integer id, RedirectAttributes ra) {
//		studentService.deleteStudent(id);
//		ra.addAttribute("message", "Student " +id+ " Deleted!");
//		return "redirect:all";
//		
//	}
//	
//	
//}





