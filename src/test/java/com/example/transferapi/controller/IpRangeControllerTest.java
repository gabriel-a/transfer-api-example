package com.example.transferapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
public class IpRangeControllerTest extends RestTemplateHelper {

    @Test
    public void ipRangeShouldReturnList() throws Exception {
        String list = this.restTemplate.getForObject(getUrl() + "/api/ipranges", String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<String> ipRange = mapper.readValue(list, new TypeReference<List<String>>(){});
        assertFalse(ipRange.isEmpty());
    }

}
