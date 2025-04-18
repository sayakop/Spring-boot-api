package com.think.rest_demo.mapper;

import com.think.rest_demo.dto.VendorDto;
import com.think.rest_demo.model.Vendor;

public class VendorMapper {

    public static VendorDto toDto(Vendor vendor)
    {
        VendorDto vendorDto = new VendorDto(
            vendor.getVendorId(),
            vendor.getVendorName(),
            vendor.getVendorAddress(),
            vendor.getVendorPhoneNumber(),
            vendor.getVendorAge() );
            return vendorDto;
    }

    public static Vendor toEntity(VendorDto vendorDto)
    {
        Vendor vendor = new Vendor(vendorDto.vendorId(),
        vendorDto.vendorName(),
        vendorDto.vendorAddress(),
        vendorDto.vendorPhoneNumber(),
        vendorDto.vendorAge());
        return vendor;
    }


}
