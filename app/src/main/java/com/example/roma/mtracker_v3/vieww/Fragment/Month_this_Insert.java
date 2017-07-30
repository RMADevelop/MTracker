package com.example.roma.mtracker_v3.vieww.Fragment;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;

import java.util.Date;

import com.example.roma.mtracker_v3.model.Item;

import io.codetail.widget.RevealLinearLayout;

import static com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry.*;


public class Month_this_Insert extends Fragment {

    private Window mWindow;
    private Point revealCenter;
    private AppBarLayout mAppBarLayout2;
    private RevealLinearLayout circleLayout;
    private Toolbar mToolbar;
    private TextView incomeText;
    private TextView outcomeText;
    private TextView convertText;
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
    private TextView nTochka;
    private ImageView backspace;
    private int xC;
    private int yC;
    public int pl_mn = 1;
    private TextView minus;


    private int toolbarColors[];
    private Drawable[] arrayDrawable;

    private OnFragmentInssertListener mListener;

    public interface OnFragmentInssertListener {
        void onFragmentInteraction(int i);
    }


    public Month_this_Insert() {
        // Required empty public constructor
    }

    public static Month_this_Insert newInstance(String arg) {
        Month_this_Insert fragment = new Month_this_Insert();
        Bundle args = new Bundle();
        args.putString(FRAGMENT_TAG, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInssertListener) {
            mListener = (OnFragmentInssertListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInssertListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_in, container, false);


        initColors();
        initDrawables();
        initAllViews(view);

        showCurrentHeaeder();
        initAllListeners(view);

        return view;
    }

    private void showCurrentHeaeder() {

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            bundle.getString(FRAGMENT_TAG);
            if (bundle.getString(FRAGMENT_TAG) == FRAGMENT_ADD_CONVERTER) {
                showHeaderInsertAndHideConverter();

            }

        } else {
            showConvertHeaderAndHideInsert();
        }
    }

    private void showConvertHeaderAndHideInsert() {
        incomeText.setVisibility(View.VISIBLE);
        outcomeText.setVisibility(View.VISIBLE);

        convertText.setVisibility(View.INVISIBLE);
    }

    private void showHeaderInsertAndHideConverter() {
        incomeText.setVisibility(View.INVISIBLE);
        outcomeText.setVisibility(View.INVISIBLE);

        convertText.setVisibility(View.VISIBLE);
    }

    private void updateColors(int i) {
        revealToolbar();
        mAppBarLayout2.setBackground(arrayDrawable[i]);
//        mToolbar.setBackgroundColor(toolbarColors[i]);
        mWindow.setStatusBarColor(toolbarColors[i]);
    }

    private void revealToolbar() {
        revealCenter = new Point(xC, yC);
        Animator anim = ViewAnimationUtils.createCircularReveal(mAppBarLayout2,
                revealCenter.x, revealCenter.y,
                0, mToolbar.getWidth() * 2);
        anim.setDuration(1000).start();
    }

    private void initColors() {
        toolbarColors = new int[2];
        toolbarColors[0] = color(R.color.primary);
        toolbarColors[1] = color(R.color.red);
    }

    private int color(@ColorRes int res) {
        return ContextCompat.getColor(getContext(), res);
    }

    private Drawable drawable(@DrawableRes int res) {
        return ContextCompat.getDrawable(getContext(), res);
    }

    private void initDrawables() {
        arrayDrawable = new Drawable[2];
        arrayDrawable[0] = drawable(R.drawable.gradient_green);
        arrayDrawable[1] = drawable(R.drawable.gradient_red);
    }

    private void initAllViews(View view) {

        mWindow = getActivity().getWindow();
        mWindow.setStatusBarColor(toolbarColors[0]);

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
        nTochka = (TextView) view.findViewById(R.id.button__);
        backspace = (ImageView) view.findViewById(R.id.button_backspace);

        minus = (TextView) view.findViewById(R.id.minus);
        minus.setVisibility(View.INVISIBLE);

        value = (TextView) view.findViewById(R.id.value);
        incomeText = (TextView) view.findViewById(R.id.incomeText);
        outcomeText = (TextView) view.findViewById(R.id.outcomeText);
        convertText = (TextView) view.findViewById(R.id.convertText);

        circleLayout = (RevealLinearLayout) view.findViewById(R.id.layer2);
        mAppBarLayout2 = (AppBarLayout) view.findViewById(R.id.appbar2);
        mAppBarLayout2.setBackground(arrayDrawable[0]);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

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
        nTochka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText() + ".");
            }
        });


        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value.setText(value.getText().subSequence(0, value.getText().length() - 1));
            }
        });

        incomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xC = (int) v.getX();
                yC = (int) v.getY();
                pl_mn = 1;
                circleLayout.setBackgroundColor(toolbarColors[1]);
                updateColors(0);
                mListener.onFragmentInteraction(1);
                minus.setVisibility(View.INVISIBLE);
            }
        });

        outcomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xC = (int) v.getX();
                yC = (int) v.getY();
                pl_mn = 0;
                Log.v("MSGER", xC + " " + yC);
                circleLayout.setBackgroundColor(toolbarColors[0]);
                updateColors(1);
                mListener.onFragmentInteraction(0);
                minus.setVisibility(View.VISIBLE);

            }
        });
    }

    private Item buildItem(int value, int pl_mn, Date date) {
        Item item = new Item();
        item.setDate(date);
        item.setPl_mn(pl_mn);
        item.setValue(value);

        return item;
    }


//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        revealCenter = new Point((int)event.getX(),(int) event.getY());
//
//        return true;
//    }


    // TODO: Rename method, update argument and hook method into UI event


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


}