package vieww;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.roma.mtracker_v3.R;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;
    Fragment mFragment;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Realm.init(this);

        initToolbar();
        initFragment();









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

    }

    private void initFragment() {
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


    public void IntentToAdd() {
        Intent intent = new Intent(this, ActivityAddEntry.class);
        startActivity(intent);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

}
