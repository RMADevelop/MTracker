package com.example.roma.mtracker_v3.vieww.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.InsertDescription;
import com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry;


public class Transaction extends Fragment {

    private int idImageCategory;

    private EditText descriptionTransaction;
    private EditText valueTransaction;
    private EditText dateTransaction;
    private ImageView imageCategory;
    private TextView textCategory;


    private IdImageOnCategoryListener mListener;


    public static Transaction newInstance() {
        Transaction fragment = new Transaction();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public Transaction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_insert, container, false);

        initHeader(view);
        initFields(view);
        initValueField(view);


        return view;
    }

    private void initHeader(View v) {
        textCategory = (TextView) v.findViewById(R.id.text_category);
        imageCategory = (ImageView) v.findViewById(R.id.category_fragment_transaction_insert);

        imageCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.displayCategoryFragment();
            }
        });
        if (onIdImage()) {
            bindHeader();
        }

    }

    private void bindHeader() {
        InsertDescription insertDescription = new InsertDescription();
        insertDescription.initArrayImages();

        imageCategory.setImageResource(insertDescription.getArrayImages().get(idImageCategory).getImageId());
        textCategory.setText(insertDescription.getArrayImages().get(idImageCategory).getDescription());
    }

    private void initFields(View v) {


        descriptionTransaction = (EditText) v.findViewById(R.id.description_fragment_transaction_insert);
        descriptionTransaction.setEnabled(false);

        dateTransaction = (EditText) v.findViewById(R.id.date_fragment_transaction_insert);
        dateTransaction.setEnabled(false);

    }

    private void initValueField(View v) {
        valueTransaction = (EditText) v.findViewById(R.id.value_fragment_transaction_insert);
        valueTransaction.setEnabled(true);

        if (valueTransaction.isEnabled()) {
            valueTransaction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.goToFragmentInsert();
                }
            });
        }
    }

    public void setIdImage(int idImage) {
        this.idImageCategory = idImage;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onIdImage()) {
            setIdImageCategory(getIdImageFromActivity());
        }
        Log.v("IDImage", "" + getIdImageCategory());


    }

    private boolean onIdImage() {
        Activity_Add_Entry activity = (Activity_Add_Entry) getActivity();
        if (activity.getIdImages() != -1) {
            setIdImageCategory(getIdImageFromActivity());
            return true;
        } else
            return false;
    }

    public int getIdImageFromActivity() {
        Activity_Add_Entry activity = (Activity_Add_Entry) getActivity();
        return activity.getIdImages();
    }

    public int getIdImageCategory() {
        return idImageCategory;
    }

    public void setIdImageCategory(int idImageCategory) {
        this.idImageCategory = idImageCategory;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IdImageOnCategoryListener) {
            mListener = (IdImageOnCategoryListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface IdImageOnCategoryListener {
        void displayCategoryFragment();
        void goToFragmentInsert();
    }
}
