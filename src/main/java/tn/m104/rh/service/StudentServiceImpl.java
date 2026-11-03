package tn.m104.rh.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import tn.m104.rh.dto.StudentDTO;
import tn.m104.rh.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import tn.m104.rh.repository.StudentRepository;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {


	private final StudentRepository studentRepository;

	private StudentDTO toDTO(Student s) {
		return new StudentDTO(s.getRollNumber(), s.getName(),
				s.getAddress(), s.getPercentage());
	}

	@Override
	public StudentDTO registerStudent(StudentDTO dto) {
		Student s = new Student();
		s.setName(dto.getName());
		s.setAddress(dto.getAddress());
		s.setPercentage(dto.getPercentage());
		return toDTO(studentRepository.save(s));
	}

	@Override
	public List<StudentDTO> getStudents() {
		return studentRepository.findAll().stream().map(this::toDTO).toList();
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDTO updateStudent(StudentDTO dto) {
		Student std = studentRepository.findById(dto.getRollNumber())
				.orElseThrow(() -> new EntityNotFoundException(
						"Student introuvable : " + dto.getRollNumber()));
		std.setName(dto.getName());
		std.setAddress(dto.getAddress());
		std.setPercentage(dto.getPercentage());
		return toDTO(studentRepository.save(std));
	}


}
