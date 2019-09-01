package me.rodionovd.rosbanktest.repository

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import me.rodionovd.rosbanktest.model.CurrencyResult
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import me.rodionovd.rosbanktest.model.CurrencyWrapper


interface CurrencyDAO {
    @GET("/daily_json.js")
    fun getCurrency() : Observable<CurrencyResult>

    companion object Factory {
        private fun buildGsonConverterFactory(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(CurrencyWrapper::class.java, CurrencyConverter())
            return GsonConverterFactory.create(gsonBuilder.create())
        }

        fun create(): CurrencyDAO {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(buildGsonConverterFactory())
                .baseUrl("https://www.cbr-xml-daily.ru")
                .build()

            return retrofit.create(CurrencyDAO::class.java)
        }
    }
}