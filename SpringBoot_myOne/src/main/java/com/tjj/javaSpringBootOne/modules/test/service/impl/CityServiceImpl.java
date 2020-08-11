package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.dao.CityDao;
import com.tjj.javaSpringBootOne.modules.test.dao.CountryDao;
import com.tjj.javaSpringBootOne.modules.test.entity.City;
import com.tjj.javaSpringBootOne.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityDao cityDao;
    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return  cityDao.getCitiesByCountryId(countryId);
    }

    @Override
    public PageInfo<City> getCitiesBy(int countryId,SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList()));
    }
}
