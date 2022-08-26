package com.example.touroperators.enums;

import com.google.gson.annotations.SerializedName;

public enum UserRole {
    @SerializedName("ROLE_Customer")
    ROLE_Customer,
    @SerializedName("ROLE_Worker")
    ROLE_Worker,
    @SerializedName("ROLE_Owner")
    ROLE_Owner
}
