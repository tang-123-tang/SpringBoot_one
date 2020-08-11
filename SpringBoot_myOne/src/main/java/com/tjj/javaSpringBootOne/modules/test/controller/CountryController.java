package com.tjj.javaSpringBootOne.modules.test.controller;

import com.tjj.javaSpringBootOne.modules.test.entity.Country;
import com.tjj.javaSpringBootOne.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    CountryService countryService;

    /**
     *
     * 127.0.0.1/api/country/522  ----get
     */
  @GetMapping("/country/{countryId}")
    Country getCountryByCountryId(@PathVariable  int countryId){
        return countryService.getCountryByCountryId(countryId);

    }

    /**
     * 127.0.0.1/api/country?countryName=China ---get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
      return countryService.getCountryByCountryName(countryName);
    }
}
