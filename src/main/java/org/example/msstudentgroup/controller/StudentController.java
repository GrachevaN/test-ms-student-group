package org.example.msstudentgroup.controller;

import org.example.msstudentgroup.api.request.NewMarkRequest;
import org.example.msstudentgroup.api.response.StudentGroupResponse;
import org.example.msstudentgroup.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping("/addStudent/{studentName}")
    public ResponseEntity<?> addStudent(@PathVariable String studentName) {
        studentService.addNewStudent(studentName);
        return ResponseEntity.ok(studentName);
    }

    @PostMapping("/addMark")
    public ResponseEntity<?> addNewMarkToStudent(
            @RequestBody NewMarkRequest newMarkRequest
    ) {
        StudentGroupResponse response = studentService.addNewMarkToStudent(newMarkRequest);
        if (!response.isResult()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{subject}")
    public ResponseEntity<?> getStudentBySubject(
            @PathVariable String subject) {
        StudentGroupResponse response = studentService.getStudentsBySubject(subject);
        if (!response.isResult()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/makeCSV")
    public ResponseEntity<?> makeCSV (
            @RequestParam(defaultValue = "") String subject
    ) {
        StudentGroupResponse response = studentService.makeCSV(subject);
        if (!response.isResult()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return ResponseEntity.ok(response);
        }
    }

}
