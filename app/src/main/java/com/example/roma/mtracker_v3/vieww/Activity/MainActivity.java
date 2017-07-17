package com.example.roma.mtracker_v3.vieww.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.roma.mtracker_v3.Adapters.TabLayoutAdapter;
import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.vieww.Fragment.Page_Converter;

import io.realm.Realm;

import static android.R.attr.fragment;
import static com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry.FRAGMENT_ADD_CONVERTER;
import static com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry.FRAGMENT_ADD_TRANSACTION;
import static com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry.FRAGMENT_ADD_VALUE;
import static com.example.roma.mtracker_v3.vieww.Activity.Activity_Add_Entry.FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity implements Page_Converter.OnClickConvertedListener {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DefaultTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Realm.init(this);

        initToolbar();
        initDrawerLayout();

        initTabs();


    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.container);
        TabLayoutAdapter adapter = new TabLayoutAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(viewPager);


    }

    private void showNotificationTab() {
        viewPager.setCurrentItem(0);
    }

    private void showArchive() {
        viewPager.setCurrentItem(1);
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.this_month:
                        showNotificationTab();
                        break;
                    case R.id.archive:
                        showArchive();
                        break;
                }

                return true;
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.TitleThisMonth);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_add:
//                        IntentToAdd();
//                        break;
//                    case R.id.menu_add_transaction:                  ТАК НЕ РАБОТАЕТ!!!
//                        IntentToTransaction();
//                        break;
//                }
//                return true;
//            }
//        });

        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_add:
                        IntentToAdd(FRAGMENT_ADD_VALUE);
                        break;
                    case R.id.menu_add_transaction:
                        IntentToAdd(FRAGMENT_ADD_TRANSACTION);
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void IntentToAdd(String fragmentTag) {
        Intent intent = new Intent(this, Activity_Add_Entry.class);
        intent.putExtra(FRAGMENT_TAG, fragmentTag);

        if (fragmentTag.equals(FRAGMENT_ADD_CONVERTER)) {
            startActivityForResult(intent, 1);
            Log.v("TESTTEST", "activiryForResult");
            return;
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String value = data.getStringExtra("value");
            View view =  viewPager.findViewWithTag("tagView");
            TextView textView = (TextView) view.findViewById(R.id.value_in_converter);

            textView.setText(value);
        }

    }

    @Override
    public void valueFieldClickListener() {
        IntentToAdd(FRAGMENT_ADD_CONVERTER);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
