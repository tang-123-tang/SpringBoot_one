package com.tjj.javaSpringBootOne.modules.test.service;

import com.tjj.javaSpringBootOne.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);
    public Country getCountryByCountryName(String countryName);
}
