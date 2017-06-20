package vieww;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roma.mtracker_v3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInsertDescription extends Fragment {
    private RecyclerView mRecyclerView;

    public FragmentInsertDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_description, container, false);

        initAllViews(view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(new RecyclerVAdapter());

        return view;
    }


    private void initAllViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_insert_description);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description_description);
        }
    }

    public class RecyclerVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final int HEADER = 0, ELEMENTS = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.recycler_insert_description_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }


}
