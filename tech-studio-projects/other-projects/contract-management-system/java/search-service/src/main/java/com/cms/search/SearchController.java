/*

- http://localhost:8080/search/contracts
   [
     {
       "id": "1",
       "title": "Contract Title A",
       "terms": "Contract terms go here",
       "dateSigned": "2024-01-01"
     },
     {
       "id": "2",
       "title": "Contract Title B",
       "terms": "Contract terms go here",
       "dateSigned": "2024-02-01"
     }
   ]

*/

package com.cms.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cms.contract.ContractRepository;
import com.cms.contract.datamodel.Contract;

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
