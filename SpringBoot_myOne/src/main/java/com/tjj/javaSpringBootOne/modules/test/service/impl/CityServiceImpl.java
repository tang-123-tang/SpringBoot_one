package com.tjj.javaSpringBootOne.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.dao.CityDao;
import com.tjj.javaSpringBootOne.modules.test.dao.CountryDao;
import com.tjj.javaSpringBootOne.modules.test.entity.City;
import com.tjj.javaSpringBootOne.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId)).orElse(Collections.emptyList()));

    }

    @Override
    public List<City> selectBySearchVo(SearchVo searchVo) {
        return Optional.of(cityDao.selectBySearchVo(searchVo)).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        cityDao.insertCity(city);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"SUCCESS ABOUT INSERTCITY",city);
    }

    @Override
    @Transactional(noRollbackFor = ArithmeticException.class)//出现ArithmeticException异常可以进行修改
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        //int x=10/0;
        return new Result<>(Result.ResultStatus.SUCCESS.status,"SUCCESS ABOUT UPDATE",city);
    }

    @Override
    @Transactional
    public Result deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result(Result.ResultStatus.SUCCESS.status,"SUCCESS ABOUT DELETE");
    }
}
