package com.project.api.data.model.driver;

public enum DriverDocumentType {
    SRC1(1), SRC2(2), SRC3(3), SRC4(4), SRC5(5), PSYCHOTECHNIQUE(6),
    NOTSET(0);

    private final int id;

    private DriverDocumentType(int id) {
        this.id = id;
    }

    public static DriverDocumentType getById(int id) {
        for (DriverDocumentType type : DriverDocumentType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return DriverDocumentType.NOTSET;
    }
}
