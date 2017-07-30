package com.example.roma.mtracker_v3.vieww.Dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.roma.mtracker_v3.R;

import java.util.Date;

import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_DELETE;
import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_STATUS_COMPLETE;
import static com.example.roma.mtracker_v3.Adapters.TransactionAdapter.TRANSACTION_ITEM_STATUS_TIME;
import static com.example.roma.mtracker_v3.vieww.Dialog.DatePicker.ARG_DATE;


public class StatusPicker extends DialogFragment {
    public static final String ARG_STATUS = "statusResult";
    public static final int REQUEST_DATE_FROM_STATUS_PICKER = 11;

    Date date;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.status_picker, container, false);
        getDialog().setTitle("Статус платежа");

        initDeleteButton(view);
        initTimeButton(view);
        initCompleteButton(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.v("DAFJNDSLHFDSB", "RESUME");

        if (date != null) {


        }
    }

    private void initTimeButton(View view) {
        ImageButton timeButton = (ImageButton) view.findViewById(R.id.time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();

            }
        });
    }

    private void showDatePicker() {
        DatePicker datepicker = new DatePicker();
        datepicker.setTargetFragment(this, REQUEST_DATE_FROM_STATUS_PICKER);
        datepicker.show(getChildFragmentManager(), "datePickerFromStatusPicker");
    }

    private void initDeleteButton(View view) {
        ImageButton failedButton = (ImageButton) view.findViewById(R.id.delete_button);
        failedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResultStatus(Activity.RESULT_OK, TRANSACTION_ITEM_DELETE);
                getDialog().dismiss();
            }
        });

    }

    private void initCompleteButton(View view) {
        ImageButton completeButton = (ImageButton) view.findViewById(R.id.complete_button);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResultStatus(Activity.RESULT_OK, TRANSACTION_ITEM_STATUS_COMPLETE);
                getDialog().dismiss();
            }
        });
    }


    private void
    sendResultStatus(int resultCode, int status) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ARG_STATUS, status);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }
    private void sendResultStatusAndDate(int resultCode, int status, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ARG_STATUS, status);
        intent.putExtra(ARG_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE_FROM_STATUS_PICKER) {
            date = (Date) data.getSerializableExtra(ARG_DATE);
            Log.v("DAFJNDSLHFDSB", "" + date);
            sendResultStatusAndDate(Activity.RESULT_OK, TRANSACTION_ITEM_STATUS_TIME, date);
            getDialog().dismiss();
        }
    }
}
