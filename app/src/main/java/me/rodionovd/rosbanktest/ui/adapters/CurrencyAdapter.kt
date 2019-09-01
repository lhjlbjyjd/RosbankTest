package me.rodionovd.rosbanktest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.currency_item.view.*
import me.rodionovd.rosbanktest.R
import me.rodionovd.rosbanktest.model.Currency

class CurrencyAdapter(
    private var items: List<Currency>,
    private val context: Context
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = items[position].charCode
        holder.subtitleView.text = items[position].name
        holder.currencyView.text = String.format("%.2f", items[position].value)
    }

    fun refreshDataset(items: List<Currency>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.titleView
        val subtitleView: TextView = view.subtitleView
        val currencyView: TextView = view.currencyView
    }
}