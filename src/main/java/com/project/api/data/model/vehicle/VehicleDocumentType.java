package com.project.api.data.model.vehicle;

public enum VehicleDocumentType {
    TRAFFICINSURANCE(1), CHAIRINSURANCE(2), VEHICLEINSURANCE(3), //KASKO
    VEHICLEINSPACTION(4), //ARAÇ MUAYENESİ
    NOTSET(0);

    private final int id;

    private VehicleDocumentType(int id) {
        this.id = id;
    }

    public static VehicleDocumentType getById(int id) {
        for (VehicleDocumentType type : VehicleDocumentType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return VehicleDocumentType.NOTSET;
    }
}
