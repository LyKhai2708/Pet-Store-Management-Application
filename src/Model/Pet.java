/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author 84896
 */
public class Pet {
    private String Pet_ID, Pet_Type, Pet_Breed,Pet_Name, Color;
    private int Age;
    private float Weight;
    private String Picture, Gender,Shots, Flush,Heath;
    private float Price;

    public Pet() {
    }

    public Pet(String Pet_ID, String Pet_Type, String Pet_Breed, String Pet_Name, String Color, int Age, float Weight, String Picture, 
               String Gender, String Shots, String Flush, String Heath, float Price) {
        this.Pet_ID = Pet_ID;
        this.Pet_Type = Pet_Type;
        this.Pet_Breed = Pet_Breed;
        this.Pet_Name = Pet_Name;
        this.Color = Color;
        this.Age = Age;
        this.Weight = Weight;
        this.Picture = Picture;
        this.Gender = Gender;
        this.Shots = Shots;
        this.Flush = Flush;
        this.Heath = Heath;
        this.Price = Price;
    }

    public String getPet_ID() {
        return Pet_ID;
    }

    public String getPet_Type() {
        return Pet_Type;
    }

    public String getPet_Breed() {
        return Pet_Breed;
    }

    public String getPet_Name() {
        return Pet_Name;
    }

    public String getColor() {
        return Color;
    }

    public int getAge() {
        return Age;
    }

    public float getWeight() {
        return Weight;
    }

    public String getPicture() {
        return Picture;
    }

    public String getGender() {
        return Gender;
    }

    public String getShots() {
        return Shots;
    }

    public String getFlush() {
        return Flush;
    }

    public String getHeath() {
        return Heath;
    }

    public float getPrice() {
        return Price;
    }

    public void setPet_ID(String Pet_ID) {
        this.Pet_ID = Pet_ID;
    }

    public void setPet_Type(String Pet_Type) {
        this.Pet_Type = Pet_Type;
    }

    public void setPet_Breed(String Pet_Breed) {
        this.Pet_Breed = Pet_Breed;
    }

    public void setPet_Name(String Pet_Name) {
        this.Pet_Name = Pet_Name;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public void setWeight(float Weight) {
        this.Weight = Weight;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setShots(String Shots) {
        this.Shots = Shots;
    }

    public void setFlush(String Flush) {
        this.Flush = Flush;
    }

    public void setHeath(String Heath) {
        this.Heath = Heath;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

}
