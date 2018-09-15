package com.project.api.data.model.vehicle;

public enum VehicleEquipmentType {
    INTERIORSTANDARD(1), INTERIORVIP(2), INTERNET(3), GPS(4), FUELOILHARDWARE(5), CAMERA(6),
    TELEVISION(7), COFFEEMACHINE(8),
    NOTSET(0);

    private final int id;

    private VehicleEquipmentType(int id) {
        this.id = id;
    }

    public static VehicleEquipmentType getById(int id) {
        for (VehicleEquipmentType type : VehicleEquipmentType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return VehicleEquipmentType.NOTSET;
    }
}
