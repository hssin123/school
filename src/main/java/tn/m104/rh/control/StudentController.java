package tn.m104.rh.control;

import java.util.List;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.m104.rh.dto.StudentDTO;

import tn.m104.rh.service.IStudentService;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
@CrossOrigin(
		origins = "http://localhost:4200",
		allowedHeaders = {"Content-Type", "Authorization"},
		methods = {RequestMethod.GET, RequestMethod.POST,
				RequestMethod.PUT, RequestMethod.DELETE}
)
public class StudentController {

	private final IStudentService studentService;

	@PostMapping("/registerStudent")
	public StudentDTO registerStudent(@RequestBody StudentDTO student) {
		return studentService.registerStudent(student);
	}

	@GetMapping("/listStudents")
	public List<StudentDTO> getStudents() {
		return studentService.getStudents();
	}

	@DeleteMapping("/deleteStudent")
	public void deleteStudent(@RequestParam Integer id) {
		studentService.deleteStudent(id);
	}

	@PutMapping("/updateStudent")
	public StudentDTO updateStudent(@RequestBody StudentDTO student) {
		return studentService.updateStudent(student);
	}


}
