package com.example.categorydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TwoTypeAdapter extends RecyclerView.Adapter<TwoTypeAdapter.TwoTypeHolder> {
    private Context context;
    private List<String> name;
    private List<String> detail;

    public TwoTypeAdapter(Context context, List<String> name, List<String> detail) {
        this.context = context;
        this.name = name;
        this.detail = detail;
    }

    @NonNull
    @Override
    public TwoTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_main_right,parent,false);
        return new TwoTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoTypeHolder holder, int position) {
        holder.type.setText(name.get(position));
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "更多", Toast.LENGTH_SHORT).show();
            }
        });
        holder.recyclerView.setAdapter(new DetailAdapter(context, detail));
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public static class TwoTypeHolder extends RecyclerView.ViewHolder{
        TextView type,more;
        RecyclerView recyclerView;
        public TwoTypeHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            more = itemView.findViewById(R.id.more);
            recyclerView = itemView.findViewById(R.id.right_recycle);
        }
    }
}
