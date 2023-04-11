package com.unisound.ant.device.bean;

import java.util.List;

public class DeviceCapability {
    String capability;
    Detail detailInfo;
    List<String> operations;

    public String getCapability() {
        return this.capability;
    }

    public void setCapability(String capability2) {
        this.capability = capability2;
    }

    public List<String> getOperations() {
        return this.operations;
    }

    public void setOperations(List<String> operations2) {
        this.operations = operations2;
    }

    public Detail getDetailInfo() {
        return this.detailInfo;
    }

    public void setDetailInfo(Detail detailInfo2) {
        this.detailInfo = detailInfo2;
    }

    public static final class Detail {
        List<Vendors> supportVendors;

        public List<Vendors> getSupportVendors() {
            return this.supportVendors;
        }

        public void setSupportVendors(List<Vendors> supportVendors2) {
            this.supportVendors = supportVendors2;
        }
    }

    public static final class Vendors {
        String extraInfo;
        String vendorName;

        public String getVendorName() {
            return this.vendorName;
        }

        public void setVendorName(String vendorName2) {
            this.vendorName = vendorName2;
        }

        public String getExtraInfo() {
            return this.extraInfo;
        }

        public void setExtraInfo(String extraInfo2) {
            this.extraInfo = extraInfo2;
        }
    }
}
