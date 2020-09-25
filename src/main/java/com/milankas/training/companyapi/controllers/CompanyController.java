package com.milankas.training.companyapi.controllers;

import com.milankas.training.companyapi.dto.CompanyDto;
import com.milankas.training.companyapi.dto.patch.CompanyPatchDto;
import com.milankas.training.companyapi.errors.ErrorResponse;
import com.milankas.training.companyapi.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/v1/companies")
    public List<CompanyDto> getAllCompanies(){
        return companyService.getCompanies();
    }

    @GetMapping("/v1/companies/{companyId}")
    public CompanyDto getCompany(@PathVariable UUID companyId) {
        if (companyService.getCompany(companyId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            );
        }
        else {
            return companyService.getCompany(companyId);
        }
    }

    @PostMapping("/v1/companies")
    public ResponseEntity<Object> postCompany(@Valid @RequestBody CompanyDto companyDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation Failure", errors));
        }
        else {
            return ResponseEntity.ok(companyService.saveCompany(companyDto));
        }
    }

    @DeleteMapping("/v1/companies/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable UUID companyId) {
        if (companyService.deleteCompany(companyId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping("/v1/companies/{companyId}")
    public ResponseEntity<Object> patchCompany(@PathVariable UUID companyId,@Valid @RequestBody CompanyPatchDto companyPatchDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Validation Failure", errors));
        }
        else {
            if (companyService.getCompany(companyId) == null) {
                return ResponseEntity.notFound().build();
            }
            else {
                return ResponseEntity.ok(companyService.updateCompany(companyPatchDto, companyId));
            }
        }
    }


}
