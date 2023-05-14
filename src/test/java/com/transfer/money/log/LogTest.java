package com.transfer.money.log;

import com.transfer.money.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LogTest {

    private String path = "log.csv";

    LoggingService loggingService = new LoggingServiceImpl();

    @Test
    void logTest() {
        LogInfo logInfo = new LogInfo("5234", "523432", 100L, 1, true);
        loggingService.log(logInfo);

        StringBuilder result = new StringBuilder();

        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(result.toString());
        assertTrue(result.toString().contains(logInfo.cardFrom()));
        assertTrue(result.toString().contains(logInfo.cardTo()));
        assertTrue(result.toString().contains(logInfo.commission().toString()));
        assertTrue(result.toString().contains(logInfo.sum().toString()));
        assertTrue(result.toString().contains(logInfo.result().toString()));
    }
}
