package com.tjj.javaSpringBootOne.modules.test.controller;

import com.github.pagehelper.PageInfo;
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
}
