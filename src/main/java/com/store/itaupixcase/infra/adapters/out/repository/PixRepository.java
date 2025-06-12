package com.store.itaupixcase.infra.adapters.out.repository;

import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PixRepository extends JpaRepository<PixEntity, UUID> {

    @Query("SELECT COUNT(p) FROM PixEntity p WHERE p.agencyNumber = :agencyNumber AND p.accountNumber = :accountNumber AND p.keyStatus = 'A'")
    long countByKeyTypeAndKeyValue(@Param("agencyNumber") Integer agencyNumber,
                                   @Param("accountNumber") Integer accountNumber);

    Optional<PixEntity> findFirstByKeyValue(String keyValue);

    @Query("SELECT p FROM PixEntity p WHERE p.id = :id AND p.keyStatus = 'A'")
    Optional<PixEntity> findByIdAndKeyStatusActive(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE PixEntity p SET p.accountType = :accountType, p.accountNumber = :accountNumber, p.agencyNumber = :agencyNumber, p.holderName = :holderName, p.holderSurname = :holderSurname WHERE p.id = :id")
    void updatePixFields(UUID id, String accountType, Integer accountNumber, Integer agencyNumber, String holderName, String holderSurname);
}
