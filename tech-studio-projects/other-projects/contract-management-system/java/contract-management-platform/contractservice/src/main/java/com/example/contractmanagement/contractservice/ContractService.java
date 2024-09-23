package com.example.contractmanagement.contractservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Optional<Contract> getContractById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract updateContract(Long id, Contract updatedContract) {
        return contractRepository.findById(id).map(contract -> {
            contract.setTitle(updatedContract.getTitle());
            contract.setDescription(updatedContract.getDescription());
            contract.setStartDate(updatedContract.getStartDate());
            contract.setEndDate(updatedContract.getStartDate());
            contract.setBudget(updatedContract.getBudget());
            return contractRepository.save(contract);
        }).orElseThrow();
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}
