package com.project.api.data.model.vehicle;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class VehicleDocument implements Serializable {

    private int id;
    private String documentNo;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate issueDate; //Başlangıç tarihi yada veriliş tarihi
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate expireDate; // Eğer document expire tarihi varsa. Örneğin kasko, araç sigortası vb.
    private VehicleDocumentType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public VehicleDocumentType getType() {
        return type;
    }

    public void setType(VehicleDocumentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VehicleDocument) {
            VehicleDocument toCompare = (VehicleDocument) o;
            return this.documentNo.equals(toCompare.documentNo);
        }
        return false;
    }
}
