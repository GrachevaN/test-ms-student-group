package org.example.msstudentgroup.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewMarkRequest {

    private String studentName;
    private int mark;
    private String subjectName;
    private int studentId;




}
