package vieww;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.roma.mtracker_v3.R;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Realm.init(this);



        fm = getSupportFragmentManager();
        mFragment = fm.findFragmentById(R.id.container);
        if (mFragment == null) {
            mFragment = new FragmentMonthThis();
            fm.beginTransaction()
                    .add(R.id.container, mFragment)
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                IntentToAdd();
                break;
        }
        return true;
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
