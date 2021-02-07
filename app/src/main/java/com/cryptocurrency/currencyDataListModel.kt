package com.cryptocurrency

import com.google.gson.annotations.SerializedName

class currencyDataListModel {

    @SerializedName("symbol")
    var image = ""
    @SerializedName("name")
    var name = ""

    @SerializedName("priceUsd")
    var price = ""

    @SerializedName("changePercent24Hr")
    var change = ""


}