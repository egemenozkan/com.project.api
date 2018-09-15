package com.project.api.data.model.document;

public enum TrafficAccidentType {
    LITTLEDAMAGED(1), MEDIUMDAMAGED(2), VERYDAMAGED(3), SCRAP(4), WITHWOUNDING(5),
    WITHDEATH(5),
    NOTSET(0);

    private final int id;

    private TrafficAccidentType(int id) {
        this.id = id;
    }

    public static TrafficAccidentType getById(int id) {
        for (TrafficAccidentType type : TrafficAccidentType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return TrafficAccidentType.NOTSET;
    }
}
