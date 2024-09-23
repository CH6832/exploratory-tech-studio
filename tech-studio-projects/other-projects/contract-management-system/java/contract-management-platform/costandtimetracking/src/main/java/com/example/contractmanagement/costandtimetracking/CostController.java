package com.example.contractmanagement.costandtimetracking;

@RestController
@RequestMapping("/api/costs")
public class CostController {
    @Autowired
    private CostService costService;

    @GetMapping("/contract/{contractId}")
    public List<Cost> getCostsForContract(@PathVariable Long contractId) {
        return costService.getAllCostsForContract(contractId);
    }

    @PostMapping
    public Cost addCost(@RequestBody Cost cost) {
        return costService.addCost(cost);
    }
}
