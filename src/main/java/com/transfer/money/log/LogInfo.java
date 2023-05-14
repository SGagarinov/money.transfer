package com.transfer.money.log;

public record LogInfo(String cardFrom, String cardTo, Long sum, Integer commission, Boolean result) { }
