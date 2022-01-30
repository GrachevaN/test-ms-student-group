package org.example.msstudentgroup.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Student {

    private String name;
    private Map<String, List<Integer>> subjectList;
    private int studentId;

    public Student(int studentId) {
        subjectList = new HashMap<>();
        this.studentId = studentId;
    }

}
