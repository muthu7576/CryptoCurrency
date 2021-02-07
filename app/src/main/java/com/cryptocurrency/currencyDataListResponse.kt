package com.cryptocurrency

import com.google.gson.annotations.SerializedName

class currencyDataListResponse {
    @SerializedName("data")
    var currecyDataListArray = ArrayList<currencyDataListModel>()
}