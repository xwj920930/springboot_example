package com.xwj.exception.entity;

/**
 * @Description 测试实体
 * @Author yuki
 * @Date 2019/4/25 9:49
 * @Version 1.0
 **/
public class Item {
    private Integer id;
    private String name;
    private Long price;

    public Item() {
    }

    public Item(int id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
