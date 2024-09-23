package com.example.contractmanagement.blockchainledgerservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionUtils;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;
import org.web3j.tx.Transfer;

import jakarta.persistence.Convert;

public class LedgerService {
    private final Web3j web3j;

    @Autowired
    public LedgerService(Web3j web3j) {
        this.web3j = web3j;
    }

    public String storeContractOnBlockchain(Contract contract) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("password", "wallet/path");
        TransactionUtils receipt = Transfer.sendFunds(
                web3j, credentials, "0xReceiverAddress", BigDecimal.ONE, Convert.ETHER
        ).send();

        return ((Object) receipt).getTransactionHash();
    }
}
