package com.milankas.training.companyapi.persistance.repository;

import com.milankas.training.companyapi.persistance.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository <Company, UUID> {
}
