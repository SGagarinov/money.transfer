package com.transfer.money.transfer.config;
import com.transfer.money.transfer.controller.TransferController;
import com.transfer.money.transfer.service.LoggingService;
import com.transfer.money.transfer.service.TransferService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("{ com.transfer.money.domain, com.transfer.money.infrastructure }")
@ComponentScan(basePackageClasses = { TransferService.class, LoggingService.class, TransferController.class})
public class ServiceConfig {
    @Value("${ dollar:30 }")
    public static final Double DOLLAR = 30.0;

    @Value("${ euro:30 }")
    public static final Double EURO = 45.0;

    @Value("${ commission:1 }")
    public static final Short COMMISSION = 1;
}
