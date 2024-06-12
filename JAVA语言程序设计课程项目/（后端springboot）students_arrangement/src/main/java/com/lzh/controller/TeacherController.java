package com.lzh.controller;


import com.lzh.entity.Teacher;
import com.lzh.form.RuleForm;
import com.lzh.form.SearchForm;
import com.lzh.service.TeacherService;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/login")
    public ResultVO login(RuleForm ruleForm){
        ResultVO resultVO = this.teacherService.login(ruleForm);
        return resultVO;
    }

    @PostMapping("/save")
    public ResultVO save(@RequestBody Teacher teacher){
        boolean save = this.teacherService.save(teacher);
        if(!save){
            return ResultVOUtil.fail();
        } else {
            return ResultVOUtil.success(null);
        }
    }

    @GetMapping("/list/{page}/{size}")
    public ResultVO list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageVO pageVO = this.teacherService.list(page, size);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/search")
    public ResultVO search(SearchForm searchForm){
        PageVO pageVO = this.teacherService.search(searchForm);
        return ResultVOUtil.success(pageVO);
    }

    @GetMapping("/findById/{id}")
    public ResultVO findById(@PathVariable("id") Integer id){
        Teacher teacher = this.teacherService.getById(id);
        return ResultVOUtil.success(teacher);
    }

    @PutMapping("/update")
    public ResultVO update(@RequestBody Teacher teacher){
        boolean update = this.teacherService.updateById(teacher);
        if(!update) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id){
        boolean remove = this.teacherService.removeById(id);
        if(!remove) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

