package com.sue.cars.entity;

public enum Eligibility {
    ELIGIBLE("Clean Alternative Fuel Vehicle Eligible"),
    NOT_ELIGIBLE("Not eligible due to low battery range"),
    UNKNOWN("Eligibility unknown as battery range has not been researched");

    private final String value;

    private Eligibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
