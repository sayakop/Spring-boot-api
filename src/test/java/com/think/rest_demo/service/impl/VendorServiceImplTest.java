package com.think.rest_demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.repository.VendorRepository;
import com.think.rest_demo.service.Vendor.VendorService;
import com.think.rest_demo.service.Vendor.impl.VendorServiceImpl;


public class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;
    private VendorService vendorService;
    AutoCloseable autoCloseable;
    Vendor vendor;


    @BeforeEach
    void setUp()
    {
        autoCloseable = MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository);
        vendor = new Vendor("C1","Sayak","Dum Dum Park","2390-8791","29");

    }

    @AfterEach
    void tearDown() throws Exception
    {
        autoCloseable.close();
    }

    @Test
    void testCreateVendor() {
        mock(Vendor.class);
        mock(VendorRepository.class);

        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.createVendor(vendor)).isEqualTo("Success");
    }

    @Test
    void testUpdateVendor() {
        mock(Vendor.class);
        mock(VendorRepository.class);

        when(vendorRepository.save(vendor)).thenReturn(vendor);
        assertThat(vendorService.createVendor(vendor)).isEqualTo("Success");
    }


    @Test
    void testGetVendor() {

        mock(Vendor.class);
        mock(VendorRepository.class);

        when(vendorRepository.findById("C1")).thenReturn(Optional.ofNullable(vendor));
        assertThat(vendorService.getVendor("C1").getVendorName()).isEqualTo(vendor.getVendorName());

    }
    
    @Test
    void testGetByVendorName() {
        mock(Vendor.class);
        mock(VendorRepository.class);

        when(vendorRepository.findByVendorName("Sayak")).
        thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
       assertThat(vendorService.getByVendorName("Sayak").get(0).getVendorId())
       .isEqualTo(vendor.getVendorId());


    }

    @Test
    void testGetAllVendors() {

        mock(Vendor.class);
        mock(VendorRepository.class);

        when(vendorRepository.findAll()).thenReturn(new ArrayList<Vendor>(Collections.singleton(vendor)));
        assertThat(vendorService.getAllVendors().get(0).getVendorName()).isEqualTo(vendor.getVendorName());
        
    }

    @Test
    void testDeleteVendor() {
        mock(Vendor.class);
        mock(VendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).
        when(vendorRepository).deleteById(any());
        assertThat(vendorService.deleteVendor("C1")).isEqualTo("Success");

    }
    

}
