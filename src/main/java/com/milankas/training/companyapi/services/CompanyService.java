package com.milankas.training.companyapi.services;

import com.milankas.training.companyapi.dto.CompanyDto;
import com.milankas.training.companyapi.dto.patch.CompanyPatchDto;
import com.milankas.training.companyapi.mappers.AddressMapper;
import com.milankas.training.companyapi.mappers.CompanyMapper;
import com.milankas.training.companyapi.persistance.model.Address;
import com.milankas.training.companyapi.persistance.model.Company;
import com.milankas.training.companyapi.persistance.repository.AddressRepository;
import com.milankas.training.companyapi.persistance.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {

    CompanyRepository companyRepository;
    AddressRepository addressRepository;
    CompanyMapper companyMapper;
    AddressMapper addressMapper;

    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository, CompanyMapper companyMapper, AddressMapper addressMapper) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.companyMapper = companyMapper;
        this.addressMapper = addressMapper;
    }

    public List<CompanyDto> getCompanies() {
        return companyMapper.toCompaniesDto(companyRepository.findAll());
    }

    public CompanyDto getCompany(UUID companyId) {
        return companyMapper.companyToDto(companyRepository.findById(companyId).orElse(null));
    }

    public CompanyDto saveCompany(CompanyDto companyDto) {
        Company newCompany = new Company();
        newCompany.setName(companyDto.getName());
        companyRepository.save(newCompany);
        Address newAddress = addressMapper.toAddress(companyDto.getAddress());
        newAddress.setCompany(newCompany);
        newCompany.setAddress(newAddress);
        addressRepository.save(newAddress);
        companyRepository.save(newCompany);
        return companyMapper.companyToDto(newCompany);
    }

    public CompanyDto deleteCompany(UUID companyId) {
        Company deletedCompany = companyRepository.findById(companyId).orElse(null);
        if (deletedCompany == null) {
            return null;
        }
        else {
            companyRepository.deleteById(companyId);
            return companyMapper.companyToDto(deletedCompany);
        }
    }

    public CompanyDto updateCompany(CompanyPatchDto companyPatchDto, UUID companyId) {
        Company existingCompany = companyRepository.findById(companyId).orElse(null);
        if (existingCompany == null) {
            return null;
        }
        else {
            if (companyPatchDto.getName() != null) {
                existingCompany.setName(companyPatchDto.getName());
            }
            if (companyPatchDto.getAddress() != null) {
                existingCompany.getAddress().setAddressLine1(companyPatchDto.getAddress().getAddressLine1());
                existingCompany.getAddress().setAddressLine2(companyPatchDto.getAddress().getAddressLine2());
                existingCompany.getAddress().setState(companyPatchDto.getAddress().getState());
                existingCompany.getAddress().setCity(companyPatchDto.getAddress().getCity());
                existingCompany.getAddress().setZipCode(companyPatchDto.getAddress().getZipCode());
                existingCompany.getAddress().setCountryCode(companyPatchDto.getAddress().getCountryCode());
            }
        }
        return companyMapper.companyToDto(companyRepository.save(existingCompany));
    }
}
