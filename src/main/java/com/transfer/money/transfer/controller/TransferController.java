package com.transfer.money.transfer.controller;

import com.transfer.money.infrastructure.exception.CardNotFoundException;
import com.transfer.money.infrastructure.exception.InsufficientFundsException;
import com.transfer.money.infrastructure.exception.InvalidCardInfoException;
import com.transfer.money.transfer.service.TransferService;
import com.transfer.money.domain.entity.Response;
import com.transfer.money.domain.entity.confirm.ConfirmProperties;
import com.transfer.money.domain.entity.transfer.TransferInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public Response transfer (@RequestBody TransferInfo info) throws Exception {
        UUID result = transferService.transfer(info);
        return new Response(result);
    }

    @PostMapping("/confirmOperation")
    public Response confirm (@RequestBody ConfirmProperties confirmProperties) throws Exception {
        UUID result = transferService.confirm(confirmProperties);
        return new Response(result);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, it's me";
    }
}
