package com.lzh.controller;


import com.lzh.entity.SystemAdmin;
import com.lzh.form.RuleForm;
import com.lzh.form.SearchForm;
import com.lzh.service.SystemAdminService;
import com.lzh.util.ResultVOUtil;
import com.lzh.vo.PageVO;
import com.lzh.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LZH
 * @since 2022-11-24
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.systemAdminService.login(ruleForm);
        return resultVO;
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody SystemAdmin systemAdmin){
        boolean save = this.systemAdminService.save(systemAdmin);
        if(!save){
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageVO pageVO = this.systemAdminService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.systemAdminService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        SystemAdmin systemAdmin = this.systemAdminService.getById(id);
        return ResultVOUtil.success(systemAdmin);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody SystemAdmin systemAdmin){
        boolean update = this.systemAdminService.updateById(systemAdmin);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove = this.systemAdminService.removeById(id);
        if(!remove) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

