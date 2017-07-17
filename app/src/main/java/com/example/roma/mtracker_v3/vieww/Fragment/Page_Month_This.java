package com.example.roma.mtracker_v3.vieww.Fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roma.mtracker_v3.Adapters.MonthThisAdapter;
import com.example.roma.mtracker_v3.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

import com.example.roma.mtracker_v3.model.Item;


public class Page_Month_This extends Fragment {

    private FloatingActionButton fab;

    private TextView incomeValue;
    private TextView outcomeValue;
    private TextView totalValue;

    private RecyclerView mRecyclerView;
    private ArrayList<Item> items;
    private Realm mRealm;

    public Page_Month_This() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_this, container, false);
        initFab(view);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_month_this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);

            }
        });

        mRealm = Realm.getDefaultInstance();

        initHeader(view);

        MonthThisAdapter adapter = new MonthThisAdapter();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initFab(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab_month_this);
    }

    private void initHeader(View view) {
        incomeValue = (TextView) view.findViewById(R.id.incomeValueFE);
        outcomeValue = (TextView) view.findViewById(R.id.outcomeValueFE);
        totalValue = (TextView) view.findViewById(R.id.totalValueFE);


        incomeValue.setText(getIncomeValue());
        outcomeValue.setText("-" + getOutcomeValue());
        totalValue.setText(getSumma());
    }

    private String getOutcomeValue() {
        int value = 0;
        RealmResults<Item> result = mRealm.where(Item.class).findAll();

        for (Item item : result) {
            if (item.getPl_mn() == 0) {
                value += item.getValue();
            }
        }
        return String.valueOf(value);
    }

    private String getIncomeValue() {
        int value = 0;
        RealmResults<Item> result = mRealm.where(Item.class).findAll();

        for (Item item : result) {
            if (item.getPl_mn() == 1) {
                value += item.getValue();
            }

        }
        return String.valueOf(value);

    }


    private String getSumma() {
        int summa = 0;
        RealmResults<Item> result = mRealm.where(Item.class).findAll();
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
