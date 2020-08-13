package com.tjj.javaSpringBootOne.modules.test.service;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;

public interface StudentService {
    Result<Student> insertStudent(Student student);
}
