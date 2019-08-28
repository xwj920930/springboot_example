package com.xwj.exception.service;

import com.xwj.exception.config.ExceptionEnum;
import com.xwj.exception.config.LyException;
import com.xwj.exception.entity.Item;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Description 测试业务
 * @Author yuki
 * @Date 2019/4/25 9:17
 * @Version 1.0
 **/
@Service
public class ItemService {
    public Item saveItem(Item item){
        if (item.getPrice()==null||item.getPrice()==0){
            throw new LyException(ExceptionEnum.PRICE_CANNOT_BE_NULL);
        }
        int id= new Random().nextInt(100);
        item.setId(id);
        return item;
    }
}
