package com.project.api.data.model.transfer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.project.api.data.enums.CommissionType;
import com.project.api.data.enums.DestinationType;
import com.project.api.data.enums.VenueType;
import com.project.api.data.model.common.Company;

public class TransferContract {

    private int id;
    private String fromDestination;
    private DestinationType fromDestinationType;
    private VenueType fromVenueType;
    private String toDestination;
    private int fromSelfId;
    private DestinationType toDestinationType;
    private VenueType toVenueType;
    private int toSelfId;
    private BigDecimal amount;
    private Currency currency;
    private BigDecimal commission;
    private CommissionType commissionType;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate beginDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    private Company company;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime savedAt;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFromDestination() {
        return fromDestination;
    }
    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }
    public DestinationType getFromDestinationType() {
        return fromDestinationType;
    }
    public void setFromDestinationType(DestinationType fromDestinationType) {
        this.fromDestinationType = fromDestinationType;
    }
    public VenueType getFromVenueType() {
        return fromVenueType;
    }
    public void setFromVenueType(VenueType fromVenueType) {
        this.fromVenueType = fromVenueType;
    }
    public String getToDestination() {
        return toDestination;
    }
    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }
    public int getFromSelfId() {
        return fromSelfId;
    }
    public void setFromSelfId(int fromSelfId) {
        this.fromSelfId = fromSelfId;
    }
    public DestinationType getToDestinationType() {
        return toDestinationType;
    }
    public void setToDestinationType(DestinationType toDestinationType) {
        this.toDestinationType = toDestinationType;
    }
    public VenueType getToVenueType() {
        return toVenueType;
    }
    public void setToVenueType(VenueType toVenueType) {
        this.toVenueType = toVenueType;
    }
    public int getToSelfId() {
        return toSelfId;
    }
    public void setToSelfId(int toSelfId) {
        this.toSelfId = toSelfId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public BigDecimal getCommission() {
        return commission;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
    public CommissionType getCommissionType() {
        return commissionType;
    }
    public void setCommissionType(CommissionType commissionType) {
        this.commissionType = commissionType;
    }
    public LocalDate getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public LocalDateTime getSavedAt() {
        return savedAt;
    }
    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }

   

}
