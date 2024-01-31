package com.example.categorydemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OneTypeAdapter extends RecyclerView.Adapter<OneTypeAdapter.OneTypeHolder> {
    private final Context context;
    private final List<String> list;
    public int selectedPosition = 0;//当前选择的下标
    private OnItemClickListener onItemClickListener;

    public OneTypeAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OneTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_main_left,parent,false);
        return new OneTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OneTypeHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.type.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClickListener(v,position);
                }
            }
        });
        if (position==selectedPosition){
            holder.itemView.setBackgroundColor(context.getColor(R.color.white));
            holder.type.setTextColor(context.getColor(R.color.colorAccent));
            holder.type.setTextSize(32);
        }
        else {
            holder.itemView.setBackgroundColor(context.getColor(R.color.flow));
            holder.type.setTextColor(context.getColor(R.color.black));
            holder.type.setTextSize(24);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OneTypeHolder extends RecyclerView.ViewHolder{
        TextView type;
        public OneTypeHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }
}
