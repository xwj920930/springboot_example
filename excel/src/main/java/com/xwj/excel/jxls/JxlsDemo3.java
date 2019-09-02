package com.xwj.excel.jxls;

import com.alibaba.fastjson.JSONArray;
import com.xwj.excel.jxls.model.Summary;
import com.xwj.excel.jxls.utils.JxlsUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 嵌套循环
 * 合并单元格的处理：先确定jx的模板再合并
 * 小计：必须在area范围内
 * @Author yuki
 * @Date 2018/10/29 15:48
 * @Version 1.0
 **/
public class JxlsDemo3 {
    public static void main(String[] args) throws IOException {
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

        InputStream is=new FileInputStream("D:\\工作\\summary_template.xls");
        OutputStream os = new FileOutputStream("D:\\工作\\summary_template_output.xls");
        Map<String , Object> model=new HashMap<>();
        model.put("summaries", summaries);
        model.put("nowdate", new Date());
        JxlsUtils.exportExcel(is, os, model);
        is.close();
        os.close();
    }
}
