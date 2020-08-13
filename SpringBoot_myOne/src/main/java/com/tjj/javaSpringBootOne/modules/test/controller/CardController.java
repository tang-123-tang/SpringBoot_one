package com.tjj.javaSpringBootOne.modules.test.controller;

import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.test.entity.Card;
import com.tjj.javaSpringBootOne.modules.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardService cardService;

    /**
     * 127.0.0.1/api/card
     * {"cardNo":"dldkjd"}
     * @param card
     * @return
     */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card) {
        return cardService.insertCard(card);
    }
}
