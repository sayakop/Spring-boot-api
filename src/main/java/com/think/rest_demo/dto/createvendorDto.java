package com.think.rest_demo.dto;

public record CreateVendorDto(
    
    String vendorId,
    String vendorName,
    String vendorAddress,
    String vendorPhoneNumber,
    String vendorAge
) {}
