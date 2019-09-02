package com.xwj.excel.jxls.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface JxlsService {
    /**
     * 最普通的对象转Excel，一个对象一行
     * @param inputStream 模板
     * @param outputStream 输出
     * @param model 数据
     */
    void exportFromSimpleObject(InputStream inputStream, OutputStream outputStream, Map model);
    /**
     * json转Excel
     * @param inputStream 模板
     * @param outputStream 输出
     * @param model 数据
     */
    void exportFromData(InputStream inputStream, OutputStream outputStream, Map model);
    /**
     * 复杂的对象转Excel，包含嵌套，一个对象多行
     * @param inputStream 模板
     * @param outputStream 输出
     * @param model 数据
     */
    void exportFromComplexObject(InputStream inputStream, OutputStream outputStream, Map model);
}
