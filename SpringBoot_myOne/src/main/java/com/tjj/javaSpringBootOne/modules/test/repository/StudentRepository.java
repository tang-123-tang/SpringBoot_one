package com.tjj.javaSpringBootOne.modules.test.repository;

import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
