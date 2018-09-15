package com.project.api.data.model.vehicle;

public enum VehicleCareAndRepairType {
    PERIODICAL(1), ACCIDENT(2),OTHER(3),
    NOTSET(0);

    private final int id;

    private VehicleCareAndRepairType(int id) {
        this.id = id;
    }

    public static VehicleCareAndRepairType getById(int id) {
        for (VehicleCareAndRepairType type : VehicleCareAndRepairType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return VehicleCareAndRepairType.NOTSET;
    }
}
