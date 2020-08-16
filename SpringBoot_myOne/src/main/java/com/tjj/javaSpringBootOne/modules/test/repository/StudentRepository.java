package com.tjj.javaSpringBootOne.modules.test.repository;

import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByStudentName(String studentName);
    List<Student> findByStudentNameLike(String studentName);
    List<Student> findTopByStudentNameLike(String studentName);
    //@Query("select s from Student s where s.studentName=?1 and s.studentCard.cardId=?2")
    //@Query("select s from Student s where s.studentName=:studentName and s.studentCard.cardId=:carId")
   @Query(nativeQuery = true,value = "select * from h_student where student_name=:studetName and card_id=:carId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName, @Param("cardId") int cardId);

}
