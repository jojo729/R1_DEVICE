package com.unisound.vui.common.location.bean;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.util.Const;

public class LocationInfo {
    public static final int GPS_BAIDU = 4;
    public static final int GPS_GAODE = 5;
    public static final int GPS_GOOGLE = 2;
    public static final int GPS_ORIGIN = 0;
    public double latitude = 0.0d;
    public float mAccuracy = 0.0f;
    public String mAddress;
    public String mAddressDetail;
    public double mAltitude = 0.0d;
    public float mBearing = 0.0f;
    public String mCity;
    public String mCityCode;
    public String mCondition;
    public String mCountry;
    public String mDistrict;
    public boolean mHasAccuracy = false;
    public boolean mHasAltitude = false;
    public boolean mHasBearing = false;
    public boolean mHasSpeed = false;
    public double mLongitude = 0.0d;
    public String mName;
    public String mPathPoints;
    public String mProvince;
    public float mSpeed = 0.0f;
    public String mStreet;
    public long mTime = 0;
    public int type = 4;

    public LocationInfo() {
    }

    public LocationInfo(LocationInfo l) {
        if (l != null) {
            this.type = l.type;
            this.latitude = l.latitude;
            this.mLongitude = l.mLongitude;
            this.mName = l.mName;
            this.mProvince = l.mProvince;
            this.mCity = l.mCity;
            this.mCityCode = l.mCityCode;
            this.mDistrict = l.mDistrict;
            this.mStreet = l.mStreet;
            this.mAddress = l.mAddress;
            this.mAddressDetail = l.mAddressDetail;
            this.mCondition = l.mCondition;
            this.mPathPoints = l.mPathPoints;
        }
    }

    public float getAccuracy() {
        return this.mAccuracy;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getAddressDetail() {
        return this.mAddressDetail;
    }

    public double getAltitude() {
        return this.mAltitude;
    }

    public float getBearing() {
        return this.mBearing;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getCityCode() {
        return this.mCityCode;
    }

    public String getDistrict() {
        return this.mDistrict;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String getName() {
        return this.mName;
    }

    public String getProvider() {
        return this.mProvince;
    }

    public String getProvince() {
        return this.mProvince;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public String getStreet() {
        return this.mStreet;
    }

    public long getTime() {
        return this.mTime;
    }

    public int getType() {
        return this.type;
    }

    public String getmCondition() {
        return this.mCondition;
    }

    public String getmCountry() {
        return this.mCountry;
    }

    public String getmPathPoints() {
        return this.mPathPoints;
    }

    public boolean hasAccuracy() {
        return this.mHasAccuracy;
    }

    public boolean hasAltitude() {
        return this.mHasAltitude;
    }

    public boolean hasBearing() {
        return this.mHasBearing;
    }

    public boolean hasSpeed() {
        return this.mHasSpeed;
    }

    public JSONObject parse2JSONObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Const.TableSchema.COLUMN_TYPE, this.type);
            jSONObject.put(Const.TableSchema.COLUMN_NAME, this.mName);
            jSONObject.put("country", this.mCountry);
            jSONObject.put("province", this.mProvince);
            jSONObject.put("city", this.mCity);
            jSONObject.put("cityCode", this.mCityCode);
            jSONObject.put("destrict", this.mDistrict);
            jSONObject.put("street", this.mStreet);
            jSONObject.put("address", this.mAddress);
            jSONObject.put("addressDetail", this.mAddressDetail);
            jSONObject.put("condition", this.mCondition);
            jSONObject.put("pathPoints", this.mPathPoints);
            jSONObject.put("lat", getLatitude());
            jSONObject.put("lng", getLongitude());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void removeAltitude() {
        this.mAltitude = 0.0d;
        this.mHasAltitude = false;
    }

    public void removeBearing() {
        this.mBearing = 0.0f;
        this.mHasBearing = false;
    }

    public void removeSpeed() {
        this.mSpeed = 0.0f;
        this.mHasSpeed = false;
    }

    public void setAccuracy(float accuracy) {
        this.mAccuracy = accuracy;
        this.mHasAccuracy = true;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public void setAddressDetail(String address) {
        this.mAddressDetail = address;
    }

    public void setAltitude(double altitude) {
        this.mAltitude = altitude;
        this.mHasAltitude = true;
    }

    public void setBearing(float bearing) {
        while (bearing < 0.0f) {
            bearing += 360.0f;
        }
        while (bearing >= 360.0f) {
            bearing -= 360.0f;
        }
        this.mBearing = bearing;
        this.mHasBearing = true;
    }

    public void setCity(String city) {
        if (city != null && city.lastIndexOf("市") > 0) {
            city = city.substring(0, city.lastIndexOf("市"));
        }
        this.mCity = city;
    }

    public void setCityCode(String mCityCode2) {
        this.mCityCode = mCityCode2;
    }

    public void setCountry(String mCountry2) {
        this.mCountry = mCountry2;
    }

    public void setDistrict(String mDistrict2) {
        this.mDistrict = mDistrict2;
    }

    public void setLatitude(double latitude2) {
        this.latitude = latitude2;
    }

    public void setLongitude(double mLongitude2) {
        this.mLongitude = mLongitude2;
    }

    public void setName(String mName2) {
        this.mName = mName2;
    }

    public void setProvider(String provider) {
        this.mProvince = provider;
    }

    public void setProvince(String province) {
        this.mProvince = province;
    }

    public void setSpeed(float speed) {
        this.mSpeed = speed;
        this.mHasSpeed = true;
    }

    public void setStreet(String mStreet2) {
        this.mStreet = mStreet2;
    }

    public void setTime(long time) {
        this.mTime = time;
    }

    public void setType(int type2) {
    }

    public void setmCondition(String mCondition2) {
        this.mCondition = mCondition2;
    }

    public void setmPathPoints(String mPathPoints2) {
        this.mPathPoints = mPathPoints2;
    }

    public String toString() {
        return parse2JSONObj().toString();
    }
}
