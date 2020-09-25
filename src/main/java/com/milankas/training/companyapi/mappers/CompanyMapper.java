package com.milankas.training.companyapi.mappers;

import com.milankas.training.companyapi.dto.CompanyDto;
import com.milankas.training.companyapi.persistance.model.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto companyToDto(Company company);

    List<CompanyDto> toCompaniesDto(List<Company> companies);

    Company toCompany(CompanyDto companyDto);
}
