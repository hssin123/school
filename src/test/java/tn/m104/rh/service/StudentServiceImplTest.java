package tn.m104.rh.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.m104.rh.dto.StudentDTO;
import tn.m104.rh.entity.Student;
import tn.m104.rh.repository.StudentRepository;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService;

    Student student = new Student(1, "name1", "adress1", 20.00);

    List<Student> listStudents = List.of(
            new Student(2, "name2", "adress2", 30.00),
            new Student(3, "name3", "adress3", 10.00));

    @Test
    void testGetStudents() {
        when(studentRepository.findAll()).thenReturn(listStudents);

        List<StudentDTO> result = studentService.getStudents();

        assertEquals(2, result.size());
        assertEquals("name2", result.get(0).getName());
    }

    @Test
    void testRegisterStudent() {
        StudentDTO dto = new StudentDTO(null, "name1", "adress1", 20.00);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO result = studentService.registerStudent(dto);

        assertEquals(1, result.getRollNumber());
        assertEquals("name1", result.getName());
    }

    @Test
    void testUpdateStudent() {
        StudentDTO dto = new StudentDTO(1, "newName", "newAdress", 18.00);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenAnswer(inv -> inv.getArgument(0));

        StudentDTO result = studentService.updateStudent(dto);

        assertEquals("newName", result.getName());
        assertEquals("newAdress", result.getAddress());
    }

    @Test
    void testUpdateStudentNotFound() {
        StudentDTO dto = new StudentDTO(99, "x", "y", 10.00);
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> studentService.updateStudent(dto));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1);

        verify(studentRepository).deleteById(1);
    }

}
