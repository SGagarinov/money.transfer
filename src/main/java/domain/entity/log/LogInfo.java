package domain.entity.log;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogInfo {

    private String cardFrom;
    private String cardTo;
    private Double sum;
    private Integer commission;
    private String result;

    public LogInfo(String cardFrom, String cardTo, Double sum, Integer commission, String result) {
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
