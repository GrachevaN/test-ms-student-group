package org.example.msstudentgroup.repository;

import org.example.msstudentgroup.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {


    List<Student> getAll();

    Optional<Student> getByName(String name, int id);

    void addNewStudent(String name);

    void remove(String name);

    void save(Student student);
}
