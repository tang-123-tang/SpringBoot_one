package com.tjj.javaSpringBootOne.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleResourceDao {
    @Delete("delete from role_resource where role_id=#{roleId}")
    void deleteRoleResourceByRoleId(int roleId);
    @Insert("insert into role_resource(role_id,resource_id) values" +
            "(#{roleId},${resourceId})")
    void insertUserRole(int roleId,int resourceId);
    @Delete("delete from role_resource where resource_id=#{resourceId}")
    void deleteRoleResourceByResourceId(int resourceId);
}
