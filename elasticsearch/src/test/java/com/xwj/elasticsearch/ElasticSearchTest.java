package com.xwj.elasticsearch;

import com.xwj.elasticsearch.model.Item;
import com.xwj.elasticsearch.repository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchTest {
    @Autowired
    ElasticsearchTemplate template;
    @Autowired
    ItemRepository repository;

    @Test//创建索引
    public void testCreat() {
        //创建索引
        template.createIndex(Item.class);
        //映射
        template.putMapping(Item.class);
    }
    @Test//删除索引
    public void testDelete() {
        //删除索引
        template.deleteIndex(Item.class);
    }
    @Test//存储文档
    public void index() {
        Item item = new Item(1L, "小米手机7", " 手机", "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        repository.save(item);
    }
    @Test//存储文档集
    public void indexList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        repository.saveAll(list);
    }
    @Test//查询所有文档
    public void testFindAll(){
        Iterable<Item> all = repository.findAll();
        all.forEach(System.out::println);
    }
    @Test//自定义方法
    public void testFindByPrice(){
        List<Item> items = repository.findByPriceBetween(3000D, 4000D);
        items.forEach(System.out::println);
    }
    @Test//复杂查询
    public void testQuery(){
        //创建查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //结果过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id","title","price"},null));
        //添加查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("title","小米手机"));
        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //分页，初始页码居然从零开始
        queryBuilder.withPageable(PageRequest.of(0,2));
        //结果
        Page<Item> result = repository.search(queryBuilder.build());
        long elements = result.getTotalElements();
        System.out.println("elements="+elements);
        int pages = result.getTotalPages();
        System.out.println("pages = " + pages);
        List<Item> items = result.getContent();
        items.forEach(item -> System.out.println("item = " + item));
    }
    @Test
//聚合，需要根据原生json的结构分析
//输入	{
//			"aggs": {
//		"popularBrand": {
//			"terms": {
//				"field": "brand",
//			}
//	输出"aggregations": {
//		"popularBrand": {
//			"doc_count_error_upper_bound": 0,
//					"sum_other_doc_count": 0,
//					"buckets": [
//			{
//				"key": "华为",
//					"doc_count": 1
//			}
    public void testAgg(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        String aggName = "popularBrand";
        //聚合，名称popularBrand，字段brand
        queryBuilder.addAggregation(AggregationBuilders.terms(aggName).field("brand"));
        //查询
        AggregatedPage<Item> result = template.queryForPage(queryBuilder.build(), Item.class);
        //解析
        Aggregations aggs = result.getAggregations();
        //获取指定的聚合，默认返回Aggregation，但是分析json和类结构(F4)发现需要
        StringTerms terms = aggs.get(aggName);
        //获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println("bucket.getKey() = " + bucket.getKeyAsString());
            System.out.println("bucket.getDocCount() = " + bucket.getDocCount());
        }
    }
}
