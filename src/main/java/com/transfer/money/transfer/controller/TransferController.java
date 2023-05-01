package com.transfer.money.transfer.controller;

import com.transfer.money.transfer.service.TransferService;
import domain.entity.Response;
import domain.entity.transfer.TransferInfo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class TransferController {

    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public Response transfer (@Valid TransferInfo info) {
        UUID result = transferService.transfer(info);
        return new Response(result);
    }

    @PostMapping("/confirmOperation")
    public Response confirm (@Valid TransferInfo info) {
       return null;
    }
}
