package com.transfer.money.domain.entity.transfer;

public class Amount {

    private Long value;
    private String currency;

    public Amount() {
    }

    public Amount(Long value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
