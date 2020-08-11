package com.tjj.javaSpringBootOne.modules.test.dao;

import com.tjj.javaSpringBootOne.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDao {

    @Select("select * from m_city where country_id =#{countryId}")
    List<City> getCitiesByCountryId(int countryId);
}

