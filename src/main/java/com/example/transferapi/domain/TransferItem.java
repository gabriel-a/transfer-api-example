package com.example.transferapi.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TransferItem {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String IpBlock;
    private String FromCompany;
    private String ToCompany;
    private LocalDateTime Date;
    private TransferType TransferType;

    public TransferItem(){}

    public TransferItem(String ipBlock, String fromCompany, String toCompany, LocalDateTime date, TransferType transferType) {
        IpBlock = ipBlock;
        FromCompany = fromCompany;
        ToCompany = toCompany;
        Date = date;
        TransferType = transferType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpBlock() {
        return IpBlock;
    }

    @Required
    public void setIpBlock(String ipBlock) {
        IpBlock = ipBlock;
    }

    public String getFromCompany() {
        return FromCompany;
    }

    public void setFromCompany(String fromCompany) {
        FromCompany = fromCompany;
    }

    public String getToCompany() {
        return ToCompany;
    }

    public void setToCompany(String toCompany) {
        ToCompany = toCompany;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    @Required
    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public TransferType getTransferType() {
        return TransferType;
    }

    public void setTransferType(TransferType transferType) {
        TransferType = transferType;
    }
}
