package vieww;

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
import android.view.Menu;
import android.view.MenuItem;

import com.example.roma.mtracker_v3.Adapters.TabLayoutAdapter;
import com.example.roma.mtracker_v3.R;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    private Fragment mFragmentRecycler;
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
        initFragments();

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
        viewPager.setCurrentItem(1);
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.this_month:
                        showNotificationTab();
                }
                return true;
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.TitleThisMonth);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_add:
                        IntentToAdd();
                        break;
                }
                return true;
            }
        });

        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_add:
                        IntentToAdd();
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

    private void initFragments() {
        fm = getSupportFragmentManager();
        mFragmentRecycler = fm.findFragmentById(R.id.container);
        if (mFragmentRecycler == null) {
            mFragmentRecycler = new FragmentMonthThis();
            fm.beginTransaction()
                    .add(R.id.container, mFragmentRecycler)
                    .commit();
        }
    }


    public void IntentToAdd() {
        Intent intent = new Intent(this, ActivityAddEntry.class);
        startActivity(intent);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
