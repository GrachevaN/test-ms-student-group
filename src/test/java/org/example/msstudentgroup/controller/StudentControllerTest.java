package org.example.msstudentgroup.controller;

import org.example.msstudentgroup.api.response.StudentGroupResponse;
import org.example.msstudentgroup.model.Student;
import org.example.msstudentgroup.repository.StudentRepository;
import org.example.msstudentgroup.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class StudentControllerTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void getStudents() {

        Student student = new Student(0);
        student.setName("Maksimov");
        List<Student> students = new LinkedList<>();
        students.add(student);

        when(studentRepository.getAll()).thenReturn(students);

        StudentGroupResponse expected = new StudentGroupResponse();
        expected.setStudents(students);
        StudentGroupResponse actual = studentService.getStudents();

        assertEquals(expected, actual);

    }
}