package tn.m104.rh.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.m104.rh.entity.Student;
import tn.m104.rh.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentService ;

    Student student = new Student(1, "name1","adress1", 20.00);
    List<Student> listStudents = new ArrayList<Student>() {
        {
            add(new Student(2, "name2","adress2", 30.00));
            add(new Student(3, "name3","adress3", 10.00));
        }
    };

    // TDD : Test Driven Development : Developpement dirigé par les Tests

    @Test
    @Order(2)
    public void testGetStudents() {

        Mockito.when(studentRepository.findAll()).thenReturn(listStudents);

        List<Student> listU = studentService.getStudents();

        Assertions.assertEquals(2, listU.size());
    }

}
