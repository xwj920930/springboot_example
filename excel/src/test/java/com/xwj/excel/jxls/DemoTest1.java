package com.xwj.excel.jxls;

import com.xwj.excel.jxls.model.Employee;
import com.xwj.excel.jxls.utils.JxlsUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description jxls根据模板导出，擅长嵌套等风格，但是不易解析excel
 * 两种风格：1.不写</each>，用lastcell 2.写</each>
 * @Author yuki
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest1 {
    private List<Employee> employees=new ArrayList<>();
    @Before
    public void init() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add( new com.xwj.excel.jxls.model.Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15) );
        employees.add( new com.xwj.excel.jxls.model.Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25) );
        employees.add( new com.xwj.excel.jxls.model.Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00) );
        employees.add( new com.xwj.excel.jxls.model.Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15) );
        employees.add( new Employee(null, dateFormat.parse("1969-May-30"), 2800, 0.20) );
    }
    @Test
    public void demo1Test() throws Exception{
        URI inUri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("object_collection_template.xls")).toURI();
        InputStream in = Files.newInputStream(Paths.get(inUri));
        String root = System.getProperty("user.dir");
        String outFilePath = root+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+ "object_collection_template_out.xls";
        Files.createFile(Paths.get(outFilePath));
        OutputStream os = Files.newOutputStream(Paths.get(outFilePath));
        Map<String , Object> model=new HashMap<>();
        model.put("employees", employees);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(in, os, model);
        in.close();
        os.close();
    }
}
