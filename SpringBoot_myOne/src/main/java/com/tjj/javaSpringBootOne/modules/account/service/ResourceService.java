package com.tjj.javaSpringBootOne.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;

import java.util.List;

public interface ResourceService {
    List<Resource> getResource();
    public PageInfo<Resource> getResourceBySearchVo(SearchVo searchVo);

    public Result<Resource> insertResource(Resource resource);
    public Result<Resource> updateResource(Resource resource);
    public Result<Object> deleteResource(int resourceId);
    public Resource getresourceByresourceId(int resurceId);
}
