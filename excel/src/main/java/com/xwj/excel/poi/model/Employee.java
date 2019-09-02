package com.xwj.excel.poi.model;

import com.xwj.excel.poi.annotation.ExcelIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 员工实体类.
 *
 * @author xuwenjie
 * @version  1.0
 */
@Data
public class Employee implements Serializable {
    @ExcelIgnore
    private String uuid;
    /**
     * 行号.
     */
    private Integer rowId;
    /**
     * eolinker名.
     */
    private String eolinkerName;
    /**
     * 真是姓名.
     */
    private String realName;
    /**
     * 是否新注册.
     */
    private String newRegist;
    /**
     * 入职时间.
     */
    private LocalDate time;
    /**
     * 职称.
     */
    private String level;
    /**
     * 分组.
     */
    private String groups;
    /**
     * 年龄.
     */
    private String age;
}
