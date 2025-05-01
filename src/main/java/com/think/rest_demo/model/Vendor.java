package com.think.rest_demo.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="vendor details", schema = "vendor")
@Schema(description = "This table contains all the vendor details")
public class Vendor {

    @Id
    @Schema(description = "This is Vendor ID, and it shall be unique.")
    private String vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhoneNumber;
    private String vendorAge;

    @JsonIgnore
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Book> books;

    public Vendor() {
    }


    public Vendor
    (String vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber,String vendorAge) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
        this.vendorAge = vendorAge;
    }


    public String getVendorId() {
        return vendorId;
    }
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getVendorAddress() {
        return vendorAddress;
    }
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }
    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }
    public String getVendorAge() {
        return vendorAge;
    }
    public void setVendorAge(String vendorAge) {
        this.vendorAge = vendorAge;
    }

}
