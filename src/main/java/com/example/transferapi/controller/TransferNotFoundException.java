package com.example.transferapi.controller;

public class TransferNotFoundException extends RuntimeException {

    public TransferNotFoundException(long transferId) {
        super("could not find transfer '" + transferId + "'.");
    }
}