package com.transfer.money.controller;

import com.transfer.money.service.TransferService;
import com.transfer.money.entity.Response;
import com.transfer.money.entity.confirm.ConfirmProperties;
import com.transfer.money.entity.transfer.TransferInfo;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<Response>(result, HttpStatus.OK);
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<Response> confirm (@RequestBody ConfirmProperties confirmProperties) throws Exception {
        Response result = transferService.confirm(confirmProperties);
        return new ResponseEntity<Response>(result, HttpStatus.OK);
    }
}
