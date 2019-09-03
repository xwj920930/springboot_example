package com.xwj.elasticsearch.repository;

import com.xwj.elasticsearch.model.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    //自定义查询
    List<Item> findByPriceBetween(Double begin, Double end);
}
