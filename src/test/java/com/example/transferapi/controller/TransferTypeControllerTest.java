package com.example.transferapi.controller;

import com.example.transferapi.domain.TransferType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TransferTypeControllerTest extends RestTemplateHelper {
    @Test
    public void transferTypeShouldReturnList() {
        TransferType[] list = this.restTemplate.getForObject(getUrl() + "/api/transfertypes", TransferType[].class);
        assertTrue(list.length > 0);
    }
}
