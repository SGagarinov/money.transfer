package com.transfer.money.entity.transfer;

import com.transfer.money.entity.Card;

public class Transaction {

    private Card cardFrom;
    private Card cardTo;
    private TransferInfo transferInfo;
    private Boolean isConfirm;

    public Transaction(Card cardFrom, Card cardTo, TransferInfo transferInfo, Boolean isConfirm) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.transferInfo = transferInfo;
        this.isConfirm = isConfirm;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    public TransferInfo getTransferInfo() {
        return transferInfo;
    }

    public void setTransferInfo(TransferInfo transferInfo) {
        this.transferInfo = transferInfo;
    }

    public Boolean getConfirm() {
        return isConfirm;
    }

    public void setConfirm(Boolean confirm) {
        isConfirm = confirm;
    }
}
