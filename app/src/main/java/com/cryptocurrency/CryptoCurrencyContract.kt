package com.cryptocurrency

interface CryptoCurrencyContract {

    interface  view : BaseView<presenter> {
        fun setCurrencyList(currencyList : ArrayList<currencyDataListModel>)
        fun updateCurrencyList(currencyList: ArrayList<currencyDataListModel>)

    }

    interface presenter : BasePresenter {
        fun updateCurrencyList();
        fun filterCurrencyList(
            filterItem: String,
            currencyDataList: ArrayList<currencyDataListModel>
        );
    }

}