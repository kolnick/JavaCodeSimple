package com.caochaojie.mock.dao;


import com.caochaojie.mock.po.User;
import com.caochaojie.mock.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DaoMockTest {

    @Mock
    private UserService userService;

    @Test
    public void test() {
        // Mockito.when(userService.findById(5)).thenReturn(User.builder().id(5).name("ccj").build());
        User user = userService.findById(5);
        //  Assert.assertEquals(user.getName(), "ccj");
        System.out.println(user);
    }
}
