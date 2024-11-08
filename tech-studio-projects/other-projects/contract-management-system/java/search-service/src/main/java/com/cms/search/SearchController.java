package com.cms.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cms.contract.Contract;
import com.cms.contract.ContractRepository;

/**
 * Controller class responsible for exposing search-related APIs.
 * <p>
 * This class handles HTTP requests for searching documents or records.
 * It uses the SearchService to process the search queries.
 * </p>
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ContractRepository contractRepository;

    /**
     * Endpoint to retrieve all contracts from the search_db.
     * Accessible at /contracts.
     *
     * @return List of all contracts
     */
    @GetMapping("/contracts")
    public List<Contract> getAllContracts() {
        return contractRepository.findAll(); // Fetch all contracts from the MongoDB collection
    }
}
