package me.rodionovd.rosbanktest.model

import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: Long,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Double,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Previous")
    val previousValue: Double
)