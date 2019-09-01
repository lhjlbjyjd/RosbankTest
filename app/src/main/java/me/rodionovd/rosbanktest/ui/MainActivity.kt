package me.rodionovd.rosbanktest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import me.rodionovd.rosbanktest.R
import me.rodionovd.rosbanktest.model.Currency
import me.rodionovd.rosbanktest.ui.adapters.CurrencyAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(CurrencyViewModel::class.java)

        viewModel.init()

        currencyList.layoutManager = LinearLayoutManager(this)

        nominalValue.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    viewModel.onNominalChanged(p0)
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            }
        )

        viewModel.currencyData.observe(this,
            Observer<List<Currency>> { t ->
                if (currencyList.adapter == null) {
                    currencyList.adapter = CurrencyAdapter(t, this)
                } else if (t != null) {
                    (currencyList.adapter as CurrencyAdapter).refreshDataset(t)
                }
            }
        )

        nominalValue.setText(String.format("%.2f", viewModel.currentNominal))
    }
}
