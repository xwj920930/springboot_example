package com.example.xwj.mongodb.service;

import com.example.xwj.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     * @param user user
     */
    public void insert(User user){
        mongoTemplate.save(user);
    }

    /**
     * 修改
     * @param user user
     */
    public void update(User user){
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("age", user.getAge()).set("name", user.getName());
        mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 查询
     * @param name name
     * @return User
     */
    public User queryByName(String name){
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 删除
     * @param id id
     */
    public void deleteById(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, User.class);
    }
}
