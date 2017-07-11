package com.example.roma.mtracker_v3.vieww.Dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Roma on 09.07.2017.
 */


public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

   public static final String ARG_DATE = "date";
    Date mDate;

//    public static DatePicker newInstance() {
//        Bundle bundle = new Bundle();
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog();
//
//
//        return
//    }


    public static DatePicker newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DATE, date);

        DatePicker fragment = new DatePicker();
        fragment.setArguments(bundle);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

//        Button button = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendResult(Activity.RESULT_OK, mDate);
//            }
//        });

        return new DatePickerDialog(getActivity(), this, year, month, day);


    }


    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        mDate = calendar.getTime();

        sendResult(Activity.RESULT_OK, mDate);

    }


    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(ARG_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }


}
