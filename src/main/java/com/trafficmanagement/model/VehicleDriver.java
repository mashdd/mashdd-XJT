package com.trafficmanagement.model;

import java.util.Date;

public class VehicleDriver {
    private int vehicleDriverId;    // 车辆驾驶员ID
    private String vehicleNumber;   // 车牌号
    private String driverIdCard;    // 驾驶员身份证号
    private Date authorizedDate;    // 授权日期

    // Getters and Setters
    public int getVehicleDriverId() {
        return vehicleDriverId;
    }

    public void setVehicleDriverId(int vehicleDriverId) {
        this.vehicleDriverId = vehicleDriverId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverIdCard() {
        return driverIdCard;
    }

    public void setDriverIdCard(String driverIdCard) {
        this.driverIdCard = driverIdCard;
    }

    public Date getAuthorizedDate() {
        return authorizedDate;
    }

    public void setAuthorizedDate(Date authorizedDate) {
        this.authorizedDate = authorizedDate;
    }
}
