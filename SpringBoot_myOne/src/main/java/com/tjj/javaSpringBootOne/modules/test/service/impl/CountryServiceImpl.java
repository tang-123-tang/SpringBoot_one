package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.tjj.javaSpringBootOne.modules.test.dao.CountryDao;
import com.tjj.javaSpringBootOne.modules.test.entity.Country;
import com.tjj.javaSpringBootOne.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
@Autowired
    CountryDao countryDao;
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
