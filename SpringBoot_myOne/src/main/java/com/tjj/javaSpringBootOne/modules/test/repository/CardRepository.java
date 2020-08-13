package com.tjj.javaSpringBootOne.modules.test.repository;

import com.tjj.javaSpringBootOne.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
