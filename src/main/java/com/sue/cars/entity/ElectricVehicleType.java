package com.sue.cars.entity;

public enum ElectricVehicleType {
    BEV("Battery Electric Vehicle (BEV)"),
    PHEV("Plug-in Hybrid Electric Vehicle (PHEV)");
    private final String value;

    private ElectricVehicleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
