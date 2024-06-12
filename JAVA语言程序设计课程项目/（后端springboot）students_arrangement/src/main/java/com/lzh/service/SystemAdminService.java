package com.lzh.service;

import com.lzh.entity.SystemAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.form.RuleForm;
import com.lzh.form.SearchForm;
import com.lzh.vo.PageVO;
import com.lzh.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LZH
 * @since 2022-11-24
 */
public interface SystemAdminService extends IService<SystemAdmin> {
    public ResultVO login(RuleForm ruleForm);
    public PageVO list(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
}
