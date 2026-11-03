package tn.m104.rh.service;

import tn.m104.rh.dto.StudentDTO;


import java.util.List;

public interface IStudentService {


    StudentDTO registerStudent(StudentDTO student);

    List<StudentDTO> getStudents();

    void deleteStudent(Integer id);

    StudentDTO updateStudent(StudentDTO student);


}
