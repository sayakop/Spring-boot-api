package com.think.rest_demo.mapper;

import com.think.rest_demo.dto.VendorDto;
import com.think.rest_demo.dto.CreateVendorRecord;
import com.think.rest_demo.model.Vendor;

public class VendorMapper {

    public static Vendor fromCreateDto(CreateVendorRecord dto) {
        Vendor vendor = new Vendor();
        vendor.setVendorId(dto.vendorId());
        vendor.setVendorName(dto.vendorName());
        vendor.setVendorAddress(dto.vendorAddress());
        vendor.setVendorPhoneNumber(dto.vendorPhoneNumber());
        vendor.setVendorAge(dto.vendorAge());
        return vendor;
    }

    public static VendorDto toDto(Vendor vendor) {
        return new VendorDto(
            vendor.getVendorId(),
            vendor.getVendorName(),
            vendor.getVendorAddress(),
            vendor.getVendorPhoneNumber(),
            vendor.getVendorAge()
        );
    }

}
