package com.example.roma.mtracker_v3.vieww.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.retrofit.Controller;
import com.example.roma.mtracker_v3.model.retrofit.Currency;
import com.example.roma.mtracker_v3.model.retrofit.CurrencyAPI;
import com.example.roma.mtracker_v3.model.retrofit.DataCurrency;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Converter extends Fragment {

    private TextView USDinRUB;
    private TextView EURinRUB;
    private TextView GBPinRUB;
    private TextView currencyDate;


    public interface OnClickConvertedListener {
        void valueFieldClickListener();
    }

    public OnClickConvertedListener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickConvertedListener) {
            listener = (OnClickConvertedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClickConvertedListener");
        }
    }

    public Page_Converter() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_converter, container, false);
        initCurrencyField(view);


        view.setTag("tagView");
        initValueIn(view);
        return view;
    }

    private void initCurrencyField(View view) {
        USDinRUB = (TextView) view.findViewById(R.id.currencyUSD_valueRUB_converter);
        EURinRUB = (TextView) view.findViewById(R.id.currencyEUR_valueRUB_converter);
        GBPinRUB = (TextView) view.findViewById(R.id.currencyGBR_valueRUB_converter);

        currencyDate = (TextView) view.findViewById(R.id.currencyDate);
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("retrofitTEST", "TEST");

        List<Currency> ratestArray = new ArrayList<>();

        if (getArguments() != null) {

        }


        Controller.createService((CurrencyAPI.class)).getData(Controller.API_KEY, Controller.API_CUR).enqueue(new Callback<DataCurrency>() {
            @Override
            public void onResponse(Call<DataCurrency> call, Response<DataCurrency> response) {
                Log.v("retrofitTEST", ""+ response.body().getQuotes().getUSDRUB());

            }

            @Override
            public void onFailure(Call<DataCurrency> call, Throwable t) {

            }
        });

//        Controller.createService(CurrencyAPI.class).getData("RUB", "USD,EUR,GBP").enqueue(new Callback<Currency>() {
//            @Override
//            public void onResponse(Call<Currency> call, Response<Currency> response) {
//                if (response.isSuccessful()) {
//                    Log.v("retrofitTEST", response.body().getRates().getEUR() + " ");
//
//                    updateCurrency(response.body().getRates());
//                    updateReverseDate(response.body().getDate());
//
//                }
//            }
//
//
//
//            @Override
//            public void onFailure(Call<Currency> call, Throwable t) {
//
//            }
//        });
    }
    private void updateCurrency(Currency.Rates rates) {
        float currencyUSDafterResponce = 1 / rates.getUSD();
        String USD = String.format("%.2f", currencyUSDafterResponce);
        USDinRUB.setText(USD);

        float currencyEURafterResponce = 1 / rates.getEUR();
        String EUR = String.format("%.2f", currencyEURafterResponce);
        EURinRUB.setText(EUR);

        float currencyGBPafterResponce = 1/ rates.getGBP();
        String GBP = String.format("%.2f", currencyGBPafterResponce);
        GBPinRUB.setText(GBP);

    }

    private void updateReverseDate(String date) {
        StringBuilder dateNormallyFormat = new StringBuilder();
        dateNormallyFormat.append(date.substring(8)).append(".").append(date.substring(5,7)).append(".").append(date.substring(0,4));

        currencyDate.append(dateNormallyFormat);
    }

    private void initValueIn(View view) {
        TextView valueIn = (TextView) view.findViewById(R.id.value_in_converter);
        valueIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.valueFieldClickListener();
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
