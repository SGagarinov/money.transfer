package com.transfer.money;

import com.transfer.money.dto.Response;
import com.transfer.money.dto.ConfirmProperties;
import com.transfer.money.dto.Amount;
import com.transfer.money.dto.TransferInfo;
import com.transfer.money.entity.Card;
import com.transfer.money.entity.Transaction;
import com.transfer.money.exception.CardNotFoundException;
import com.transfer.money.exception.InsufficientFundsException;
import com.transfer.money.exception.InvalidCardInfoException;
import com.transfer.money.repository.CardDatabase;
import com.transfer.money.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TransferService transferService;

	@Container
	private GenericContainer<?> myTransfer = new GenericContainer<>("transfer:1.0")
			.withExposedPorts(5500);

	@BeforeEach
	void setUp() {
		myTransfer.start();
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
		System.out.println(transfer.getBody().operationId());
		assertNotNull(transfer.getBody().operationId());

		ConfirmProperties confirmProperties = new ConfirmProperties(transfer.getBody().operationId().toString(), "6324231");
		ResponseEntity<Response> confirm = restTemplate.postForEntity("http://localhost:" + port + "/confirmOperation",  confirmProperties, Response.class);
		System.out.println(confirm.getBody().operationId());
		assertNotNull(confirm.getBody().operationId());
	}
}
