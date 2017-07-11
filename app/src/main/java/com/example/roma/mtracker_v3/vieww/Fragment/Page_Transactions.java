package com.example.roma.mtracker_v3.vieww.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.mtracker_v3.Adapters.TransactionAdapter;
import com.example.roma.mtracker_v3.R;


public class Page_Transactions extends Fragment {


    private RecyclerView mRecyclerView;

    public Page_Transactions() {
        // Required empty public constructor
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_page_transaction, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_page_transaction);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TransactionAdapter adapter = new TransactionAdapter();
        mRecyclerView.setAdapter(adapter);
        return view;
    }

}
