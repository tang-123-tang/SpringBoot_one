package com.tjj.javaSpringBootOne.modules.test.controller;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import com.tjj.javaSpringBootOne.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 127.0.0.1/api/student
     * {"studentName":"gfdssdfg","studentCard":{"cardId":"1"}}
     * @param student
     * @return
     */
    @PostMapping(value = "/student",consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }
}
