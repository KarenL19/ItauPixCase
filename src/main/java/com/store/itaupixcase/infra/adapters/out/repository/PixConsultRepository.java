package com.store.itaupixcase.infra.adapters.out.repository;

import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PixConsultRepository {

    private final EntityManager entityManager;

    public PixConsultRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<PixEntity> findByFilters(FiltersCommand filters) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PixEntity> cq = cb.createQuery(PixEntity.class);
        Root<PixEntity> root = cq.from(PixEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getId() != null) {
            predicates.add(cb.equal(root.get("id"), filters.getId()));
        }
        if (filters.getKeyType() != null) {
            predicates.add(cb.equal(root.get("keyType"), filters.getKeyType()));
        }
        if (filters.getKeyValue() != null) {
            predicates.add(cb.equal(root.get("keyValue"), filters.getKeyValue()));
        }
        if (filters.getAccountNumber() != null) {
            predicates.add(cb.equal(root.get("accountNumber"), filters.getAccountNumber()));
        }
        if (filters.getAgencyNumber() != null) {
            predicates.add(cb.equal(root.get("agencyNumber"), filters.getAgencyNumber()));
        }
        if (filters.getAccountHolderName() != null) {
            predicates.add(cb.equal(root.get("holderName"), filters.getAccountHolderName()));
        }
        if (filters.getAccountType() != null) {
            predicates.add(cb.equal(root.get("accountType"), filters.getAccountType()));
        }

        if (filters.getInactiveTime() != null) {
            LocalDate date = LocalDate.parse(filters.getInactiveTime(), formatter);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            predicates.add(cb.between(root.get("inactiveTime"), start, end));
        }

        if (filters.getCreatedTime() != null) {
            LocalDate date = LocalDate.parse(filters.getCreatedTime(), formatter);
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            predicates.add(cb.between(root.get("createdTime"), start, end));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
