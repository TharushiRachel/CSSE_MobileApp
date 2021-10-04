package com.example.csse_mobileapp.Testing;

import com.example.csse_mobileapp.Items;
import com.example.csse_mobileapp.Order;

import org.junit.Before;
import org.junit.Test;


public class CalculatorTest {

    Order order;
    Items items;

    @Before
    void setUp(){
        order = new Order();
        items= new Items();
    }

    @Test
    void testAddition() {
        assertEquals((int) order.getTotal(10,3),13,
                "Regular multiplication should work");
    }

    private void assertEquals(int i, double total, String regular_multiplication_should_work) {
    }



}
