package com.example.transferapi.controller;

import com.example.transferapi.domain.TransferType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/transfertypes")
public class TransferTypeController {
    @GetMapping
    ResponseEntity<Collection<String>> get() {
        Collection<String> enumNames = Stream.of(TransferType.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        return new ResponseEntity<>(enumNames, HttpStatus.OK);
    }
}
