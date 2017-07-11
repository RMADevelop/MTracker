package com.example.roma.mtracker_v3.vieww.Activity;

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
import android.widget.Toast;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.SingleFragmentClass;

import java.util.Date;

import com.example.roma.mtracker_v3.model.Item;
import com.example.roma.mtracker_v3.model.TransactionItem;
import com.example.roma.mtracker_v3.vieww.Fragment.Month_this_Insert;
import com.example.roma.mtracker_v3.vieww.Fragment.Month_this_insert_Description;
import com.example.roma.mtracker_v3.vieww.Fragment.Transaction_Insert;
import com.example.roma.mtracker_v3.vieww.Fragment.Transaction;

import io.realm.Realm;

import static com.example.roma.mtracker_v3.vieww.Fragment.Month_this_insert_Description.*;

public class Activity_Add_Entry extends SingleFragmentClass implements Transaction.IdImageOnCategoryListener, Month_this_Insert.OnFragmentInssertListener, OnFragmentInsertDesciptionListener {

    public static final String FRAGMENT_TAG = "fragmentTag";
    public static final String FRAGMENT_ADD_VALUE = "value";
    public static final String FRAGMENT_ADD_TRANSACTION = "transaction";


    private String valueTransaction = null;
    private Button nextBtn;
    private int pl_mn = 1;
    int value = 0;
    private boolean onInsertDescription = false;

    private int idImages = -1;
    Realm realm;
//    private SectionsPagerAdapter mSectionsPagerAdapter;


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
                Log.v("FragmentTag", "" + getFragment().getClass());
                if (getSupportFragmentManager().findFragmentById(R.id.fragment_host) instanceof Month_this_Insert || getSupportFragmentManager().findFragmentById(R.id.fragment_host) instanceof Month_this_insert_Description) {
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

                        goTo();
                    } else if (onInsertDescription) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                Item item = realm.createObject(Item.class);
                                item.setValue(value);
                                item.setDate(new Date());
                                item.setPl_mn(pl_mn);
                                item.setIdImage(idImages);
                            }
                        });
                        goTo();

                    } else {
                        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_host);
                        TextView textView = (TextView) fragment.getView().findViewById(R.id.value);
                        String text = textView.getText().toString();
                        value = Integer.parseInt(text);
                        onInsertDescription = true;

                        nextFragment(newInstance(FRAGMENT_INSERT));
                    }
                } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_host) instanceof Transaction_Insert) {

                    Toast.makeText(Activity_Add_Entry.this, "Click", Toast.LENGTH_SHORT).show();

                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_host);
                    TextView textView = (TextView) fragment.getView().findViewById(R.id.value_transaction_insert);
                    String text = textView.getText().toString();

                    setValueTransaction(text);
                    getSupportFragmentManager().popBackStack();


                } else if (getSupportFragmentManager().findFragmentById(R.id.fragment_host) instanceof Transaction) {
                    final Transaction fragment = (Transaction) getSupportFragmentManager().findFragmentById(R.id.fragment_host);

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            TransactionItem transactionItem = realm.createObject(TransactionItem.class);
                            transactionItem.setValue(Integer.parseInt(fragment.getValueForRealm()));
                            transactionItem.setDescription(fragment.getDescriptionForRealm());
                            transactionItem.setDate(fragment.getDateForRealm());
                            transactionItem.setIdImages(fragment.getIdImageCategory());
                        }
                    });
                    goTo();
                }


//                    goTo();


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
    protected Fragment createFragment() {
        return getFragment();
    }

    public Fragment getFragment() {
        if (getTagForFragment().equals(FRAGMENT_ADD_TRANSACTION))
            return Transaction.newInstance();
        else if (getTagForFragment().equals(FRAGMENT_ADD_VALUE))
            return new Month_this_Insert();
        return null;
    }

    private String getTagForFragment() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        return bundle.getString(FRAGMENT_TAG);
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

    public void goTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public String getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(String valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public int getIdImages() {
        return idImages;
    }

    public void setIdImages(int idImages) {
        this.idImages = idImages;
    }

    @Override
    public void onFragmentInteraction(int i) {
        pl_mn = i;
    }

    @Override
    public void onFragmentInsertDescription(int idImages) {
        setIdImages(idImages);
    }

    @Override
    public void displayCategoryFragment() {
        nextFragmentWithBackStack(Month_this_insert_Description.newInstance(FRAGMENT_TRANSACTION));
    }

    @Override
    public void goToFragmentInsert() {
        nextFragmentWithBackStack(new Transaction_Insert());
    }
}
