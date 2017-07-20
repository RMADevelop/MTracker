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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.Converter;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.retrofit.Controller;
import com.example.roma.mtracker_v3.model.retrofit.Currency;
import com.example.roma.mtracker_v3.model.retrofit.CurrencyAPI;
import com.example.roma.mtracker_v3.model.retrofit.DataCurrency;
import com.victor.loading.rotate.RotateLoading;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Converter extends Fragment {

    private TextView USDinRUB;
    private TextView EURinRUB;
    private TextView GBPinRUB;
    private TextView currencyDate;
    TextView valueOut;

    private Button update;
    private RotateLoading animViewUpdate;
    private LinearLayout currencyLayout;
    private DateCustomChanger dateCustom;


    private float RUB;
    private float USD;
    private float EUR;
    private float GBP;


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
        initUpdateViews(view);


        view.setTag("tagView");
        initValueIn(view);
        return view;
    }

    private void initUpdateViews(View view) {
        update = (Button) view.findViewById(R.id.button_update_converter);
        animViewUpdate = (RotateLoading) view.findViewById(R.id.anim_view);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrencyREST();
            }
        });
    }

    private void initCurrencyField(View view) {
        currencyLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        USDinRUB = (TextView) view.findViewById(R.id.currencyUSD_valueRUB_converter);
        EURinRUB = (TextView) view.findViewById(R.id.currencyEUR_valueRUB_converter);
        GBPinRUB = (TextView) view.findViewById(R.id.currencyGBR_valueRUB_converter);

        currencyDate = (TextView) view.findViewById(R.id.currencyDate);

    }

    private void getCurrencyREST() {
        setLoadingState();
        Controller.createService((CurrencyAPI.class)).getData(Controller.API_KEY, Controller.API_CUR).enqueue(new Callback<DataCurrency>() {
            @Override
            public void onResponse(Call<DataCurrency> call, Response<DataCurrency> response) {
                Log.v("retrofitTEST", "" + response.body().getQuotes().getUSDRUB());
                if (response.isSuccessful()) {
                    updateCurrency(response.body().getQuotes());
//                    updateDateText(response.body());

                    String simple = new SimpleDateFormat("dd.MM.yyyy").format(new Date(response.body().getTimestamp() * 1000L));
                    currencyDate.setText(simple);
                    animViewUpdate.stop();

                    disableLoadingState();

                }
            }

            @Override
            public void onFailure(Call<DataCurrency> call, Throwable t) {
                disableLoadingState();


            }
        });
    }

    public void setLoadingState() {
        animViewUpdate.start();
        currencyLayout.setVisibility(View.INVISIBLE);
    }

    public void disableLoadingState() {
        animViewUpdate.stop();
        currencyLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("retrofitTEST", "create");


        if (getArguments() != null) {

        }

    }


    private void updateCurrency(DataCurrency.Quotes quotes) {
        float RUBinUSD = getRUBinUSD(quotes);
        float RUBinEUR = getRUBnEUR(quotes);
        float RUBinGBP = getRUBinGBP(quotes);

        USDinRUB.setText(trimerNumberAndReturnString(RUBinUSD));
        EURinRUB.setText(trimerNumberAndReturnString(RUBinEUR));
        GBPinRUB.setText(trimerNumberAndReturnString(RUBinGBP));
    }


    private float getRUBinUSD(DataCurrency.Quotes quotes) {

        return USD = quotes.getUSDRUB();
    }

    private float getRUBnEUR(DataCurrency.Quotes quotes) {
        return EUR = (quotes.getUSDRUB() / quotes.getUSDEUR());
    }

    private float getRUBinGBP(DataCurrency.Quotes quotes) {
        return GBP = quotes.getUSDRUB() / quotes.getUSDGBP();
    }

    private String trimerNumberAndReturnString(float currency) {
        String trimCurrency = String.format("%.2f", currency);
        return trimCurrency;
    }

    private String getDateOfUpdate(DataCurrency dataCurrency) {
        dateCustom = new DateCustomChanger(dataCurrency.getTimestamp());

        String date = String.format("%s.%s.%d", dateCustom.getDay(), dateCustom.getMonth(), dateCustom.getCalendar().get(Calendar.YEAR));
        return date;

    }


    private void initValueIn(View view) {
        TextView valueIn = (TextView) view.findViewById(R.id.value_in_converter);
        valueIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.valueFieldClickListener();
            }
        });

        valueOut = (TextView) view.findViewById(R.id.value_out_converter);
        valueOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO конвертер надо создавать если преференсы не равны нулю или после реквеста
                Converter converter = new Converter(USD, EUR, GBP, RUB);
                final float result = converter.convert(Converter.IN_EUR, 3, Converter.OUT_USD);

                valueOut.setText(trimerNumberAndReturnString(result));

            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
