package com.example.roma.mtracker_v3.vieww.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.Converter;
import com.example.roma.mtracker_v3.model.ConverterSpinnerImageManager;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.SPreferenceManager;
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

import static com.example.roma.mtracker_v3.model.SPreferenceManager.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Converter extends Fragment {

    private TextView USDinRUB;
    private TextView EURinRUB;
    private TextView GBPinRUB;
    private TextView currencyDate;

    TextView valueIn;
    TextView valueOut;

    ImageButton convertButton;

    private ImageView imageIN;
    private ImageView imageOUT;

    private Button update;
    private RotateLoading animViewUpdate;
    private LinearLayout currencyLayout;
    private DateCustomChanger dateCustom;

    Spinner spinnerIn;
    Spinner spinnerOut;

    private float resultConvert;
    private float RUBinUSD;
    private float RUBinEUR;
    private float RUBinGBP;

    SPreferenceManager shPrefManager;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_converter, container, false);
        initSharedPreferenceManager(getActivity());

        initCurrencyField(view);
        initUpdateViews(view);

        view.setTag("tagView");
        initValueIn(view);
        initSpinners(view);
        initConvertLogicAndButton(view);
        return view;
    }

    private void initSharedPreferenceManager(Context context) {
        shPrefManager = new SPreferenceManager(context);
    }

    private void initConvertLogicAndButton(View view) {

        convertButton = (ImageButton) view.findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConvertValueOUT();
            }
        });

    }

    private void getConvertValueOUT() {
        Converter converter = new Converter(RUBinUSD, RUBinEUR, RUBinGBP);

        Log.v("CurrencyFloat", spinnerIn.getSelectedItemPosition() + " " + Float.parseFloat(valueIn.getText().toString()) + " " + spinnerOut.getSelectedItemPosition());
        resultConvert = converter.convert(spinnerIn.getSelectedItemPosition(), Float.parseFloat(valueIn.getText().toString()), spinnerOut.getSelectedItemPosition());
        valueOut.setText(trimerNumberAndReturnString(resultConvert));
    }

    private void initSpinners(View view) {
        final ConverterSpinnerImageManager imageM = new ConverterSpinnerImageManager();

        imageIN = (ImageView) view.findViewById(R.id.image_currency_IN_converter);
        imageOUT = (ImageView) view.findViewById(R.id.image_currency_OUT_converter);

        spinnerIn = (Spinner) view.findViewById(R.id.spinner_IN_converter);
        spinnerIn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("spinnerLOG", "click item: " + position);
                setImageSpinner(imageIN, position, imageM);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOut = (Spinner) view.findViewById(R.id.spinner_OUT_converter);
        spinnerOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("spinnerLOG", "click item: " + position);
                setImageSpinner(imageOUT, position, imageM);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setImageSpinner(ImageView imageView, int position, ConverterSpinnerImageManager imageM) {
        imageView.setImageResource(imageM.getImage(position));


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

        if (!shPrefManager.getCurrencyDate(PREF_DATE).isEmpty()) {
            Log.v("retrofitTEST", "" + trimerNumberAndReturnString(shPrefManager.getCurrencyValue(PREF_USD)));

            USDinRUB.setText(trimerNumberAndReturnString(shPrefManager.getCurrencyValue(PREF_USD)));
            EURinRUB.setText(trimerNumberAndReturnString(shPrefManager.getCurrencyValue(PREF_EUR)));
            GBPinRUB.setText(trimerNumberAndReturnString(shPrefManager.getCurrencyValue(PREF_GBP)));

            currencyDate.setText(shPrefManager.getCurrencyDate(PREF_DATE));
        }

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


                    String dateUpdateCurrency = new SimpleDateFormat("dd.MM.yyyy").format(new Date(response.body().getTimestamp() * 1000L));
                    currencyDate.setText(dateUpdateCurrency);

                    shPrefManager.setInfoCurrencyUpdate(RUBinUSD, RUBinEUR, RUBinGBP, dateUpdateCurrency);

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


    private void updateCurrency(DataCurrency.Quotes quotes) {
        RUBinUSD = getRUBinUSD(quotes);
        RUBinEUR = getRUBnEUR(quotes);
        RUBinGBP = getRUBinGBP(quotes);

        Log.v("currencyValue", " " + RUBinUSD + " " + RUBinEUR + " " + RUBinGBP);
        USDinRUB.setText(trimerNumberAndReturnString(RUBinUSD));
        EURinRUB.setText(trimerNumberAndReturnString(RUBinEUR));
        GBPinRUB.setText(trimerNumberAndReturnString(RUBinGBP));
    }


    private float getRUBinUSD(DataCurrency.Quotes quotes) {

        return RUBinUSD = quotes.getUSDRUB();
    }

    private float getRUBnEUR(DataCurrency.Quotes quotes) {
        return RUBinEUR = (quotes.getUSDRUB() / quotes.getUSDEUR());
    }

    private float getRUBinGBP(DataCurrency.Quotes quotes) {
        return RUBinGBP = quotes.getUSDRUB() / quotes.getUSDGBP();
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
        valueIn = (TextView) view.findViewById(R.id.value_in_converter);
        valueIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.valueFieldClickListener();
            }
        });

        valueOut = (TextView) view.findViewById(R.id.value_out_converter);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
