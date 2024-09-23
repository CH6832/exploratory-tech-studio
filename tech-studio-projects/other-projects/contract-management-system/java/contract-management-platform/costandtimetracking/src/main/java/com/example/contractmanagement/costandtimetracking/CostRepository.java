package com.example.contractmanagement.costandtimetracking;

@Service
public class CostService {
    @Autowired
    private CostRepository costRepository;

    public List<Cost> getAllCostsForContract(Long contractId) {
        return costRepository.findByContractId(contractId);
    }

    public Cost addCost(Cost cost) {
        return costRepository.save(cost);
    }
}