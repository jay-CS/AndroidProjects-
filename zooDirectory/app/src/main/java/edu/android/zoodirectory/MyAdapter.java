package edu.android.zoodirectory;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> values;
    private List<Integer> img_values;
    final private animalViewClick itemListener;

    //DRAWABLE SHOULD BE A LIST OF INTEGER ID FROM RES
    public MyAdapter(List<String> myDataset, List<Integer> myDataSetImages, animalViewClick itemListener) {
        values = myDataset;
        img_values = myDataSetImages;
        this.itemListener = itemListener;
    }

    interface animalViewClick {

        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtHeader;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout=v;
            txtHeader=v.findViewById(R.id.firstLine);
            icon =v.findViewById(R.id.icon);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            itemListener.onItemClick(position);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new viewLayout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh= new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        // -get element from your dataset at this position
        // -replace the contents of the view with that element

        final String name = values.get(position);
        holder.icon.setImageResource(img_values.get(position));
        holder.txtHeader.setText(name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount(){
        return values.size();
    }

}


