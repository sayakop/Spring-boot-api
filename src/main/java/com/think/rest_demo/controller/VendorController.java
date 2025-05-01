package com.think.rest_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.response.BookResponseHandler;
import com.think.rest_demo.response.VendorResponseHandler;
import com.think.rest_demo.service.Vendor.VendorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController 
{
    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeMessage()
    {
        return new ResponseEntity<>("Welcome to the Vendor Details", HttpStatus.OK);
    }

    //Get a specific Vendor from Database
    @GetMapping("{vendorId}")
    @Operation(
    summary = "Get Vendor by ID",
    description = "Provide a vendor ID to retrieve the vendor details",
    responses = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Vendor not found")
    }
)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        return VendorResponseHandler.responseBuilder
        ("Requested Vendor Details Given Here",HttpStatus.OK,vendorService.getVendor(vendorId));
    
    }

    //Get All Vendor Details from Database
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Vendor>> getAllVendorDetails()
    {
        return new ResponseEntity<>(vendorService.getAllVendors(),HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createVendorDetails(@RequestBody Vendor vendor)
    {
        vendorService.createVendor(vendor);
        return BookResponseHandler.responseBuilder(
        "Requested Vendor details are here",
        HttpStatus.OK,
        vendor
    );
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateVendorDetails(@RequestBody Vendor vendor)
    {
        vendorService.updateVendor(vendor);
        return "Vendor Updated Successfully";

    }

    @DeleteMapping("/{vendorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        boolean isVendorDeleted = vendorService.deleteVendor(vendorId);
        if(isVendorDeleted)
        {
            return new ResponseEntity<>("Vendor Deleted SuccessFully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Vendor Not Found", HttpStatus.NOT_FOUND);
        }

    }
}
