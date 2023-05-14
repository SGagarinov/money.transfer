package com.transfer.money.service;

import com.transfer.money.dto.Response;
import com.transfer.money.dto.ConfirmProperties;
import com.transfer.money.dto.TransferInfo;

public interface TransferService {

    Response transfer(TransferInfo transferInfo) throws Exception;

    Response confirm(ConfirmProperties confirmProperties) throws Exception;
}
