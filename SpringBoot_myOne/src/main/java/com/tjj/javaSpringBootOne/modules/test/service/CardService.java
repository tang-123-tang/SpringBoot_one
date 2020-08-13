package com.tjj.javaSpringBootOne.modules.test.service;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Card;

public interface CardService {
    Result<Card> insertCard(Card card);
}
