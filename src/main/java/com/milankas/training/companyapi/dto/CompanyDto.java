package com.milankas.training.companyapi.dto;

import com.milankas.training.companyapi.persistance.model.Address;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class CompanyDto {

    private UUID id;

    @NotNull(message = "Name is required")
    @Size(min = 5, max = 12, message = "Name must be have 5 characters minimum and max 12 characters")
    private String name;

    @Valid
    @NotNull(message = "Address is required")
    private AddressDto address;
}
