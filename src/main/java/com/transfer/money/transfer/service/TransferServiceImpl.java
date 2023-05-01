package com.transfer.money.transfer.service;

import domain.entity.transfer.TransferInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    @Override
    public UUID transfer(TransferInfo transferInfo) {
        return null;
    }

    @Override
    public UUID confirm() {
        return null;
    }
}
