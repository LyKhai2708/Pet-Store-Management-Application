/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author 84896
 */
public class Staff {
    private String id, name, nation, phone, email, socialmedia;

    public Staff() {
    }
    
    public Staff(String id, String name, String nation, String phone, String email, String socialmedia) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.phone = phone;
        this.email = email;
        this.socialmedia = socialmedia;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSocialmedia() {
        return socialmedia;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSocialmedia(String socialmedia) {
        this.socialmedia = socialmedia;
    }
    
}
    