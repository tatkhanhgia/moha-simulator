package com.example.hdld.domain.repository;

import com.example.hdld.domain.entity.LaborContract;
import com.example.hdld.domain.valueobject.ContractUuid;
import com.example.hdld.domain.valueobject.EnterpriseUuid;

import java.util.List;
import java.util.Optional;

/**
 * Port for labor contract persistence operations.
 */
public interface LaborContractRepository {

    LaborContract save(LaborContract contract);

    Optional<LaborContract> findById(ContractUuid id);

    List<LaborContract> findByEnterpriseId(EnterpriseUuid enterpriseId);

    List<LaborContract> findAll();

    void deleteById(ContractUuid id);
}
