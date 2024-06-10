package com.lzh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzh.entity.Teacher;
import com.lzh.form.RuleForm;
import com.lzh.form.SearchForm;
import com.lzh.mapper.TeacherMapper;
import com.lzh.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.vo.PageVO;
import com.lzh.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LZH
 * @since 2022-11-24
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        //1.判断用户名是否存在
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        Teacher teacher = this.teacherMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (teacher == null){
            resultVO.setCode(-1);
        }else {
            //2.判断密码是否正确
            if (!teacher.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(teacher);
            }
        }

        return resultVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Teacher> teacherPage = new Page<>(page, size);
        Page<Teacher> resultPage = this.teacherMapper.selectPage(teacherPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //模糊查询+分页
        Page<Teacher> teacherPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Teacher> resultPage = null;
        //没有给值
        if (searchForm.getValue().equals("")) {
            resultPage = this.teacherMapper.selectPage(teacherPage, null);
        } //有值再做模糊查询
        else {
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());//模糊查询条件
            resultPage = this.teacherMapper.selectPage(teacherPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }
}
