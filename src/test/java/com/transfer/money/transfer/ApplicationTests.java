package com.transfer.money.transfer;

import com.transfer.money.domain.entity.Response;
import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.Amount;
import com.transfer.money.domain.entity.transfer.TransferInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Container
	private GenericContainer<?> myTransfer = new GenericContainer<>("transfer:1.0")
			.withExposedPorts(5500);

	@BeforeEach
	void setUp() {
		myTransfer.start();
	}

	@Test
	void contextLoads() {
		String value = "Hello, it's me";
		Integer port = myTransfer.getMappedPort(5500);
		ResponseEntity<String> entityFirst = restTemplate.getForEntity("http://localhost:" + port + "/hello", String.class);
		System.out.println(entityFirst.getBody());
		assertEquals(value, entityFirst.getBody());
	}

	@Test
	void postTest() {
		Integer port = myTransfer.getMappedPort(5500);
		TransferInfo transferInfo = new TransferInfo();
		transferInfo.setCardFromCVV((short)632);
		transferInfo.setCardFromNumber("4960142027505908");
		transferInfo.setCardFromValidTill("10/27");
		transferInfo.setCardToNumber("4960147466192985");
		transferInfo.setAmount(new Amount(1000L, "USD"));

		ResponseEntity<Response> transfer = restTemplate.postForEntity("http://localhost:" + port + "/transfer",  transferInfo, Response.class);
		System.out.println(transfer.getBody().getOperationId());
		assertNotNull(transfer.getBody().getOperationId());

		ConfirmProperties confirmProperties = new ConfirmProperties();
		confirmProperties.setOperationId(transfer.getBody().getOperationId().toString());
		confirmProperties.setCode("6324231");
		ResponseEntity<Response> confirm = restTemplate.postForEntity("http://localhost:" + port + "/confirmOperation",  confirmProperties, Response.class);
		System.out.println(confirm.getBody().getOperationId());
		assertNotNull(confirm.getBody().getOperationId());
	}
}
