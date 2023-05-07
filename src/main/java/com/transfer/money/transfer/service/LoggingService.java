package com.transfer.money.transfer.service;

import domain.entity.log.LogInfo;
import org.springframework.stereotype.Service;


public interface LoggingService {
    void log(LogInfo logInfo);
}
