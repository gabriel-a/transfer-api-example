package com.example.transferapi.controller;

import com.example.transferapi.domain.TransferItem;
import com.example.transferapi.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferRepository transferRepository;

    @Autowired
    public TransferController(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @GetMapping
    ResponseEntity<Collection<TransferItem>> get() {
        return new ResponseEntity<>(this.transferRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{transferId}")
    TransferItem findById(@PathVariable Long transferId) {
        return this.transferRepository
                .findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException(transferId));
    }

    @PostMapping
    ResponseEntity<TransferItem> add(@RequestBody TransferItem transferItem) {
        return new ResponseEntity<>(transferRepository.save(transferItem), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<TransferItem> update(@RequestBody TransferItem transferItem) {

       TransferItem transferItemInDb = this.transferRepository.findById(transferItem.getId())
               .orElseThrow(() -> new TransferNotFoundException(transferItem.getId()));
       transferItemInDb.setDate(transferItem.getDate());
       transferItemInDb.setFromCompany(transferItem.getFromCompany());
       transferItemInDb.setIpBlock(transferItem.getIpBlock());
       transferItemInDb.setToCompany(transferItem.getToCompany());
       transferItemInDb.setTransferType(transferItem.getTransferType());
       return new ResponseEntity<>(transferRepository.save(transferItemInDb),HttpStatus.OK);
    }

    @DeleteMapping("/{transferId}")
    ResponseEntity<TransferItem> delete(@PathVariable Long transferId) {

        TransferItem transferItemDb = this.transferRepository
                .findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException(transferId));

        transferRepository.delete(transferItemDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
