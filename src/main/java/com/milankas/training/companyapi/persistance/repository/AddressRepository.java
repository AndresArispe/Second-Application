package com.milankas.training.companyapi.persistance.repository;

import com.milankas.training.companyapi.dto.AddressDto;
import com.milankas.training.companyapi.persistance.model.Address;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository <Address, UUID> {

}
