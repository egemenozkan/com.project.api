package com.project.api.data.model.vehicle;

import java.time.LocalDate;

public class VehicleWorkHistory {

    private LocalDate startDate;
    private LocalDate endDate;
    private Long startKM;
    private Long endKM;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getStartKM() {
        return startKM;
    }

    public void setStartKM(Long startKM) {
        this.startKM = startKM;
    }

    public Long getEndKM() {
        return endKM;
    }

    public void setEndKM(Long endKM) {
        this.endKM = endKM;
    }
}
