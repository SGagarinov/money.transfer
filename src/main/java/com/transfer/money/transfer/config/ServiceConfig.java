package com.transfer.money.transfer.config;


import org.springframework.beans.factory.annotation.Value;

public class ServiceConfig {
    @Value("${ dollar:30 }")
    public static final Double DOLLAR = 30.0;

    @Value("${ euro:30 }")
    public static final Double EURO = 45.0;
}
