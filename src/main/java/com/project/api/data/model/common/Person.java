package com.project.api.data.model.common;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.project.api.data.enums.BloodType;
import com.project.api.data.enums.Gender;
import com.project.api.data.enums.MaritalStatus;
import com.project.api.data.enums.UserType;

public abstract class Person {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthdate;
    private IdValue nationality;
    private String nationalID;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private ContactInformation contactInformation;
    private BloodType bloodType;
    private UserType userType;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public void setMiddleName(String middleName) {
	this.middleName = middleName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
	return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
	this.birthdate = birthdate;
    }

    public IdValue getNationality() {
	return nationality;
    }

    public void setNationality(IdValue nationality) {
	this.nationality = nationality;
    }

    public Gender getGender() {
	return gender;
    }

    public void setGender(Gender gender) {
	this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
	return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public ContactInformation getContactInformation() {
	return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
	this.contactInformation = contactInformation;
    }

    public BloodType getBloodType() {
	return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
	this.bloodType = bloodType;
    }

    public String getNationalID() {
	return nationalID;
    }

    public void setNationalID(String nationalID) {
	this.nationalID = nationalID;
    }

    public UserType getUserType() {
	return userType;
    }

    public void setUserType(UserType userType) {
	this.userType = userType;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

}
