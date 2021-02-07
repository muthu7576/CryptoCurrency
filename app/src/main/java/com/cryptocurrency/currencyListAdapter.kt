package com.cryptocurrency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView



internal class currencyListAdapter(private var currencyDataList: List<currencyDataListModel>) :
        RecyclerView.Adapter<currencyListAdapter.MyViewHolder>() {
    var parentCon : ViewGroup? = null
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var seraiNo: TextView = view.findViewById(R.id.seraiNo)
        var currencyImage: ImageView = view.findViewById(R.id.currencyImage)
        var currencyNameData: TextView = view.findViewById(R.id.currencyNameData)
        var currencyPriceData: TextView = view.findViewById(R.id.currencyPriceData)
        var currencychangeData: TextView = view.findViewById(R.id.currencychangeData)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_data_card, parent, false)
        parentCon = parent;

        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currencyDataLists = currencyDataList[position]
        holder.seraiNo.text = (position+1).toString()
        getImage(currencyDataLists.image.toLowerCase())?.let { holder.currencyImage.setImageResource(it) };



        holder.currencyNameData.text = currencyDataLists.name
        val price = String.format("%.2f", (currencyDataLists.price.toString()).toDouble()).toDouble().toString();
        val dollarString: String? = parentCon?.context?.getString(R.string.dollar)
        holder.currencyPriceData.text = dollarString.plus(" ").plus(price)

        val change = String.format("%.2f", (currencyDataLists.change.toString()).toDouble()).toDouble().toString()
        val percentageString: String? = parentCon?.context?.getString(R.string.percentage)

        holder.currencychangeData.text = change.plus(percentageString);
    }
    override fun getItemCount(): Int {
        return currencyDataList.size
    }

    fun getImage(imageName: String): Int? {


        val drawableResourceId =
            parentCon?.context?.resources?.getIdentifier(imageName, "drawable", parentCon!!.context.getPackageName());

        return  drawableResourceId;
    }

  fun updateList(list: ArrayList<currencyDataListModel>){
      currencyDataList = list
      notifyDataSetChanged()
  }

}