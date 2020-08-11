package com.tjj.javaSpringBootOne.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId);
    PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo);
}
