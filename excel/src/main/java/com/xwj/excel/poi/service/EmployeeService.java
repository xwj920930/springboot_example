package com.xwj.excel.poi.service;

import com.xwj.excel.poi.enumm.PoiType;
import com.xwj.excel.poi.model.Employee;

import java.io.InputStream;
import java.util.List;

/**
 * 员工业务.
 *
 * @author xuwenjie
 * @version  1.0
 */

public interface EmployeeService {
    /**
     * 将Excel文件解析.
     *
     * @param inputStream Excel文件流
     * @param poiType Excel文件类型
     * @return 插入数据库的实体列表
     * service层入参最好不要是MultipartFile
     */
    List<Employee> getEntitiesFromExcel(InputStream inputStream, PoiType poiType);
}
