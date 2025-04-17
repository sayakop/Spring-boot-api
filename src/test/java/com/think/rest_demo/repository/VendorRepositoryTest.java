package com.think.rest_demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.think.rest_demo.model.Vendor;

@DataJpaTest
public class VendorRepositoryTest {

    //given-when-then
    @Autowired
    private VendorRepository vendorRepository;
    Vendor vendor;

    @BeforeEach
    void setUp()
    {
        vendor = new Vendor("C1","Sayak","Dum Dum Park","2390-8791","29");
        vendorRepository.save(vendor);

    }

    @AfterEach
    void tearDown()
    {
        vendor = null;
        vendorRepository.deleteAll();
    }

    //Test Case SUCCESS

    @Test
    void testFoundVendorName()
    {
        List<Vendor> vendorList = vendorRepository.findByVendorName("Sayak");
        assertThat(vendorList.get(0).getVendorId()).isEqualTo(vendor.getVendorId());
        assertThat(vendorList.get(0).getVendorAge()).isEqualTo(vendor.getVendorAge());

    }

    //Test Case FAILURE
    @Test
    void testNotFoundVendorName()
    {
        List<Vendor> vendorList = vendorRepository.findByVendorName("Oli");
        assertThat(vendorList.isEmpty()).isTrue();
    }



}
