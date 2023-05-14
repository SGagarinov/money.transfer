package com.transfer.money.controller;

import com.transfer.money.service.TransferService;
import com.transfer.money.dto.Response;
import com.transfer.money.dto.ConfirmProperties;
import com.transfer.money.dto.TransferInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Response> transfer (@RequestBody TransferInfo info) throws Exception {
        Response result = transferService.transfer(info);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<Response> confirm (@RequestBody ConfirmProperties confirmProperties) throws Exception {
        Response result = transferService.confirm(confirmProperties);
        return ResponseEntity.ok(result);
    }
}
