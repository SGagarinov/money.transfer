package com.transfer.money.domain.repository;

import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.TransferInfo;

import java.util.UUID;

public interface CardDatabase {
    UUID transfer(TransferInfo transferInfo) throws Exception;
    UUID confirmOperation (ConfirmProperties confirmProperties) throws Exception;

    void init();
}
