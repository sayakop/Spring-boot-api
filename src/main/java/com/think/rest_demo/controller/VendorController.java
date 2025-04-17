package com.think.rest_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.response.BookResponseHandler;
import com.think.rest_demo.response.VendorResponseHandler;
import com.think.rest_demo.service.Vendor.VendorService;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController 
{
    VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    //Get a specific Vendor from Database
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getVendorDetails(@PathVariable("vendorId") String vendorId)
    {
        return VendorResponseHandler.responseBuilder
        ("Requested Vendor Details Given Here",HttpStatus.OK,vendorService.getVendor(vendorId));
    
    }

    //Get All Vendor Details from Database
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendorDetails()
    {
        return new ResponseEntity<>(vendorService.getAllVendors(),HttpStatus.OK);
    }

    @PostMapping("")
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
    public String updateVendorDetails(@RequestBody Vendor vendor)
    {
        vendorService.updateVendor(vendor);
        return "Vendor Updated Successfully";

    }

    @DeleteMapping("/{vendorId}")
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
