package vieww;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;
import com.example.roma.mtracker_v3.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import model.Item;

/**
 * Created by Roma on 17.06.2017.
 */

public class MainAdapter extends SectionedRecyclerViewAdapter<MainAdapter.MainVH> {
    private Realm mRealm = Realm.getDefaultInstance();
    RealmResults<Item> result = mRealm.where(Item.class).findAll().sort("mDate", Sort.DESCENDING);

    int valueDay = 0;
    ArrayList<Integer> array = getCountItem();

    public ArrayList<Integer> getArray() {
        return array;
    }

    int counter = getCountSection(result);


    public int getCalendarItem(Item item) {
        Date date = item.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return day;
    }


    public int getCountSection(RealmResults<Item> result) {

        int countSection = 0;

        if (result.size() != 0) {
            countSection++;
            valueDay = getCalendarItem(result.get(0));
        }

        for (int i = 0; i < result.size(); i++) {
            if (getCalendarItem(result.get(i)) != valueDay) {
                countSection++;
                valueDay = getCalendarItem(result.get(i));
            }
        }
        Log.v("array", " countSection " + countSection);
        return countSection;
    }

    public ArrayList<Integer> getCountItem() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        try {
            int size = 0;
            valueDay = getCalendarItem(result.get(0));


            for (int i = 0; i < result.size(); i++) {
                if (getCalendarItem(result.get(i)) == valueDay) {
                    size++;
                } else {
                    valueDay = getCalendarItem(result.get(i));
                    arrayList.add(size);
                    size = 1;
                }
            }
            arrayList.add(size);

//            Log.v("array", "arraySize0 " + arrayList.size());
//            for (int i = 0; i < arrayList.size(); i++) {
//                Log.v("arrayI", "arrayI " + arrayList.get(i));
//            }
        } catch (Exception e) {

        }
        return arrayList;



    }

    public int getTotalHeader(int section) {
        int start = getCalendarItem(result.get(0));
        int size = 0;
        int totalValue = 0;

        for (int i = 0; i < result.size(); i++) {
            if (getCalendarItem(result.get(i)) == start && size == section) {
                totalValue += result.get(i).getValue();
                continue;
            }
            if (getCalendarItem(result.get(i)) != start) {
                size++;
                start = getCalendarItem(result.get(i));
                if (size == section) totalValue += result.get(i).getValue();
            }
        }
        return totalValue;
    }

    public int getDayHeader(int section) {
        int start = getCalendarItem(result.get(0));
        int size = 0;
        int day = 0;

        for (int i = 0; i < result.size(); i++) {
            if (getCalendarItem(result.get(i)) == start && size == section) {
                return getCalendarItem(result.get(i));
            } else {

                start--;
                if (getCalendarItem(result.get(i)) == start && size == section)
                    return getCalendarItem(result.get(i));

                if (getCalendarItem(result.get(i)) == start && size != section) {
                    size++;
                    if (size == section) return getCalendarItem(result.get(i));
                }
                if (getCalendarItem(result.get(i)) == start && size == section)
                    return getCalendarItem(result.get(i));
            }
        }
        return 1;

    }


    @Override
    public int getSectionCount() {

        return counter; // number of sections, you would probably base this on a data set such as a map
    }

    @Override
    public int getItemCount(int sectionIndex) {


        return array.get(sectionIndex); // number of items in section, you could also pull this from a map of lists
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section, boolean expanded) {
        try {
            holder.valueHeader.setText(Integer.toString(getTotalHeader(section)));
            holder.dateDayHeader.setText(Integer.toString(getDayHeader(section)));
        } catch (Exception e) {

        }


//        int start = 100;
//        int size = 0;
//        int total = 0;
//
//        for (int i = 0; i < result.size(); i++) {
//            if (size == section) {
//                total += result.get(i).getValue();
//            }
//            if (getCalendarItem(result.get(i)) != start) {
//                size++;
//                start = getCalendarItem(result.get(i));
//            }
//
//
//        }
    }

    @Override
    public void onBindFooterViewHolder(MainVH holder, int section) {

    }

    @Override
    public void onBindViewHolder(MainVH holder, int section, int relativePosition, int absolutePosition) {
        // Setup non-header view.
        // 'section' is section index.
        // 'relativePosition' is index in this section.
        // 'absolutePosition' is index out of all items, including headers and footers.
        // See sample project for a visual of how these indices work.
        Log.v("sectionNum", " " + section);
        holder.value.setText(Integer.toString(result.get(absolutePosition - section - 1).getValue()));
    }


    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        // Change inflated layout based on type
        int layoutRes;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layoutRes = R.layout.section_recycle_item_thismonth;
                break;
            default:
                layoutRes = R.layout.child_recycle_item_thismonth;
                break;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutRes, parent, false);
        return new MainVH(v);
    }

    public static class MainVH extends SectionedViewHolder {
        TextView description;
        TextView value;

        TextView valueHeader;
        TextView dateDayHeader;

        public MainVH(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.descriptionChild);
            value = (TextView) itemView.findViewById(R.id.valueChild);

            valueHeader = (TextView) itemView.findViewById(R.id.totalValueOnDay);
            dateDayHeader = (TextView) itemView.findViewById(R.id.dateText);
            // Setup view holder. You'd want some views to be optional, e.g. the
            // header/footer will have views that normal item views do or do not have.
        }


    }

}
