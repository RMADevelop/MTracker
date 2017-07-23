package com.example.roma.mtracker_v3.vieww.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roma.mtracker_v3.Adapters.MonthThisAdapter;
import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.Item;
import com.example.roma.mtracker_v3.model.SpinnerMonthThis;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.roma.mtracker_v3.model.DateCustomChanger.*;
import static com.example.roma.mtracker_v3.model.SpinnerMonthThis.*;


public class Page_Month_This extends Fragment {

    private TextView incomeValue;
    private TextView outcomeValue;
    private TextView totalValue;
    private TextView monthHeader;

    private RecyclerView mRecyclerView;
    private ArrayList<Item> items;

    private Spinner spinnerMonthChooser;
    private Realm mRealm;
    private RealmResults<Item> realmResult;

    DateCustomChanger dateCustom = new DateCustomChanger();
    MonthThisAdapter adapter;
    SpinnerMonthThis spinner;

    public Page_Month_This() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_this, container, false);
        mRealm = Realm.getDefaultInstance();

initHeader(view);
        initRecyclerView(view);
        initSpinner(view);
//        Log.v("REALMTEST","initS" + realmResult.size() );

        adapter = new MonthThisAdapter(realmResult);
//        adapter.setResult(realmResult);


        mRecyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.setArray();
        adapter.setCounter();

        mRecyclerView.setAdapter(new MonthThisAdapter(realmResult));
    }

    private void initSpinner(View view) {
        spinnerMonthChooser = (Spinner) view.findViewById(R.id.spinner_month_this);
        spinner = new SpinnerMonthThis(spinnerMonthChooser);

        realmResult = spinner.getRealmResult(mRealm, 0);
        spinnerMonthChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                realmResult = null;
                realmResult = spinner.getRealmResult(mRealm, position);
                fillHeader(realmResult);

                switch (position) {
                    case MONTH_THIS:
                        adapter = new MonthThisAdapter(realmResult);
                        initMonthHeader(MONTH_THIS);
                        break;
                    case MONTH_PREV:
                        initMonthHeader(MONTH_PREV);
                        adapter = new MonthThisAdapter(realmResult, "s");
                        break;
                }


                mRecyclerView.setAdapter(adapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                realmResult = spinner.getRealmResult(mRealm, 0);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initMonthHeader(int id) {
        String dateHeader = null;
        switch (id) {
            case MONTH_THIS:
                dateCustom.setDateNow();
                dateHeader = String.format("%s %d", dateCustom.getMonth(MONTH_FULL), dateCustom.getYear());
                break;
            case MONTH_PREV:
                dateCustom.setDatePrev();
                dateHeader = String.format("%s %d", dateCustom.getMonth(MONTH_FULL), dateCustom.getYear());
                break;
        }
        monthHeader.setText(dateHeader);
    }


    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_month_this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

    private void initHeader(View view) {

        incomeValue = (TextView) view.findViewById(R.id.incomeValueFE);
        outcomeValue = (TextView) view.findViewById(R.id.outcomeValueFE);
        totalValue = (TextView) view.findViewById(R.id.totalValueFE);

        monthHeader = (TextView) view.findViewById(R.id.month_header_page_month_this);


    }

    private void fillHeader(RealmResults<Item> result) {
        incomeValue.setText(getIncomeValue(result));
        outcomeValue.setText("-" + getOutcomeValue(result));
        totalValue.setText(getSumma(result));
    }

    private String getOutcomeValue(RealmResults<Item> result) {
        int value = 0;

        for (Item item : result) {
            if (item.getPl_mn() == 0) {
                value += item.getValue();
            }
        }
        return String.valueOf(value);
    }

    private String getIncomeValue(RealmResults<Item> result) {
        int value = 0;
        if (result.size() != 0) {
            for (Item item : result) {
                if (item.getPl_mn() == 1) {
                    value += item.getValue();
                }
            }
        }
        return String.valueOf(value);
    }


    private String getSumma(RealmResults<Item> result) {
        int summa = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getPl_mn() == 1) {
                summa += result.get(i).getValue();
            } else {
                summa -= result.get(i).getValue();
            }
        }
        return String.valueOf(summa);
    }


}
