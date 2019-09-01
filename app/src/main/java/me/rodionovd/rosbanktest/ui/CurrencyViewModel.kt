package me.rodionovd.rosbanktest.ui

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.rodionovd.rosbanktest.model.Currency
import me.rodionovd.rosbanktest.model.CurrencyResult
import me.rodionovd.rosbanktest.repository.CurrencyDAO
import java.util.*

class CurrencyViewModel : ViewModel() {

    private var currencyResult: CurrencyResult? = null
    private val currencyDAO = CurrencyDAO.create()
    private val refreshTimer = Timer()

    private val mutableCurrencyData = MutableLiveData<List<Currency>>()
    val currencyData = mutableCurrencyData

    var currentNominal: Double = 100.0

    fun init() {
        refreshTimer.schedule(UpdateDataTask(), 0, 60000)
    }

    fun onNominalChanged(editable: Editable?) {
        val value = editable.toString().replace(',', '.').toDoubleOrNull()
        if (value != null) {
            currentNominal = value
            onDataChanged(value)
        }
    }

    private fun onDataChanged(nominal: Double) {
        val currencyList = mutableListOf<Currency>()
        currencyResult?.currency?.let {
            for (currency in it.currency) {
                currencyList.add(
                    Currency(
                        currency.name,
                        currency.numCode,
                        currency.charCode,
                        1.0,
                        currency.name,
                        nominal / (currency.value / currency.nominal),
                        currency.previousValue
                    )
                )
            }
        }
        currencyData.postValue(currencyList)
    }

    inner class UpdateDataTask : TimerTask() {
        override fun run() {
            currencyDAO.getCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    currencyResult = result
                    onDataChanged(100.0)
                }, { error ->
                    error.printStackTrace()
                })
        }
    }
}