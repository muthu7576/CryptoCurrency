package com.cryptocurrency

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class CryptoCurrencyPresenter(view : CryptoCurrencyContract.view) : CryptoCurrencyContract.presenter {

     private var view: CryptoCurrencyContract.view? = view

    override fun updateCurrencyList() {
            val BaseUrl = "https://api.coincap.io/v2/"
            val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(currencyDataListService::class.java)
            val call = service.getCurrencyDataList()
            call.enqueue(object : Callback<currencyDataListResponse> {
                override fun onResponse(call: Call<currencyDataListResponse>, response: Response<currencyDataListResponse>) {
                    if (response.code() == 200) {
                        val currencyDataResponses = response.body()!!
                        view?.setCurrencyList(currencyDataResponses.currecyDataListArray);
                    }
                }
                override fun onFailure(call: Call<currencyDataListResponse>, t: Throwable) {
                    System.out.println("the response from backenc failure: "+t.stackTrace.toString() + call)
                }
            })
    }

    override fun filterCurrencyList(
        filterItem: String,
        currencyDataList: ArrayList<currencyDataListModel>
    ) {
        var tempList:ArrayList<currencyDataListModel> = ArrayList()

        for(list in currencyDataList){
            if(filterItem.toLowerCase() in  list.name.toLowerCase()){
                tempList.add(list);
            }
        }
        view?.updateCurrencyList(tempList);
    }

     override fun onDestroy() {
         this.view = null
     }
 }