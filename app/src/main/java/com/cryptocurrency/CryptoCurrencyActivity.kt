package com.cryptocurrency

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class CryptoCurrencyActivity : AppCompatActivity() , CryptoCurrencyContract.view {


    private lateinit var currencyListAdapter: currencyListAdapter;
    private lateinit var searchCurrencyList: EditText;
    private lateinit var currencyList: RecyclerView;

    lateinit var currencyDataList : ArrayList<currencyDataListModel>;



    internal lateinit var cryptoCurrencyPresenter: CryptoCurrencyContract.presenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchCurrencyList = findViewById(R.id.searchCurrencyList);
        currencyList = findViewById(R.id.currencyList)



        setPresenter(CryptoCurrencyPresenter(this))

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLoader)


        cryptoCurrencyPresenter.updateCurrencyList();

        swipeRefreshLayout.setOnRefreshListener {
            cryptoCurrencyPresenter.updateCurrencyList()
            swipeRefreshLayout.isRefreshing = false
        }

        searchCurrencyList.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                cryptoCurrencyPresenter.filterCurrencyList(s.toString(), currencyDataList);
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

    }

    override fun updateCurrencyList(currencyList: ArrayList<currencyDataListModel>) {
        currencyListAdapter.updateList(currencyList)
    }

    override fun setPresenter(presenter: CryptoCurrencyContract.presenter) {
        this.cryptoCurrencyPresenter = presenter
    }


    override fun setCurrencyList(currencyListResponse: ArrayList<currencyDataListModel>) {
        currencyListAdapter = currencyListAdapter(currencyListResponse)
        currencyDataList = currencyListResponse
        val layoutManager = LinearLayoutManager(applicationContext)
        currencyList.layoutManager = layoutManager
        currencyList.itemAnimator = DefaultItemAnimator()
        currencyList.adapter = currencyListAdapter
        currencyListAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        cryptoCurrencyPresenter.onDestroy()
        super.onDestroy();
    }

}
