package com.cryptocurrency

import retrofit2.Call
import retrofit2.http.GET

interface currencyDataListService {

    @GET("assets")
    fun getCurrencyDataList() : Call<currencyDataListResponse>
}