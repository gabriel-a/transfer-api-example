package com.example.transferapi.controller;

import com.example.transferapi.data.TransferItemsData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/ipranges")
public class IpRangeController {
    @GetMapping
    ResponseEntity<Collection<String>> getAll() {
        Collection<String> collection = IntStream.range(1, 100)
                .mapToObj(TransferItemsData::GetListOfIps)
                .collect(Collectors.toCollection(ArrayList::new));

        return new ResponseEntity<>(collection, HttpStatus.OK);
    }
}
