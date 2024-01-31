package com.example.categorydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ThreeTYpeHolder> {
    private final Context context;
    private final List<String> list;

    public DetailAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ThreeTYpeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_main_details,parent,false);
        return new ThreeTYpeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeTYpeHolder holder, int position) {
        holder.type.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThreeTYpeHolder extends RecyclerView.ViewHolder{
        TextView type;
        ImageView icon;
        public ThreeTYpeHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
