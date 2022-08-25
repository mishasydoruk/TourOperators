package com.example.touroperators.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;


public enum UserRole {
    @SerializedName("Customer")
    Customer,
    @SerializedName("Worker")
    Worker,
    @SerializedName("Owner")
    Owner
}
