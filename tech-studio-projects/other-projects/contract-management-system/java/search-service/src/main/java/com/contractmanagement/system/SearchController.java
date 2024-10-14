package com.contractmanagement.system;

import com.contractmanagement.system.Contract;
import com.contractmanagement.system.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/contracts")
    public ResponseEntity<List<Contract>> searchContracts(@RequestParam String title) {
        List<Contract> contracts = searchService.searchByTitle(title);
        return ResponseEntity.ok(contracts);
    }
}