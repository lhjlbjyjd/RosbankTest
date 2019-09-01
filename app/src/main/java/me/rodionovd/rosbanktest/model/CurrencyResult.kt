package me.rodionovd.rosbanktest.model

import com.google.gson.annotations.SerializedName

data class CurrencyResult(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val prevDate: String,
    @SerializedName("PreviousURL")
    val prevUrl: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val currency: CurrencyWrapper
)

data class CurrencyWrapper(
    val currency: List<Currency>
)