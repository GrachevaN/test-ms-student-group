package org.example.msstudentgroup.service;


import org.example.msstudentgroup.api.request.NewMarkRequest;
import org.example.msstudentgroup.api.response.StudentGroupResponse;
import org.example.msstudentgroup.model.Student;
import org.example.msstudentgroup.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    public StudentServiceImpl(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public void addNewStudent(String studentName) {
        studentRepo.addNewStudent(studentName);
    }

    @Override
    public StudentGroupResponse addNewMarkToStudent(NewMarkRequest newMarkRequest) {
        Optional<Student> optionalStudent = studentRepo.getByName(newMarkRequest.getStudentName(), newMarkRequest.getStudentId());
        StudentGroupResponse response = new StudentGroupResponse();
        boolean error = true;
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Integer> marks = new ArrayList<>();
            if (student.getSubjectList().containsKey(newMarkRequest.getSubjectName())) {
                 marks = student.getSubjectList().get(newMarkRequest.getSubjectName());
            }
            if (newMarkRequest.getMark() >= 2 && newMarkRequest.getMark() <= 5) {
                marks.add(newMarkRequest.getMark());
                student.getSubjectList().put(newMarkRequest.getSubjectName(), marks);
                error = false;
            }
        }

        response.setResult(error);
        response.setStudents(studentRepo.getAll());

        return response;
    }

    @Override
    public StudentGroupResponse getStudents() {
        StudentGroupResponse response = new StudentGroupResponse();
        response.setStudents(studentRepo.getAll());
        return response;
    }

    @Override
    public StudentGroupResponse getStudentsBySubject(String subjectName) {
        List<Student> students = studentRepo.getAll()
                .stream().filter(student -> student.getSubjectList().containsKey(subjectName) == true)
                .collect(Collectors.toList());
        StudentGroupResponse response = makeGroupResponse(students);
        if (students.isEmpty()) {
            response.setResult(false);
        }
        response.setSubject(subjectName);
        return response;
    }

    @Override
    public StudentGroupResponse makeCSV(String subjectName) {
        StudentGroupResponse response = new StudentGroupResponse();
        List<Student> students = studentRepo.getAll();
        try (PrintWriter writer = new PrintWriter("src/main/resources/report_" + LocalDateTime.now().getNano() + ".csv")) {


            Set<String> subjects = new TreeSet<>(Comparator.naturalOrder());
            students.forEach(student -> {
                        student.getSubjectList().keySet().forEach
                                (s -> subjects.add(s));
                    }
            );
//
            if (subjects.contains(subjectName)) {
                subjects.clear();
                subjects.add(subjectName);
            }

            StringBuilder sb = new StringBuilder();

            sb.append("Student name");

            for (String s: subjects) {
                sb.append(',');
                sb.append(s);
            }
            sb.append('\n');

            students.forEach(student -> {
                sb.append(student.getName());
                for (String s: subjects) {
                    sb.append(',');
                    if (student.getSubjectList().containsKey(s)) {
                        List<Integer> marks = student.getSubjectList().get(s);
                        int total = (marks.stream().mapToInt(Integer::intValue).sum()) / marks.size();
                        sb.append(total);
                    }
                    else {
                        sb.append('-');
                    }

                }
                sb.append('\n');
            });

            writer.write(sb.toString());
            response.setResult(true);


        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            response.setResult(false);
        }
        response = makeGroupResponse(students);
        return response;
    }

    private StudentGroupResponse makeGroupResponse(List<Student> students) {
        StudentGroupResponse response = new StudentGroupResponse();
        response.setStudents(students);
        return response;
    }

}
