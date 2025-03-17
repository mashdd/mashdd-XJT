package com.trafficmanagement.model;

import java.util.Date;

public class Vehicle {
    private String vehicleNumber;   // 车牌号
    private String drivingLicenseNumber; // 驾驶证号
    private String vehicleName;     // 车辆名称
    private String vehicleType;     // 车辆类型
    private Date registrationDate;  // 注册日期


    public Vehicle(String vehicleNumber, String vehicleName, String vehicleType, Date registrationDate, String drivingLicenseNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.registrationDate = registrationDate;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
    // Getters and Setters
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
