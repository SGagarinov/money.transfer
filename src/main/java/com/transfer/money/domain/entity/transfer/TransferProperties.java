package com.transfer.money.domain.entity.transfer;


public class TransferProperties {

    private String cardFromNumber;
    private String cardFromValidTill;
    private Short cardFromCVV;
    private String cardToNumber;

    public TransferProperties() {

    }

    public TransferProperties(String cardFromNumber, String cardFromValidTill, Short cardFromCVV, String cardToNumber) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public Short getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(Short cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }
}
