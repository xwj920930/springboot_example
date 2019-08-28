package com.xwj.jacoco.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacocoTestTest {

    @Autowired
    private JacocoTest jacocoTest;
    @Test
    public void getSum() {
        assertEquals(3, jacocoTest.getSum(1, 2));
    }
}