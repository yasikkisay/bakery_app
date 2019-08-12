package com.bakery.test.models.enums;


public enum StatusType {
    FRESH("fresh"),
    STALE("stale");

    public final String title;

    StatusType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static StatusType fromValue(String value) {
        for (StatusType status : values()) {
            if (status.title.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown ENUM - '" + value + "'");
    }

}
