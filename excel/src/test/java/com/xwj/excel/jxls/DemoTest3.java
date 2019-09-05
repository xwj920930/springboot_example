package com.xwj.excel.jxls;

import com.alibaba.fastjson.JSONArray;
import com.xwj.excel.jxls.model.Summary;
import com.xwj.excel.jxls.utils.JxlsUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Description 嵌套循环
 * 合并单元格的处理：先确定jx的模板再合并
 * 小计：必须在area范围内
 * @Author yuki
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest3 {
    @Test
    public void demo3Test() throws Exception{
        URI inUri = Objects.requireNonNull(this.getClass().getClassLoader().getResource("summary_template.xls")).toURI();
        InputStream in = Files.newInputStream(Paths.get(inUri));
        String root = System.getProperty("user.dir");
        String outFilePath = root+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+ "summary_template_output.xls";
        Files.createFile(Paths.get(outFilePath));
        OutputStream os = Files.newOutputStream(Paths.get(outFilePath));
        String str="[\n" +
                "    {\n" +
                "        \"name\": \"admin\",\n" +
                "        \"userid\": \"1\",\n" +
                "        \"bytime\": [\n" +
                "            {\n" +
                "                \"gather\": 0,\n" +
                "                \"channel\": \"1\",\n" +
                "                \"refund\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"gather\": 0,\n" +
                "                \"channel\": \"2\",\n" +
                "                \"refund\": 0\n" +
                "            },\n" +
                "            {\n" +
                "                \"gather\": 0,\n" +
                "                \"channel\": \"3\",\n" +
                "                \"refund\": 0\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"admin\",\n" +
                "        \"userid\": \"1\",\n" +
                "        \"bytime\": [\n" +
                "            {\n" +
                "                \"gather\": 1,\n" +
                "                \"channel\": \"1\",\n" +
                "                \"refund\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                \"gather\": 2,\n" +
                "                \"channel\": \"2\",\n" +
                "                \"refund\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"gather\": 3,\n" +
                "                \"channel\": \"3\",\n" +
                "                \"refund\": 3\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        List<Summary> summaries= JSONArray.parseArray(str,Summary.class);
        System.out.println(summaries);
        Map<String , Object> model=new HashMap<>();
        model.put("summaries", summaries);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(in, os, model);
        in.close();
        os.close();
    }
}
