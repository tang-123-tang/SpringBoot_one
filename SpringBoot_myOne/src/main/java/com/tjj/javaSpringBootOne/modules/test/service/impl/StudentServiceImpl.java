package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import com.tjj.javaSpringBootOne.modules.test.repository.StudentRepository;
import com.tjj.javaSpringBootOne.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @Override
    public Student getStudentByStudentId(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> getStudentBySearchVo(SearchVo searchVo) {
        Sort.Direction direction="desc".equalsIgnoreCase(searchVo.getSort())?Sort.Direction.DESC:Sort.Direction.ASC;
        String orderBy= StringUtils.isBlank(searchVo.getOrderBy())?"studentId":searchVo.getOrderBy();
        Sort sort=new Sort(direction,orderBy);
        Pageable pageable= PageRequest.of(searchVo.getCurrentPage()-1,searchVo.getPageSize(),sort);
        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
                .withMatcher("studentName",con->con.contains())
                .withIgnorePaths("studentId");
        Student student=new Student();
        student.setStudentName(searchVo.getKeyWord());
        Example<Student> example=Example.of(student,exampleMatcher);
        return studentRepository.findAll(example,pageable);
    }
}
