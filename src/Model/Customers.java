/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author 84896
 */
public class Customers {
    private String CustomerCCCD, CustomerName, Cus_Phone, Email, Address, SocialMedia, Gender, Nation;
    private Date DateOfBirth;
    public Customers() {
    }

    public Customers(String CustomerCCCD, String CustomerName, String Cus_Phone, String Email, String Address, String SocialMedia, String Gender, String Nation, Date DateOfBirth) {
        this.CustomerCCCD = CustomerCCCD;
        this.CustomerName = CustomerName;
        this.Cus_Phone = Cus_Phone;
        this.Email = Email;
        this.Address = Address;
        this.SocialMedia = SocialMedia;
        this.Gender = Gender;
        this.Nation = Nation;
        this.DateOfBirth = DateOfBirth;
    }

    public String getCustomerCCCD() {
        return CustomerCCCD;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCus_Phone() {
        return Cus_Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getSocialMedia() {
        return SocialMedia;
    }

    public String getGender() {
        return Gender;
    }

    public String getNation() {
        return Nation;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setCustomerCCCD(String CustomerCCCD) {
        this.CustomerCCCD = CustomerCCCD;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setCus_Phone(String Cus_Phone) {
        this.Cus_Phone = Cus_Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setSocialMedia(String SocialMedia) {
        this.SocialMedia = SocialMedia;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setNation(String Nation) {
        this.Nation = Nation;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }
    
}
