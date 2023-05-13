package com.transfer.money.service;

import com.transfer.money.entity.Response;
import com.transfer.money.entity.confirm.ConfirmProperties;
import com.transfer.money.entity.transfer.TransferInfo;

import java.util.UUID;

public interface TransferService {

    Response transfer(TransferInfo transferInfo) throws Exception;

    Response confirm(ConfirmProperties confirmProperties) throws Exception;
}
