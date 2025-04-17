package com.think.rest_demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.think.rest_demo.model.Vendor;
import com.think.rest_demo.service.Vendor.VendorService;


@WebMvcTest(controllers = VendorController.class)
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("removal")
    @MockBean
    private VendorService vendorService;
    Vendor vendorFirst;
    Vendor vendorSecond;
    List<Vendor> vendorList = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        vendorFirst = new Vendor("C1","Sayak","Dum Dum Park","2390-8791","29");
        vendorSecond = new Vendor("C2","Avishikta","Belghoria","2019-2098","26");
        vendorList.add(vendorFirst);
        vendorList.add(vendorSecond);

    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void testGetVendorDetails() throws Exception {
        when(vendorService.getVendor("C1")).thenReturn(vendorFirst);
        this.mockMvc.perform(get("/vendor/C1")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testGetAllVendorDetails() throws Exception{
        when(vendorService.getAllVendors()).thenReturn(vendorList);
        this.mockMvc.perform(get("/vendor")).andDo(print()).andExpect(status().isOk());

    }


    @Test
    void testCreateVendorDetails() throws Exception{

        ObjectMapper objMap = new ObjectMapper();
        objMap.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objWri = objMap.writer().withDefaultPrettyPrinter();
        String requestJson = objWri.writeValueAsString(vendorFirst);
        when(vendorService.createVendor(vendorFirst)).thenReturn("C1");
        this.mockMvc.perform(post("/vendor")
        .content(requestJson) // Correct: Pass the actual request JSON
        .contentType(MediaType.APPLICATION_JSON)) // Correct: Specify the content type
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    void updateVendorDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(vendorFirst);

        when(vendorService.updateVendor(vendorFirst))
                .thenReturn("Cloud Vendor Updated Successfully");
        this.mockMvc.perform(put("/cloudvendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void testDeleteVendorDetails() throws Exception{
        when(vendorService.deleteVendor("C1")).thenReturn(true);
        this.mockMvc.perform(delete("/vendor/C1")).andDo(print()).andExpect(status().isOk());

    }

}
