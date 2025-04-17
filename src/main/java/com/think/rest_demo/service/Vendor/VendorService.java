package com.think.rest_demo.service.Vendor;

import com.think.rest_demo.model.Vendor;

import java.util.List;

public interface VendorService {
    public String createVendor(Vendor vendor);
    public String updateVendor(Vendor vendor);
    public boolean deleteVendor(String vendorId);
    public Vendor getVendor(String vendorId);
    public List<Vendor> getAllVendors();
    public List<Vendor> getByVendorName(String vendorName);
}
