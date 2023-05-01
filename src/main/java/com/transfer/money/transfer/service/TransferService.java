package com.transfer.money.transfer.service;

import domain.entity.transfer.TransferInfo;

import java.util.UUID;

public interface TransferService {

    UUID transfer(TransferInfo transferInfo);

    UUID confirm();
}
