package com.xwj.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwj.mybatis.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll();
    IPage<User> selectByPage(Page page);
}
