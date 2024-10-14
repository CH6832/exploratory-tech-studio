package com.contractmanagement.system;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Marks this interface as a Spring Data repository
@Repository 
public interface ContractRepository extends JpaRepository<Contract, Long> {
 // JpaRepository provides CRUD operations, so no additional methods are needed here.
 // You can define custom query methods if required.
}
