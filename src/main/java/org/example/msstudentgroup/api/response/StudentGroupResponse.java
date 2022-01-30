package org.example.msstudentgroup.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.msstudentgroup.model.Student;

import java.util.LinkedList;
import java.util.List;

@Data
public class StudentGroupResponse {

    public StudentGroupResponse() {
        this.students = new LinkedList<>();
        result = true;
    }

    private boolean result;

    private List<Student> students;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String subject;

}
