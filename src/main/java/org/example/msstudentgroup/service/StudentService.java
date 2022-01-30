package org.example.msstudentgroup.service;

import org.example.msstudentgroup.api.request.NewMarkRequest;
import org.example.msstudentgroup.api.response.StudentGroupResponse;
import org.example.msstudentgroup.model.Student;

import java.util.List;

public interface StudentService {

    void addNewStudent(String studentName);

    StudentGroupResponse addNewMarkToStudent(NewMarkRequest newMarkRequest);

    StudentGroupResponse getStudents();

    StudentGroupResponse getStudentsBySubject(String subjectName);

    StudentGroupResponse makeCSV(String subjectName);

}
