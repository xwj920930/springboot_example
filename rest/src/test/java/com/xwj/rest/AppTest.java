package com.xwj.rest;

import com.xwj.rest.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple RestApplication.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void httpGet() {
        Student student = restTemplate.getForObject("http://localhost:8088/springboot-demo/student", Student.class);
        System.out.println(student);
    }
}
