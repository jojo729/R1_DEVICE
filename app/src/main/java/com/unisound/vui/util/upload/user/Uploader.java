package com.unisound.vui.util.upload.user;

import org.json.JSONObject;

public interface Uploader {
    void upload(JSONObject jSONObject, UploaderListener uploaderListener);
}
