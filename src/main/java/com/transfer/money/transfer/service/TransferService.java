package com.transfer.money.transfer.service;

import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.TransferInfo;

import java.util.UUID;

public interface TransferService {

    UUID transfer(TransferInfo transferInfo) throws Exception;

    UUID confirm(ConfirmProperties confirmProperties) throws Exception;
}
