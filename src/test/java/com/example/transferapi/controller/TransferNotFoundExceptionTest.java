package com.example.transferapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TransferNotFoundExceptionTest {
    @Test
    public void transferTypeShouldReturnList() {
        TransferNotFoundException transferNotFoundException = new TransferNotFoundException(1L);
        assertEquals(transferNotFoundException.getMessage(), "could not find transfer '1'.");
    }
}
