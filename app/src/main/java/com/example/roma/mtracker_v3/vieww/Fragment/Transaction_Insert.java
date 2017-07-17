package com.example.roma.mtracker_v3.vieww.Fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;

import io.codetail.widget.RevealLinearLayout;


public class Transaction_Insert extends Fragment {

    private TextView value;
    private TextView n1;
    private TextView n2;
    private TextView n3;
    private TextView n4;
    private TextView n5;
    private TextView n6;
    private TextView n7;
    private TextView n8;
    private TextView n9;
    private TextView n0;
    private ImageView backspace;


//    private OnFragmentInteractionListener mListener;

    public Transaction_Insert() {
        // Required empty public constructor
    }


    public static Transaction_Insert newInstance(String param1, String param2) {
        Transaction_Insert fragment = new Transaction_Insert();
        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_fragment_transaction_insert, container, false);

        initAllViews(view);
        initAllListeners(view);
        return view;
    }


    private void initAllViews(View view) {


        n1 = (TextView) view.findViewById(R.id.n1);
        n2 = (TextView) view.findViewById(R.id.n2);
        n3 = (TextView) view.findViewById(R.id.n3);
        n4 = (TextView) view.findViewById(R.id.n4);
        n5 = (TextView) view.findViewById(R.id.n5);
        n6 = (TextView) view.findViewById(R.id.n6);
        n7 = (TextView) view.findViewById(R.id.n7);
        n8 = (TextView) view.findViewById(R.id.n8);
        n9 = (TextView) view.findViewById(R.id.n9);
        n0 = (TextView) view.findViewById(R.id.n0);
        backspace = (ImageView) view.findViewById(R.id.button_backspace);



        value = (TextView) view.findViewById(R.id.value_transaction_insert);




    }


    private void initAllListeners(View view) {

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "1");

            }
        });

        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "2");
            }
        });

        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "3");
            }
        });

        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "4");
            }
        });

        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "0");
            }
        });

        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "5");
            }
        });

        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "9");
            }
        });

        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "6");
            }
        });

        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "7");
            }
        });

        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + "8");
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText().subSequence(0, value.getText().length()-1));
            }
        });


    }

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
