package com.nickwellman.collections.gas.models;

import lombok.Data;

@Data
public class GasRequest {
    private String date;
    private String odometer;
    private String cost;
    private String gas;
    private String vehicle;
}
