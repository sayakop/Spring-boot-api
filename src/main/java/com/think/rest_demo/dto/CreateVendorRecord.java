package com.think.rest_demo.dto;

public record CreateVendorRecord(
    
    String vendorId,
    String vendorName,
    String vendorAddress,
    String vendorPhoneNumber,
    String vendorAge
) {}
