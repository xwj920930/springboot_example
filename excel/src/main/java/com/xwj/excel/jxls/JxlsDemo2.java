package com.xwj.excel.jxls;

import com.xwj.excel.jxls.utils.JxlsUtils;

import java.io.*;
import java.text.ParseException;
import java.util.*;

/**
 * @Description jxls demo
 * @Author yuki
 * @Date 2018/10/25 19:31
 * @Version 1.0
 **/
public class JxlsDemo2 {
    public static void main(String[] args) throws ParseException, IOException {
        InputStream is=new FileInputStream("D:\\工作\\demo.xls");
        OutputStream os = new FileOutputStream("D:\\工作\\demo_out.xls");
        Map<String , Object> model=new HashMap<String , Object>();
        model.put("id", "A123asd");
        model.put("name", "许文杰");
        model.put("age", "26");
        JxlsUtils.exportExcel(is, os, model);
        is.close();
        os.close();
    }
}
