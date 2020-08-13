package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import com.tjj.javaSpringBootOne.modules.test.repository.StudentRepository;
import com.tjj.javaSpringBootOne.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Success about InsertStudent",student);
    }
}
