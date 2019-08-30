package com.xwj.mybatis.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwj.mybatis.mapper.UserMapper;
import com.xwj.mybatis.pojo.User;
import com.xwj.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper,User> implements UserService{
    @Override
    public int insertUser(User user) {
        return baseMapper.insert(user);//baseMapper自带方法
    }

    @Override
    public boolean insertUsers(List<User> users) {
        return saveBatch(users);//serviceImpl自带方法
    }

    @Override
    public User selectByAge(int age){
        return baseMapper.selectOne(new QueryWrapper<User>()
                .eq("age",age));//使用queryWrapper
    }

    @Override
    public List<User> selectAll() {
        return baseMapper.selectAll();//使用xml
    }

    @Override//自带分页
    public IPage<User> selectByPage1(int page) {
        return baseMapper.selectPage(new Page<>(page,2),null);
    }

    @Override//自定义分页，xml
    public IPage<User> selectByPage2(int page) {
        return baseMapper.selectByPage(new Page(page,2));
    }
}
