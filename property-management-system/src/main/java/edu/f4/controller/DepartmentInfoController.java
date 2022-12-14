package edu.f4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.f4.dto.DeptAndEmpVo;
import edu.f4.pojo.ChargeInfo;
import edu.f4.pojo.DepartmentInfo;
import edu.f4.pojo.OwnerInfo;
import edu.f4.result.Result;
import edu.f4.service.IChargeInfoService;
import edu.f4.service.IDepartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept")
public class DepartmentInfoController {

    // 自动注入
    @Autowired
    private IDepartmentInfoService departmentInfoService;

    @PostMapping
    public Result addDept(@RequestBody DepartmentInfo departmentInfo) {
        return Result.ok(departmentInfoService.save(departmentInfo));
    }

    @PutMapping
    public Result updateDept(@RequestBody DepartmentInfo departmentInfo) {
        return Result.ok(departmentInfoService.updateById(departmentInfo));
    }

    @DeleteMapping("/{deptId}")
    public Result delDept(@PathVariable Integer deptId) {
        return Result.ok(departmentInfoService.deleteDeptById(deptId));
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id){
        DepartmentInfo departmentInfo = departmentInfoService.getById(id);
        if (departmentInfo == null) {
            return Result.fail("不存在此部门信息");
        }
        return Result.ok(departmentInfo);
    }

    @GetMapping
    public Result queryAll() {
        List<DeptAndEmpVo> departmentInfoList = departmentInfoService.findEmpByDeptNum();
        return Result.ok(departmentInfoList);
    }

}
