package com.caochaojie.springboot.mock;


import com.caochaojie.springboot.service.IOCService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockServiceTest {

    @Autowired
    private IOCService iocService;


    @Test
    public void test2() {
        String message = iocService.getMessage();
        // Mockito.when(message).thenReturn("hello world");
        Assert.assertEquals(message, "hello world");
    }
}
