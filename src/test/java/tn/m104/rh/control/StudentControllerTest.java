package tn.m104.rh.control;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tn.m104.rh.dto.StudentDTO;
import tn.m104.rh.service.IStudentService;


@WebMvcTest(StudentController.class)
 class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    IStudentService studentService;

    @Test
    void testListStudents() throws Exception {
        when(studentService.getStudents()).thenReturn(List.of(
                new StudentDTO(1, "name1", "adress1", 20.00),
                new StudentDTO(2, "name2", "adress2", 30.00)));

        mockMvc.perform(get("/student/listStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("name1"));
    }

    @Test
    void testRegisterStudent() throws Exception {
        StudentDTO input = new StudentDTO(null, "name1", "adress1", 20.00);
        StudentDTO saved = new StudentDTO(1, "name1", "adress1", 20.00);
        when(studentService.registerStudent(any(StudentDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/student/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rollNumber").value(1))
                .andExpect(jsonPath("$.name").value("name1"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentDTO input = new StudentDTO(1, "newName", "newAdress", 18.00);
        when(studentService.updateStudent(any(StudentDTO.class))).thenReturn(input);

        mockMvc.perform(put("/student/updateStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("newName"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/deleteStudent").param("id", "1"))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(1);
    }
}
