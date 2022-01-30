package org.example.msstudentgroup.repository;

import org.example.msstudentgroup.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private List<Student> students = new LinkedList<>();
    private static int studentCounter;

    public StudentRepositoryImpl() {
        studentCounter = 0;
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public Optional<Student> getByName(String name, int id) {
        Optional<Student> optionalStudent = students.stream()
                .filter(s -> (s.getName().equals(name) && s.getStudentId() == id)).findFirst();
        return optionalStudent;
    }


    @Override
    public void addNewStudent(String studentName) {
        Student student = new Student(studentCounter);
        studentCounter++;
        student.setName(studentName);
        students.add(student);
        Collections.sort(students, (o1, o2) -> o1.getName().compareTo(o2.getName()));
    }

    @Override
    public void remove(String studentName) {
        students.removeIf(student -> student.getName().equals(studentName));
    }

    @Override
    public void save(Student student) {
        if (students.contains(student)) {

        }
    }

    public void getAllSubjects() {
        Set<String> subjects = new HashSet<>();
                students.forEach(student -> {
                   student.getSubjectList().keySet().forEach
                           (s -> subjects.add(s));
            }
        );
    }

    public List<Student> getStudentFilteredBySubject(String subject) {
        List<Student> studentList = new LinkedList<>();
                studentList = students.stream()
                .filter(student -> student.getSubjectList().keySet().contains(subject))
                .collect(Collectors.toList());
        return studentList;
    }


}
