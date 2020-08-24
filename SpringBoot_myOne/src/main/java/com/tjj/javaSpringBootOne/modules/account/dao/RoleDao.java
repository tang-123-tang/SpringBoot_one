package com.tjj.javaSpringBootOne.modules.account.dao;

import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleDao {
    @Select("select * from role r left join  user_role ur on r.role_id=ur.role_id where ur.user_id=#{userId} ")
    List<Role> getRolesByUserId(int userId);
    @Select("select * from role")
    List<Role> getRoles();
    @Select("<script>" +
            "select * from role "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (role_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by role_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Role> getRoleBySearchVo(SearchVo searchVo);

    @Insert("insert into role(role_name) values" +
            " (#{roleName})")
    @Options(useGeneratedKeys = true,keyProperty = "roleId",keyColumn = "role_id")
    void inserRole( Role role);
    @Select("select * from role where role_name=#{roleName}")
    Role getUserByRoleName(String roleName);
    @Update("update role set role_name=#{roleName} where role_id=#{roleId}")
    void updateRole(Role role);
    @Delete("delete from role where role_id =#{roleId}")
    void deleteRole(int roleId);
    @Select("select * from role where role_id=#{roleId}")
    @Results(id="roleResults", value = {@Result(column = "role_id",property = "roleId"),
            @Result(column = "role_id",property = "resources",
                    javaType = List.class,many = @Many(select ="com.tjj.javaSpringBootOne.modules.account.dao.ResourceDao.getResource" ))})
    Role getRoleByRoleId(int roleId);
}
