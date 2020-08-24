package com.tjj.javaSpringBootOne.modules.account.dao;

import com.tjj.javaSpringBootOne.modules.account.entity.Resource;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceDao {


    @Select("select * from resource r  left join role_resource  rr on r.resource_id=rr.resource_id where rr.role_id=#{roleId}")
    List<Resource> getResource( int roleId);
    @Select("select * from resource ")
    List<Resource> getResources();

    @Select("select * from resource r left join  role_resource ur on r.resource_id=ur.resource_id where ur.role_id=#{roleId} ")
    List<Resource> getResourcesByResourceId(int roleId);
    @Select("<script>" +
            "select * from resource "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (resource_id like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by resource_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> getResourceBySearchVo(SearchVo searchVo);

    @Insert("insert into resource(resource_name,permission,resource_uri) values" +
            " (#{resourceName},#{permission},#{resourceUri})")
    @Options(useGeneratedKeys = true,keyProperty = "resourceId",keyColumn = "resource_id")
    void inserResource(Resource resource);
    @Select("select * from resource where resource_name=#{resourceName}")
    Resource getRoleByresourceNameName(String resourceName);
    @Update("update resource set resource_name=#{resourceName},permission=#{permission},resource_uri=#{resourceUri} where resource_id=#{resourceId}")
    void updateResource(Resource resource);
    @Delete("delete from resource where resource_id =#{resourceId}")
    void deleteResource(int resourceId);
    @Select("select * from resource where resource_id=#{resourceId}")
    @Results(id="resourceResults", value = {@Result(column = "resource_id",property = "resourceId"),
            @Result(column = "resource_id",property = "roles",
                    javaType = List.class,many = @Many(select ="com.tjj.javaSpringBootOne.modules.account.dao.RoleDao.getRoles" ))})
    Resource getresourceByresourceId(int resourceId);
}
