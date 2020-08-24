package com.tjj.javaSpringBootOne.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.tjj.javaSpringBootOne.modules.account.entity.Role;
import com.tjj.javaSpringBootOne.modules.account.entity.User;
import com.tjj.javaSpringBootOne.modules.account.service.RoleService;
import com.tjj.javaSpringBootOne.modules.common.vo.Result;
import com.tjj.javaSpringBootOne.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 127.0.0.1/api/roles
     * @return
     */
    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
    /**
     * 127.0.0.1/api/roles ---post
     * {"currentPage":"1","pageSize":"5","keyWord":"role_name"}
     * @param searchVo
     * @return
     */
    @PostMapping(value = "/roles",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getRoleBySearchVo(@RequestBody  SearchVo searchVo) {
        return roleService.getRoleBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/role---post
     * {"roleName":"曹军"}
     */
    @PostMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertUser(@RequestBody Role role) {
        return roleService.insertRole(role);
    }


    /**
     * 127.0.0.1/api/role  ----put
     * {"roleName":"weed"}
     * @param role
     * @return
     */
    @PutMapping(value = "/update1",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    /**
     * 127.0.0.1/api/user/12
     * @param roleId
     * @return
     */
    @DeleteMapping("/role/{roleId}")
    public Result<Object> deleteRole(@PathVariable int roleId) {
        return roleService.deletRole(roleId);
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId) {
        return roleService.getRoleByRoleId(roleId);
    }
}
