package com.unisound.ant.device.bean;

public class GpsInfo extends Parameter {
    private String address;
    private String city;
    private String cityCode;
    private String country;
    private double lat;
    private double lon;
    private int mapType;
    private String province;
    private String region;

    public int getMapType() {
        return this.mapType;
    }

    public void setMapType(int mapType2) {
        this.mapType = mapType2;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country2) {
        this.country = country2;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat2) {
        this.lat = lat2;
    }

    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon2) {
        this.lon = lon2;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city2) {
        this.city = city2;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address2) {
        this.address = address2;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode2) {
        this.cityCode = cityCode2;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province2) {
        this.province = province2;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region2) {
        this.region = region2;
    }
}
