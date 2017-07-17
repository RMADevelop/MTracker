package com.example.roma.mtracker_v3.vieww.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Page_Converter extends Fragment {


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



        view.setTag("tagView");
        initValueIn(view);
        return view;
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
