package com.example.transferapi;

import com.example.transferapi.controller.IpRangeController;
import com.example.transferapi.controller.TransferController;
import com.example.transferapi.controller.TransferNotFoundException;
import com.example.transferapi.controller.TransferTypeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransferApiApplicationTests {

	@Autowired
	private IpRangeController ipRangeController;

	@Autowired
	private TransferController transferController;

	@Autowired
	private TransferTypeController transferTypeController;

	@Test
	public void contexLoads() {
		assertThat(ipRangeController).isNotNull();
		assertThat(transferController).isNotNull();
		assertThat(transferTypeController).isNotNull();
	}
}
