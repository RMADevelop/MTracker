package com.example.roma.mtracker_v3.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.DateCustomChanger;
import com.example.roma.mtracker_v3.model.InsertDescription;
import com.example.roma.mtracker_v3.model.TransactionItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Roma on 10.07.2017.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolderTransaction> {
    private DateCustomChanger dateCustom  = new DateCustomChanger();


    private InsertDescription arrayInsertDescription = new InsertDescription();
    private ArrayList<InsertDescription> arrayImages;

    private Realm mRealm = Realm.getDefaultInstance();
    RealmResults<TransactionItem> result = mRealm.where(TransactionItem.class).findAll().sort("date",Sort.ASCENDING);

    public static class ViewHolderTransaction extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView category;
        public TextView description;
        public TextView valueT;
        public TextView date;

        public ViewHolderTransaction(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_transaction);
            category = (TextView) itemView.findViewById(R.id.category);
            description = (TextView) itemView.findViewById(R.id.detail_description);
            valueT = (TextView) itemView.findViewById(R.id.value_transaction);
            date = (TextView) itemView.findViewById(R.id.date_transaction);
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
    public void onBindViewHolder(ViewHolderTransaction holder, int position) {
        try {
            arrayImages = arrayInsertDescription.getArrayImages();

            dateCustom.setDate(result.get(position).getDate());

            holder.valueT.setText(String.valueOf(result.get(position).getValue()));
            holder.mImageView.setImageResource(arrayImages.get(result.get(position).getIdImages()).getImageId());
            holder.description.setText(result.get(position).getDescription());
            holder.category.setText(arrayImages.get(result.get(position).getIdImages()).getDescription());

            holder.date.setText(dateCustom.getDay()+ " "+dateCustom.getMonth());
        } catch (Exception e) {

        }



    }


    @Override
    public int getItemCount() {
        return result.size();
    }
}
