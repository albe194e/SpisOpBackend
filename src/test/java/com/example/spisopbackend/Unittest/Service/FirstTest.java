package com.example.spisopbackend.Unittest.Service;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FirstTest {


    @Test
    void test(){

        assertEquals(2,1+1);
    }
}
