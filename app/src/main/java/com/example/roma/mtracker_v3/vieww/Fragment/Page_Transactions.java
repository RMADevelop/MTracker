package com.example.roma.mtracker_v3.vieww.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.roma.mtracker_v3.Adapters.TransactionAdapter;
import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.RealmResultManager;
import com.example.roma.mtracker_v3.model.ResultsRealmForTransaction;
import com.example.roma.mtracker_v3.model.TotalMoneys;
import com.example.roma.mtracker_v3.model.TransactionItem;
import com.example.roma.mtracker_v3.vieww.Dialog.StatusPicker;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_DELETE;
import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_STATUS_COMPLETE;
import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_STATUS_TIME;


public class Page_Transactions extends Fragment implements TransactionAdapter.OnItemClickListener {

    public static final int REQUEST_STATUS = 0;

    private TextView valueTotalTransaction;
    private TextView valueCurrentTransaction;
    private DateCustomChanger dateCustom;
    private RecyclerView mRecyclerView;
    private int itemPosition;
    private boolean thisMonth = true;

    private CheckBox completeCB;
    private CheckBox failedCB;
    private Spinner spinner;

    private int anim_recycler = R.anim.recycle_item_animation;
    TotalMoneys mTotalMoneys;
    private TransactionAdapter adapter;
    private Realm realm = Realm.getDefaultInstance();
    ResultsRealmForTransaction mResultsRealmForTransaction = new ResultsRealmForTransaction(realm);
    private RealmResults<TransactionItem> realmResult;

    private RealmResultManager realmManager = new RealmResultManager(realm);


    public TransactionAdapter.OnItemClickListener listener;


    public Page_Transactions() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.scheduleLayoutAnimation();
    }

    public static Page_Transactions newInstance(String param1, String param2) {
        Page_Transactions fragment = new Page_Transactions();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_page_transaction, container, false);
        initCheckButtons(view);
        initRealmResults(realm);
        initSpinner(view);
        initHeader(view);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_transaction);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listener = this;
        adapter = new TransactionAdapter(listener, getActivity(), realmResult);

        mRecyclerView.setAdapter(adapter);

        runLayoutAnimation(anim_recycler);
        return view;
    }

    private void runLayoutAnimation(int anim) {
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), anim);
        mRecyclerView.setLayoutAnimation(controller);
        mRecyclerView.getAdapter().notifyDataSetChanged();
        mRecyclerView.scheduleLayoutAnimation();
    }

    private void initHeader(View view) {
        mTotalMoneys = new TotalMoneys(realm);
        valueTotalTransaction = (TextView) view.findViewById(R.id.future_transaction_value);
        valueCurrentTransaction = (TextView) view.findViewById(R.id.future_transaction_value_lost);
        setTotalValue();


    }

    private void setTotalValue() {
        int totalValue = mTotalMoneys.getTotalValue(thisMonth);
        valueTotalTransaction.setText(String.valueOf(totalValue));

        int currentTotalValue = mTotalMoneys.getTotalValueLost(thisMonth, failedCB.isChecked());
        valueCurrentTransaction.setText(String.valueOf(currentTotalValue));
    }

    private void initSpinner(View view) {
        spinner = (Spinner) view.findViewById(R.id.spinner_transaction);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    setThisMonth(true);
                    initRealmResults(realm);
                    adapter = new TransactionAdapter(listener, getActivity(), realmResult);
                    mRecyclerView.setAdapter(adapter);
                    setTotalValue();
                } else {
                    setThisMonth(false);
                    initRealmResults(realm);
                    adapter.notifyDataSetChanged();
                    adapter = new TransactionAdapter(listener, getActivity(), realmResult);
                    mRecyclerView.setAdapter(adapter);
                    setTotalValue();
                }
                mRecyclerView.scheduleLayoutAnimation();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private void initRealmResults(Realm realm) {
        realmResult = mResultsRealmForTransaction.getResultCustom(realm, isThisMonth(), completeCB.isChecked(), failedCB.isChecked());
        if (!completeCB.isChecked() && !failedCB.isChecked()) {
            realmResult = mResultsRealmForTransaction.getResultCommon(realm, isThisMonth());
        } else if (completeCB.isChecked() && failedCB.isChecked()) {
            realmResult = mResultsRealmForTransaction.getResultAll(realm, isThisMonth());
        }
        realmManager.setResultForManager(realmResult);
        realmManager.refreshStatusResults();
    }


    private void initCheckButtons(View view) {
        completeCB = (CheckBox) view.findViewById(R.id.complete_transaction);
        failedCB = (CheckBox) view.findViewById(R.id.failed_transaction);

        completeCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initRealmResults(realm);
                adapter = new TransactionAdapter(listener, getActivity(), realmResult);
                mRecyclerView.setAdapter(adapter);
                setTotalValue();
                mRecyclerView.scheduleLayoutAnimation();

            }
        });
        failedCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initRealmResults(realm);
                adapter = new TransactionAdapter(listener, getActivity(), realmResult);
                mRecyclerView.setAdapter(adapter);
                setTotalValue();
                mRecyclerView.scheduleLayoutAnimation();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_STATUS) {
            Log.v("status_pick", data.getExtras().getInt(StatusPicker.ARG_STATUS) + "");
            setStatusResult(data);
        }
        realmManager.refreshStatusResults();
        setTotalValue();
        adapter = new TransactionAdapter(listener, getActivity(), realmResult);
        mRecyclerView.setAdapter(adapter);
    }

    private void setStatusResult(Intent data) {
        switch (data.getExtras().getInt(StatusPicker.ARG_STATUS)) {
            case TRANSACTION_ITEM_STATUS_COMPLETE:
                setCompleteStatus();
                break;
            case TRANSACTION_ITEM_DELETE:
                deleteTransaction();
                break;
            case TRANSACTION_ITEM_STATUS_TIME:
                setNewTimeTransaction(data);
        }
    }

    private void setNewTimeTransaction(final Intent data) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResult.get(itemPosition).setDate((Date) data.getExtras().getSerializable("date"));
            }
        });
    }

    private void deleteTransaction() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResult.get(itemPosition).deleteFromRealm();
            }
        });
    }

    private void setCompleteStatus() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realmResult.get(itemPosition).setCompleteStatus(true);
            }
        });
    }


    @Override
    public void onItemClicked(int id, RealmResults<TransactionItem> results) {
        this.realmResult = results;
        this.itemPosition = id;
        FragmentManager fragmentManager = getChildFragmentManager();
        StatusPicker statusPicker = new StatusPicker();
        statusPicker.setTargetFragment(this, REQUEST_STATUS);
        statusPicker.show(fragmentManager, "statusPicker");
    }

    public boolean isThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(boolean thisMonth) {
        this.thisMonth = thisMonth;
    }
}
