package com.contractmanagement.system;

import com.contractmanagement.system.Contract;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContractSearchRepository extends ElasticsearchRepository {
    // Additional query methods can be defined here
}