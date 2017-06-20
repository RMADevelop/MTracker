package vieww;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.SingleFragmentClass;

import java.util.Date;

import model.Item;
import io.realm.Realm;
import io.realm.RealmResults;

public class ActivityAddEntry extends SingleFragmentClass implements FragmentInsert.OnFragmentInteractionListener {
    private Button nextBtn;
    private int pl_mn = 1;
    Realm realm;
//    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected Fragment createFragment() {
        return FragmentInsert.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();

        nextBtn = (Button) findViewById(R.id.button_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MSG", "click");
                if (pl_mn == 1) {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_host);
                    TextView textView = (TextView) fragment.getView().findViewById(R.id.value);
                    String text = textView.getText().toString();
                    final int value = Integer.parseInt(text);

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Item item = realm.createObject(Item.class);
                            item.setValue(value);
                            item.setDate(new Date());
                            item.setPl_mn(pl_mn);
                        }
                    });
                    RealmResults<Item> result = realm.where(Item.class).findAll();
                    Log.v("MSG", value + " " + result.size() + " " + pl_mn);
                    goTo();

                } else {
                    nextFragment();
                }
            }
        });


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goTo(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFragmentInteraction(int i) {
        pl_mn = i;
    }


//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            Toast.makeText(ActivityAddEntry.this, "test", Toast.LENGTH_SHORT).show();
//            return FragmentInsert.newInstance(position + 1);
//        }
//
//
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 2;
//        }
//
//        @Override
//        public String getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Добавить";
//                case 1:
//                    return "Вычесть";
//            }
//            return null;
//        }
//    }
}
