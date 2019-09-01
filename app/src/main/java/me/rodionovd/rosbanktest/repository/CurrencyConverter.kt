package me.rodionovd.rosbanktest.repository

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import me.rodionovd.rosbanktest.model.Currency
import me.rodionovd.rosbanktest.model.CurrencyWrapper
import java.lang.reflect.Type

class CurrencyConverter : JsonDeserializer<CurrencyWrapper> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrencyWrapper {
        val result = mutableListOf<Currency>()
        json?.let {
            for (key in it.asJsonObject.keySet()) {
                result.add(Gson().fromJson(it.asJsonObject.get(key), Currency::class.java))
            }
        }
        return CurrencyWrapper(result)
    }
}