package otal.egym.model;

import java.util.UUID;

public class User {

    public enum Gender {
        MALE, FEMALE
    }

    private String id;
    private Gender gender;

    private String title;
    private String firstName;
    private String lastName;

    private String street;
    private String city;
    private String state;
    private String postcode;
    private String username;
    private String phone;

    private String largePicture;
    private String thumbnail;

    public User() {
        id = UUID.randomUUID().toString();
    }

    public static String genderToString(Gender gender) {
        switch (gender) {
            case MALE: return "Male";
            case FEMALE: return "Female";
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLargePicture() {
        return largePicture;
    }

    public void setLargePicture(String largePicture) {
        this.largePicture = largePicture;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String toString() {
        return "Username: " + username + " Phone: " + phone + " Thumbnail: " + thumbnail;
    }
}
