package com.xwj.excel.poi.service.impl;

import com.xwj.excel.poi.enumm.PoiType;
import com.xwj.excel.poi.model.Employee;
import com.xwj.excel.poi.service.EmployeeService;
import com.xwj.excel.poi.utils.PoiUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 员工业务实现.
 *
 * @author xuwenjie
 * @version  1.0
 */
@Service("excelService")
public class EmployeeServiceImp implements EmployeeService {
    /**
     * 过滤条件.
     *职称为P1或P2
     * 2018年入职
     * 除去重复记录
     * @param employees 实体列表
     * @return 过滤后的实体列表
     */
    private List<Employee> getFilter(List<Employee> employees) {
        employees.forEach(employee -> employee.setAge(employee.getAge().substring(0, 2)));
        return employees.stream()
                .filter(employee -> "P1".equals(employee.getLevel())||"P2".equals(employee.getLevel()))
                .filter(employee -> yearFilter(employee.getTime()))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 日期是否为2018年.
     *
     * @param date 日期
     * @return 是/否
     */
    private boolean yearFilter(LocalDate date){
        return 2018==date.getYear();
    }

    @Override
    public List<Employee> getEntitiesFromExcel(InputStream inputStream, PoiType poiType) {
        List<Employee> employeesFromExcel= PoiUtils
                .excelToEntities(inputStream, poiType, Employee.class);
        return getFilter(Objects.requireNonNull(employeesFromExcel));
    }
}
