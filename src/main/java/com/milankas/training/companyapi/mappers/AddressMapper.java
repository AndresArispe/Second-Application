package com.milankas.training.companyapi.mappers;

import com.milankas.training.companyapi.dto.AddressDto;
import com.milankas.training.companyapi.persistance.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto addressToDto(Address address);

    List<AddressDto> toAddressesDto(List<Address> addresses);

    Address toAddress(AddressDto addressDto);
}
