package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.tjj.javaSpringBootOne.modules.test.dao.CountryDao;
import com.tjj.javaSpringBootOne.modules.test.entity.Country;
import com.tjj.javaSpringBootOne.modules.test.service.CountryService;
import com.tjj.javaSpringBootOne.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
@Autowired
    CountryDao countryDao;
@Autowired
    RedisTemplate redisTemplate;
@Autowired
    RedisUtils redisUtils;
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountryByReids(int countryId) {
        Country country=countryDao.getCountryByCountryId(countryId);
        String countryKey=String.format("country%d",countryId);
/*
         redisTemplate.opsForValue().set(countryKey,country);

        return (Country)redisTemplate.opsForValue().get(countryKey);*/
        redisUtils.set(countryKey,country);
        return (Country)redisUtils.get(countryKey);
    }
}
