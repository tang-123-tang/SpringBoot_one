package com.tjj.javaSpringBootOne.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.dao.ResourceDao;
import com.tjj.javaSpringBootOne.modules.account.dao.RoleResourceDao;
import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.service.ResourceService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    @Override
    public List<Resource> getResource() {
        return Optional.of( resourceDao.getResources()).orElse(Collections.emptyList());
    }


    @Override
    public PageInfo<Resource> getResourceBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());

        return new PageInfo<Resource>(Optional.ofNullable(resourceDao.getResourceBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<Resource> insertResource(Resource resource) {
        Resource resourceTemp=resourceDao.getRoleByresourceNameName(resource.getResourceName());
        if(resourceTemp!=null){
            return  new Result<Resource>(Result.ResultStatus.FAILED.status,"Resource name is repeat");
        }
        roleResourceDao.deleteRoleResourceByResourceId(resource.getResourceId());
       // List<Resource> resources=resource.get();
        resourceDao.inserResource(resource);

        return new  Result<Resource>(Result.ResultStatus.SUCCESS.status,"添加成功",resource);
    }

    @Override
    @Transactional
    public Result<Resource> updateResource(Resource resource) {
        Resource resourceTemp=resourceDao.getRoleByresourceNameName(resource.getResourceName());
        if(resourceTemp!=null &&resourceTemp.getResourceId()!=resource.getResourceId()){
            return  new Result<Resource>(Result.ResultStatus.FAILED.status,"Resource name is repeat");
        }
        resourceDao.updateResource(resource);
       // roleResourceDao.deleteRoleResourceByResourceId(resource.getResourceId());
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"update resource success ");
    }


    @Override
    @Transactional
    public Result<Object> deleteResource(int resourceId) {
        resourceDao.deleteResource(resourceId);
        roleResourceDao.deleteRoleResourceByResourceId(resourceId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delte resource success");
    }

    @Override
    public Resource getresourceByresourceId(int resurceId) {
        return resourceDao.getresourceByresourceId(resurceId);
    }
}
