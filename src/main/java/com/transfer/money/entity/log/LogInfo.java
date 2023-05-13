package com.transfer.money.entity.log;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogInfo {

    private String cardFrom;
    private String cardTo;
    private Long sum;
    private Integer commission;
    private Boolean result;

    public LogInfo(String cardFrom, String cardTo, Long sum, Integer commission, Boolean result) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.sum = sum;
        this.commission = commission;
        this.result = result;
    }

    public String getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(String cardFrom) {
        this.cardFrom = cardFrom;
    }

    public String getCardTo() {
        return cardTo;
    }

    public void setCardTo(String cardTo) {
        this.cardTo = cardTo;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
