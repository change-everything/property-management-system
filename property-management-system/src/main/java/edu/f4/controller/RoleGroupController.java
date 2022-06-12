package edu.f4.controller;

import edu.f4.dto.IdListDTO;
import edu.f4.pojo.Permissions;
import edu.f4.pojo.RoleGroup;
import edu.f4.result.Result;
import edu.f4.service.IPermissionsService;
import edu.f4.service.IRoleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleGroupController {

    // 自动注入
    @Autowired
    private IRoleGroupService roleGroupService;

    @PostMapping
    public Result addRole(@RequestBody RoleGroup roleGroup) {
        return Result.ok(roleGroupService.save(roleGroup));
    }

    @PutMapping
    public Result updateRole(@RequestBody RoleGroup roleGroup) {
        return Result.ok(roleGroupService.updateById(roleGroup));
    }

    @DeleteMapping("/{id}")
    public Result delRole(@PathVariable Integer id) {
        return Result.ok(roleGroupService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        RoleGroup roleGroup = roleGroupService.getById(id);
        if (roleGroup == null) {
            return Result.fail("不存在此角色");
        }
        return Result.ok(roleGroup);
    }

    @GetMapping
    public Result queryAll() {
        List<RoleGroup> roleGroupList = roleGroupService.list();
        return Result.ok(roleGroupList);
    }


    @PostMapping("/{roleId}")
    public Result grantAuthorityByRoleId(@RequestBody IdListDTO idListDTO, @PathVariable Integer roleId) {

        roleGroupService.grantAuthorityByRoleId(roleId, idListDTO.getPermIds());
        return Result.ok();
    }

}