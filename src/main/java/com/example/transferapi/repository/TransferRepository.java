package com.example.transferapi.repository;

import com.example.transferapi.domain.TransferItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferItem, Long> {

}
