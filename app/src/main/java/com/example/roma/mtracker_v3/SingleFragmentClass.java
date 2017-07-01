package com.example.roma.mtracker_v3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.roma.mtracker_v3.vieww.FragmentInsertDescription;

/**
 * Created by Roma on 28.05.2017.
 */


public abstract class SingleFragmentClass extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_host);

        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_host, fragment)
                    .commit();
        }
    }
    public void nextFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new FragmentInsertDescription();
        fragmentManager.beginTransaction().replace(R.id.fragment_host, fragment).addToBackStack(null).commit();

    }

}









