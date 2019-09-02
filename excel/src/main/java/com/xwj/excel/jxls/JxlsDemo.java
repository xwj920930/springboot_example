package com.xwj.excel.jxls;

import com.xwj.excel.jxls.model.Employee;
import com.xwj.excel.jxls.utils.JxlsUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description jxls根据模板导出，擅长嵌套等风格，但是不易解析excel
 * 两种风格：1.不写</each>，用lastcell 2.写</each>
 * @Author yuki
 * @Date 2018/10/25 19:31
 * @Version 1.0
 **/
public class JxlsDemo {
    public static void main(String[] args) throws ParseException, IOException {
        List<Employee> employees = generateSampleEmployeeData();
        InputStream is=new FileInputStream("D:\\工作\\object_collection_template.xls");
        OutputStream os = new FileOutputStream("D:\\工作\\object_collection_output.xls");
        Map<String , Object> model=new HashMap<String , Object>();
        model.put("employees", employees);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(is, os, model);
        is.close();
        os.close();
    }

    public static List<Employee> generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<Employee>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15) );
        employees.add( new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25) );
        employees.add( new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00) );
        employees.add( new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15) );
        employees.add( new Employee(null, dateFormat.parse("1969-May-30"), 2800, 0.20) );
        return employees;
    }
}
