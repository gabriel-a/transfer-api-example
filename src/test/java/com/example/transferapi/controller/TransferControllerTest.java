package com.example.transferapi.controller;

import com.example.transferapi.data.TransferItemsData;
import com.example.transferapi.domain.TransferItem;
import com.example.transferapi.domain.TransferType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TransferControllerTest extends RestTemplateHelper {

    private static final String API_TRANSFERS = "/api/transfers";

    @Test
    public void get() {
        ResponseEntity<TransferItem[]> response =
                this.restTemplate.getForEntity(getUrl() + API_TRANSFERS, TransferItem[].class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().length > 0);
    }

    @Test
    public void findById() {
        ResponseEntity<TransferItem> response = this.restTemplate.getForEntity(getUrl() + API_TRANSFERS + "/1", TransferItem.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().getId().equals(1L));
    }

    @Test
    public void findByIdDoesNotExists() {
        String url = getUrl() + API_TRANSFERS + "/0";
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void CrudOperation() {
        String ipAddress = TransferItemsData.GetListOfIps(1);
        LocalDateTime now = LocalDateTime.now();
        String fromCompanyA = "From Company A";
        String toCompanyB = "To Company B";

        TransferItem transferItem = new TransferItem();
        transferItem.setIpBlock(ipAddress);
        transferItem.setDate(now);
        transferItem.setFromCompany(fromCompanyA);
        transferItem.setToCompany(toCompanyB);
        transferItem.setTransferType(TransferType.MergerOrAcquisition);

        ResponseEntity<TransferItem> response = this.restTemplate.postForEntity(getUrl() + API_TRANSFERS, transferItem, TransferItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        TransferItem transferItemInserted = response.getBody();

        assertEquals(transferItemInserted.getId(), new Long(100));
        assertEquals(transferItemInserted.getDate(),now);
        assertEquals(transferItemInserted.getFromCompany(), fromCompanyA);
        assertEquals(transferItemInserted.getToCompany(), toCompanyB);
        assertEquals(transferItemInserted.getTransferType(), TransferType.MergerOrAcquisition);
    }

    @Test
    public void update()  {
        String resourceUrl = getUrl() + API_TRANSFERS + "/99";
        ResponseEntity<TransferItem> response = this.restTemplate.getForEntity(resourceUrl, TransferItem.class);

        String ipAddress = TransferItemsData.GetListOfIps(5);
        LocalDateTime now = LocalDateTime.now();
        String fromCompanyA = "From Company D";
        String toCompanyB = "To Company E";

        TransferItem transferItem = response.getBody();
        transferItem.setIpBlock(ipAddress);
        transferItem.setDate(now);
        transferItem.setFromCompany(fromCompanyA);
        transferItem.setToCompany(toCompanyB);
        transferItem.setTransferType(TransferType.Policy);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransferItem> entity = new HttpEntity<>(transferItem, headers);
        ResponseEntity<TransferItem> updated = this.restTemplate.exchange(getUrl() + API_TRANSFERS, HttpMethod.PUT, entity, TransferItem.class);

        assertEquals(updated.getStatusCode(), HttpStatus.OK);
        TransferItem transferItemUpdated = updated.getBody();

        assertEquals(transferItemUpdated.getId(), transferItem.getId());
        assertEquals(transferItemUpdated.getDate(), transferItem.getDate());
        assertEquals(transferItemUpdated.getFromCompany(), transferItem.getFromCompany());
        assertEquals(transferItemUpdated.getToCompany(), transferItem.getToCompany());
        assertEquals(transferItemUpdated.getTransferType(), transferItem.getTransferType());
    }

    @Test
    public void updateFailNotFound()  {

        String ipAddress = TransferItemsData.GetListOfIps(5);
        LocalDateTime now = LocalDateTime.now();
        String fromCompanyA = "From Company D";
        String toCompanyB = "To Company E";

        TransferItem transferItem = new TransferItem();
        transferItem.setId(0L);
        transferItem.setIpBlock(ipAddress);
        transferItem.setDate(now);
        transferItem.setFromCompany(fromCompanyA);
        transferItem.setToCompany(toCompanyB);
        transferItem.setTransferType(TransferType.Policy);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransferItem> entity = new HttpEntity<>(transferItem, headers);
        ResponseEntity<String> updated = this.restTemplate.exchange(getUrl() + API_TRANSFERS, HttpMethod.PUT, entity, String.class);

        assertEquals(updated.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void delete() {
        String resourceUrl = getUrl() + API_TRANSFERS + "/5";
        this.restTemplate.delete(resourceUrl);
        ResponseEntity<String> response = this.restTemplate.getForEntity(resourceUrl, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}