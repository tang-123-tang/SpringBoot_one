package com.tjj.javaSpringBootOne.modules.test.service;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Result<Student> insertStudent(Student student);
    public Student getStudentByStudentId(int studentId);
    Page<Student> getStudentBySearchVo(SearchVo searchVo);
    public List<Student> getStudentsByStudentName(String studentName, int cardId);
}
