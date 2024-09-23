package com.example.contractmanagement.contractservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {

	List<Contract> findAll();

	Contract save(Contract contract);

	Optional<Contract> findById(Long id);

	void deleteById(Long id);
}
