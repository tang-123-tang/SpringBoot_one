package com.tjj.springcloud.springCloudClientAccount.modules.account.service;

import com.tjj.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.tjj.springcloud.springCloudClientAccount.modules.account.service.impl.TestFeighFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "CLIENT-TEST",fallback = TestFeighFallBack.class)
public interface TestFeignClient {
    @RequestMapping("/api/cities/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId);
}
