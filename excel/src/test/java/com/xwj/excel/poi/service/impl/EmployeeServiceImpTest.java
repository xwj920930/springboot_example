package com.xwj.excel.poi.service.impl;

import com.xwj.excel.poi.enumm.PoiType;
import com.xwj.excel.poi.model.Employee;
import com.xwj.excel.poi.service.EmployeeService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImpTest {
    @Autowired
    EmployeeService excelService;

    @Test
    public void getEntitiesFromExcel() throws Exception{
        URI uri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("eolinker信息.xlsx")).toURI();
        InputStream stream = Files.newInputStream(Paths.get(uri));
        List<Employee> employees = excelService.getEntitiesFromExcel(stream, PoiType.XLSX);
        System.out.println(employees);
        Assert.assertEquals(employees.size(),5);
    }

    @Test//删除空行
    public void getExcelByDemo() throws Exception{
        URI uri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("eolinker信息.xlsx")).toURI();
        InputStream stream = Files.newInputStream(Paths.get(uri));
        XSSFWorkbook workbook= new XSSFWorkbook(stream);
        XSSFSheet sheet=workbook.getSheetAt(0);
        int i = sheet.getLastRowNum();
        XSSFRow tempRow;
        while(i > 0){
            i--;
            tempRow = sheet.getRow(i);
            if(tempRow == null){
                sheet.shiftRows(i+1, sheet.getLastRowNum(), -1);
            }
        }
        FileOutputStream out=new FileOutputStream("");
        workbook.write(out);
        out.close();
    }
}