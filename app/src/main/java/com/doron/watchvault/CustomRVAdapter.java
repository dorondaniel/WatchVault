package com.doron.watchvault;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomRVAdapter extends RecyclerView.Adapter<CustomRVAdapter.ViewHolder> {
    public CustomRVAdapter(Context context, ) {
    }

    @NonNull
    @Override
    public CustomRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRVAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
                    //TODO
                }
            });
        }
    }
}
