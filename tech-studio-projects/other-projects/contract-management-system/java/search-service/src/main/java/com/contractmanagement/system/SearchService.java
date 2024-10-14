package com.contractmanagement.system;


import com.contractmanagement.system.Contract;
import com.contractmanagement.system.ContractSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ContractSearchRepository contractSearchRepository;

    @Autowired
    public SearchService(ContractSearchRepository contractSearchRepository) {
        this.contractSearchRepository = contractSearchRepository;
    }

    public List<Contract> searchByTitle(String title) {
        return contractSearchRepository.findByTitle(title);
    }
}