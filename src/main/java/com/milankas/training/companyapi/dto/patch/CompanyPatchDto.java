package com.milankas.training.companyapi.dto.patch;

import com.milankas.training.companyapi.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
public class CompanyPatchDto {

    private UUID id;

    @Size(min = 5, max = 12, message = "Name must be have 5 characters minimum and max 12 characters")
    private String name;

    @Valid
    private AddressDto address;
}