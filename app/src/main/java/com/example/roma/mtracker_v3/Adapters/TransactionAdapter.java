package com.example.roma.mtracker_v3.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.InsertDescription;
import com.example.roma.mtracker_v3.model.TransactionItem;
import com.example.roma.mtracker_v3.vieww.Fragment.Page_Transactions;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.example.roma.mtracker_v3.model.DateCustomChanger.*;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolderTransaction> {
    public static final int TRANSACTION_ITEM_STATUS_NONE = 0;
    public static final int TRANSACTION_ITEM_STATUS_COMPLETE = 1;
    public static final int TRANSACTION_ITEM_STATUS_TIME = 2;
    public static final int TRANSACTION_ITEM_DELETE = 3;

    private DateCustomChanger dateCustom = new DateCustomChanger();


    private InsertDescription arrayInsertDescription = new InsertDescription();
    private ArrayList<InsertDescription> arrayImages;

    private Realm mRealm = Realm.getDefaultInstance();
    RealmResults<TransactionItem> result;

    public interface OnItemClickListener {
        void onItemClicked(int id, RealmResults<TransactionItem> results);

    }

    private OnItemClickListener listener;

    Page_Transactions mPage_transactions;
    Context mContext;


    public TransactionAdapter(OnItemClickListener listener, Context context, RealmResults<TransactionItem> realmResult) {
        this.listener = listener;
        mContext = context;
        result = realmResult;
    }

    public class ViewHolderTransaction extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView category;
        public TextView description;
        public TextView valueT;
        public TextView date;
        public TextView indicator;

        public ViewHolderTransaction(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(getAdapterPosition(), result);
                }
            });

            mImageView = (ImageView) itemView.findViewById(R.id.image_transaction);
            category = (TextView) itemView.findViewById(R.id.category);
            description = (TextView) itemView.findViewById(R.id.detail_description);
            valueT = (TextView) itemView.findViewById(R.id.value_transaction);
            date = (TextView) itemView.findViewById(R.id.date_transaction);
            indicator=(TextView) itemView.findViewById(R.id.transaction_indicator);
        }
    }

    @Override
    public ViewHolderTransaction onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.child_recycler_transaction, parent, false);

        ViewHolderTransaction viewHolder = new ViewHolderTransaction(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderTransaction holder,  int position) {
        try {
            arrayImages = arrayInsertDescription.getArrayImages();

            dateCustom.setDate(result.get(position).getDate());



            holder.valueT.setText(String.valueOf(result.get(position).getValue()));
            holder.mImageView.setImageResource(arrayImages.get(result.get(position).getIdImages()).getImageId());
            holder.description.setText(result.get(position).getDescription());
            holder.category.setText(arrayImages.get(result.get(position).getIdImages()).getDescription());

            holder.date.setText(dateCustom.getDay() + " " + dateCustom.getMonth(MONTH_SHORT));
            if (result.get(position).isFailedStatus()) {
                holder.indicator.setBackgroundColor(Color.RED);
            }
            if (result.get(position).isCompleteStatus()) {
                holder.indicator.setBackgroundColor(Color.GREEN);
            }

            Log.v("StatusTransaction",  result.get(position).isCompleteStatus()+"   "+ result.get(position).isFailedStatus());
        } catch (Exception e) {

        }


    }


    @Override
    public int getItemCount() {
        return result.size();
    }


}

