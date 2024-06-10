package com.lzh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzh.entity.SystemAdmin;
import com.lzh.form.RuleForm;
import com.lzh.form.SearchForm;
import com.lzh.mapper.SystemAdminMapper;
import com.lzh.service.SystemAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.vo.PageVO;
import com.lzh.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LZH
 * @since 2022-11-24
 */
@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {

    @Autowired
    private SystemAdminMapper systemAdminMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        //1.判断用户名是否存在
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",ruleForm.getUsername());
        SystemAdmin systemAdmin = this.systemAdminMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (systemAdmin == null){
            resultVO.setCode(-1);
        }else {
            //2.判断密码是否正确
            if (!systemAdmin.getPassword().equals(ruleForm.getPassword())) {
                resultVO.setCode(-2);
            } else {
                resultVO.setCode(0);
                resultVO.setData(systemAdmin);
            }
        }

        return resultVO;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<SystemAdmin> systemAdminPage = new Page<>(page, size);
        Page<SystemAdmin> resultPage = this.systemAdminMapper.selectPage(systemAdminPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        //模糊查询+分页
        Page<SystemAdmin> systemAdminPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<SystemAdmin> resultPage = null;
        //没有给值
        if(searchForm.getValue().equals("")){
            resultPage = this.systemAdminMapper.selectPage(systemAdminPage, null);
        } //有值再做模糊查询
        else {
            QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());//模糊查询条件
            resultPage = this.systemAdminMapper.selectPage(systemAdminPage, queryWrapper);
        }
        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(resultPage.getRecords());
        return pageVO;
    }
}
