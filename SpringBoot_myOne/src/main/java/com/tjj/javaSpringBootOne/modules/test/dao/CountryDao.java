package com.tjj.javaSpringBootOne.modules.test.dao;

import com.tjj.javaSpringBootOne.modules.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
@Mapper
public interface CountryDao {
    @Select("select * from m_country where country_id =#{countryId}")
    @Results(id = "CountryList",value = {
            @Result(column = "country_id",property = "countryId"),
            @Result(column = "country_id",property = "cities",
                    javaType = List.class,
                    many = @Many(select = "com.tjj.javaSpringBootOne.modules.test.dao.CityDao.getCitiesByCountryId")
            )
    })
    Country getCountryByCountryId(int countryId);
    @Select("select * from m_country where country_name =#{countryName}")
    @ResultMap(value="CountryList")
    public Country getCountryByCountryName(String countryName);

}
