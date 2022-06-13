package com.example.bd_app_tfc_alberto.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvCalendarAdapter extends RecyclerView.Adapter<RvCalendarAdapter.ViewHolder>{

    private Context context;

    public RvCalendarAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public RvCalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RvCalendarAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
