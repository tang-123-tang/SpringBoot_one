package com.tjj.javaSpringBootOne.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.service.ResourceService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {
   @Autowired
    ResourceService service;
   @GetMapping("/resources")
    public List<Resource> getResource(){
        return service.getResource();
    }
    @PostMapping(value = "/resources",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Resource> getResourceBySearchVo(@RequestBody SearchVo searchVo) {
        return service.getResourceBySearchVo(searchVo);
    }

    @PostMapping(value = "/resource",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> insertResource(@RequestBody Resource resource) {
        return service.insertResource(resource);
    }

    @PutMapping(value = "/resource",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> updateResource(@RequestBody Resource resource) {
        return service.updateResource(resource);
    }

    @DeleteMapping("/resource/{resourceId}")
    public Result<Object> deleteResource(@PathVariable int resourceId) {
        return service.deleteResource(resourceId);
    }

    @GetMapping("/resource/{resourceId}")
    public Resource getRoleByRoleId(@PathVariable int resourceId) {
        return service.getresourceByresourceId(resourceId);
    }


}
