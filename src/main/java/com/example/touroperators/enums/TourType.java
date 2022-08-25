package com.example.touroperators.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.SerializedName;

public enum TourType {

    @SerializedName("Croisse")
    Croisse,
    @SerializedName("Avia")
    Avia,
    @SerializedName("Walk")
    Walk;
}


