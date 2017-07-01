package com.example.roma.mtracker_v3.vieww;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;
import com.example.roma.mtracker_v3.model.InsertDescription;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInsertDescription extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> imagesArray;
    private InsertDescription insertDescription;

    public FragmentInsertDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_description, container, false);

        insertDescription = new InsertDescription();
        insertDescription.initArrayImages();


        initAllViews(view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(new RecyclerAdapter());

        return view;
    }



    private void initAllViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_insert_description);

    }


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView mImageView;
            public TextView description;

            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.description_image);
                description = (TextView) itemView.findViewById(R.id.description_text);
            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.recycler_insert_description_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            ImageView imageView = holder.mImageView;
            imageView.setImageResource(insertDescription.getArrayImages().get(position).getImageId());

            TextView descriptionText = holder.description;
            descriptionText.setText(insertDescription.getArrayImages().get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return insertDescription.getArrayImages().size();
        }
    }
}




