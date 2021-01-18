package com.example.xwj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 1.Spring的默认的事务规则是遇到运行异常（RuntimeException及其子类）和程序错误（Error）才会进行事务回滚，
 * 可以使用rollbackFor = {Exception.class} 来解决这个问题
 * 2.被catch处理了的异常，不会被事物作为判断依据
 * 3.阿里推荐的自动提交/手动回滚
 */
public class UserService {
    @Autowired
    DataSourceTransactionManager transactionManager;

    /**
     * 自动回滚.
     */
    @Transactional
    public void save(){
        throw new RuntimeException();
    }

    /**
     * 自动提交/手动回滚.
     */
    @Transactional(rollbackFor = {Exception.class})
    public void save2(){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //do SomeThing
        }catch (Exception e){
            transactionManager.rollback(status);
        }

        //替代
//        try {
//            //do SomeThing
//        }catch (Exception e){
//            throw e;
//        }
    }
}
