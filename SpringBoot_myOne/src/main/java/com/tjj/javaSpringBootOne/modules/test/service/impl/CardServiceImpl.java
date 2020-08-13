package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Card;
import com.tjj.javaSpringBootOne.modules.test.repository.CardRepository;
import com.tjj.javaSpringBootOne.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"Success about insertCard",card);
    }
}
