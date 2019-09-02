package com.xwj.excel.jxls.model;

import lombok.Data;

import java.util.List;

@Data
public class Summary {
    private String name;
    private Integer userId;
    private List<InSummary> byTime;
}
