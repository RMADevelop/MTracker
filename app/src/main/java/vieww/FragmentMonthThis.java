package vieww;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.example.roma.mtracker_v3.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import model.Item;


public class FragmentMonthThis extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Item> items;
    private Realm mRealm;

    public FragmentMonthThis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_this, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_month_this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRealm = Realm.getDefaultInstance();

        MainAdapter adapter = new MainAdapter();
        mRecyclerView.setAdapter(adapter);
        return view;
    }


//    public class ViewHolder1 extends RecyclerView.ViewHolder {
//        TextView summa;
//
//        public ViewHolder1(View itemView) {
//            super(itemView);
//            summa = (TextView) itemView.findViewById(R.id.totalValueFE);
//        }
//    }
//
//    public class ViewHolder2 extends RecyclerView.ViewHolder {
//        TextView date;
//
//        public ViewHolder2(View itemView) {
//            super(itemView);
//            date = (TextView) itemView.findViewById(R.id.child);
//        }
//    }
//
//    public class ViewHolder3 extends RecyclerView.ViewHolder {
//        TextView date;
//
//        public ViewHolder3(View itemView) {
//            super(itemView);
//            date = (TextView) itemView.findViewById(R.id.dateText);
//        }
//    }


//    public class ViewHolder1 extends RecyclerView.ViewHolder {
//        TextView summa;
//
//        public ViewHolder1(View itemView) {
//            super(itemView);
//            summa = (TextView) itemView.findViewById(R.id.totalValueFE);
//        }
//    }
//
//    public class ViewHolder2 extends RecyclerView.ViewHolder {
//        TextView date;
//
//        public ViewHolder2(View itemView) {
//            super(itemView);
//            date = (TextView) itemView.findViewById(R.id.child);
//        }
//    }
//
//    public class ViewHolder3 extends RecyclerView.ViewHolder {
//        TextView date;
//
//        public ViewHolder3(View itemView) {
//            super(itemView);
//            date = (TextView) itemView.findViewById(R.id.dateText);
//        }
//    }
//
//    public class RecyclerV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//        boolean firstEl = true;
//        int count = 0;
//
//        RealmResults<Item> result = mRealm.where(Item.class).findAll();
//
//
//        public RealmResults<Item> getResult() {
//            result = result.sort("mDate", Sort.DESCENDING);
//            return result;
//        }
//
//        public int getCalendarNow() {
//            Date date = new Date();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            int min = calendar.get(Calendar.MINUTE);
//
//            return min;
//        }
//
//        public int getCalendarItem(Item item) {
//            Date date = item.getDate();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            int min = calendar.get(Calendar.MINUTE);
//
//            return min;
//        }
//
//        int minuts = getCalendarNow();
//
//
//        private final int HEADER = 0, SECTION = 1, ELEMENTS = 2;
//
//        @Override
//        public int getItemViewType(int position) {
//
//
//            Log.v("result", "" + result.get(position) + " size " + result.size() + " count " + count);
//            if (position == 0) {
//                if (firstEl) {
//                    Log.v("counter", " " + count);
//                    count++;
//                    firstEl = false;
//                }
//
//                return HEADER;
//            }
//
//            if (minuts == getCalendarItem(result.get(position))) {
//                return ELEMENTS;
//            } else if (minuts != getCalendarItem(result.get(position))) {
////                count++;
//                minuts = getCalendarItem(result.get(position));
//                return SECTION;
//            }
//            return HEADER;
//
//        }
//
//
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            RecyclerView.ViewHolder viewHolder;
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//            if (viewType == HEADER) {
//                View view = inflater.inflate(R.layout.first_element, parent, false);
//                viewHolder = new ViewHolder1(view);
//                return viewHolder;
//            }
//            if (viewType == ELEMENTS) {
//                View view = inflater.inflate(R.layout.child_recycle_item_thismonth, parent, false);
//                viewHolder = new ViewHolder2(view);
//                return viewHolder;
//            } else {
//                View view = inflater.inflate(R.layout.section_recycle_item_thismonth, parent, false);
//                viewHolder = new ViewHolder3(view);
//                return viewHolder;
//            }
//
//        }
//
//        @Override
//        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//            if (holder.getItemViewType() == HEADER) {
//                ViewHolder1 viewHolder = (ViewHolder1) holder;
//                viewHolder.summa.setText(getSumma());
//            }
//            if (holder.getItemViewType() == ELEMENTS) {
//                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
//                viewHolder2.date.setText(String.valueOf(getResult().get(position).getValue()));
//            }
//            if (holder.getItemViewType() == SECTION) {
//                ViewHolder3 viewHolder3 = (ViewHolder3) holder;
//                viewHolder3.date.setText("test");
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            RealmResults<Item> result = mRealm.where(Item.class).findAll();
//            return result.size();
//        }
//    }


    private String getSumma() {
        int summa = 0;
        RealmResults<Item> result = mRealm.where(Item.class).findAll();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getPl_mn() == 1) {
                summa += result.get(i).getValue();
            } else {
                summa -= result.get(i).getValue();
            }
        }
        return String.valueOf(summa);
    }


}
