package com.transfer.money.transfer.service;

import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.TransferInfo;
import com.transfer.money.domain.repository.CardDatabase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private CardDatabase cardDatabase;

    public TransferServiceImpl(CardDatabase cardDatabase) {
        this.cardDatabase = cardDatabase;
        cardDatabase.init();
    }

    @Override
    public UUID transfer(TransferInfo transferInfo) throws Exception {
        return cardDatabase.transfer(transferInfo);
    }

    @Override
    public UUID confirm(ConfirmProperties confirmProperties) throws Exception {
        return cardDatabase.confirmOperation(confirmProperties);
    }
}
