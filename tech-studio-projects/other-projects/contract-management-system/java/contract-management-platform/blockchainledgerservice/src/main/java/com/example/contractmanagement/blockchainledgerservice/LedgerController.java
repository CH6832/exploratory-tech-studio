package com.example.contractmanagement.blockchainledgerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.tx.Contract;

@RestController
@RequestMapping("/api/ledger")
public class LedgerController {
    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/storeContract")
    public ResponseEntity<String> storeContract(@RequestBody Contract contract) {
        try {
            String transactionHash = ledgerService.storeContractOnBlockchain(contract);
            return ResponseEntity.ok(transactionHash);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store contract");
        }
    }
}
