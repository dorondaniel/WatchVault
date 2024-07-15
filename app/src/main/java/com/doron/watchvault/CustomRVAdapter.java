package com.doron.watchvault;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doron.watchvault.network.models.RVModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomRVAdapter extends RecyclerView.Adapter<CustomRVAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<RVModel> rvModels;

    public CustomRVAdapter(Context context, ArrayList<RVModel> rvModels) {
        this.context = context;
        this.rvModels = rvModels;
    }

    @NonNull
    @Override
    public CustomRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRVAdapter.ViewHolder holder, int position) {
        RVModel rvm = rvModels.get(position);
        Log.d("CustomRVAdapter", "Binding view for position: " + position + ", Title: " + rvm.getTitle());
        holder.mv_name.setText(rvm.getTitle());
        Picasso.get()
                .load(rvm.getImageUrl())
                .resize(250,300)
                .into(holder.mvposter);
        Log.d("CustomRVAdapter", "Image URL: " + rvm.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return rvModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView mv_name;
        private final ImageView mvposter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mv_name = itemView.findViewById(R.id.mv_name);
            mvposter = itemView.findViewById(R.id.poster);

            //CardView itemClick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int pos = getAdapterPosition();
                    RVModel rvModel = rvModels.get(pos);
                    Toast.makeText(context, rvModel.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
