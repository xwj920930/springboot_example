package com.xwj.excel.jxls;

import com.xwj.excel.jxls.utils.JxlsUtils;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description jxls根据模板导出，擅长嵌套等风格，但是不易解析excel
 * 两种风格：1.不写</each>，用lastcell 2.写</each>
 * @Author yuki
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest2 {
    @Test
    public void demo2Test() throws Exception{
        URI inUri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("demo.xls")).toURI();
        InputStream in = Files.newInputStream(Paths.get(inUri));
        String root = System.getProperty("user.dir");
        String outFilePath = root+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+ "demo_out.xls";
        Files.createFile(Paths.get(outFilePath));
        OutputStream os = Files.newOutputStream(Paths.get(outFilePath));
        Map<String , Object> model=new HashMap<>();
        model.put("id", "A123asd");
        model.put("name", "许文杰");
        model.put("age", "26");
        JxlsUtils.exportExcel(in, os, model);
        in.close();
        os.close();
    }
}
