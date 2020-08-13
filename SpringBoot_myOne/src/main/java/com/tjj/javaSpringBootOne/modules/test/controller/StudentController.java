package com.tjj.javaSpringBootOne.modules.test.controller;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import com.tjj.javaSpringBootOne.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 127.0.0.1/api/student/2
     * @param studentId
     * @return
     */
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
        return studentService.getStudentByStudentId(studentId);
    }

    /**
     * 127.0.0.1/api/students
     * {"currentPage":"1","pageSize":"5","keyWord":"张","orderBy":"studentName","sort":"desc"}
     * @param searchVo
     * @return
     */
    @PostMapping(value = "/students",consumes = "application/json")
    public Page<Student> getStudentBySearchVo(@RequestBody SearchVo searchVo) {
        return  studentService.getStudentBySearchVo(searchVo);
    }
}
