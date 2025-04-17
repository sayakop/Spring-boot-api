package com.think.rest_demo.service.Vendor.impl;

import com.think.rest_demo.exception.VendorException.NotFoundVendorException;
import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.repository.VendorRepository;
import com.think.rest_demo.service.Vendor.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {                                                                                                                                                                 

    @Autowired
    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public String createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return "Success";
    }

    @Override
    public String updateVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return "Success";
    }

    @Override
    public boolean deleteVendor(String vendorId) {
        if(vendorRepository.existsById(vendorId))
        {
            vendorRepository.deleteById(vendorId);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Vendor getVendor(String vendorId) {
        // More Business Logic
        if(vendorRepository.findById(vendorId).isEmpty())
            throw new NotFoundVendorException("Requested Vendor does not exist");
        return vendorRepository.findById(vendorId).get();
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
    @Override
    public List<Vendor> getByVendorName(String vendorName)
    {
        return vendorRepository.findByVendorName(vendorName);
    }

}
