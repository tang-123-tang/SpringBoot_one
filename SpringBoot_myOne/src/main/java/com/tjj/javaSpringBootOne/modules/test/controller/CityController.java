package com.tjj.javaSpringBootOne.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import com.tjj.javaSpringBootOne.modules.test.entity.City;
import com.tjj.javaSpringBootOne.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    CityService cityService;

    /**
     *
     *  127.0.0.1/api/cities/522
     */
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable  int countryId){
      return   cityService.getCitiesByCountryId(countryId);
    }

    /**
     *
     * 127.0.0.1/api/cities/522
     * {"currentPage":"1","pageSize":"5"}
     */
    @PostMapping(value="/cities/{countryId}",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@PathVariable  int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId,searchVo);
    }

    /**
     * 127.0.0.1/api/cities
     *  {"currentPage":"1","pageSize":"5","keyWord":"Sh","orderBy":"city_name","order":"desc"}
     * @param searchVo
     * @return
     */
    @PostMapping(value = "/cities",consumes = "application/json")
    List<City> selectBySearchVo(@RequestBody SearchVo searchVo){
        return cityService.selectBySearchVo(searchVo);
    }
  /**
   * 127.0.0.1/api/city
   * {"cityName":"wangwang","localCityName":"sdfd","countryId":522}
   */
    @PostMapping(value = "/city",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city) {
        return cityService.insertCity(city);
    }
    @PutMapping(value = "/city",consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city) {
        return cityService.updateCity(city);
    }

    /**
     * 127.0.0.1/api/city/2261
     * @param cityId
     * @return
     */
   @DeleteMapping("/city/{cityId}")
    public Result deleteCity(@PathVariable int cityId) {
        return  cityService.deleteCity(cityId);
    }
}
