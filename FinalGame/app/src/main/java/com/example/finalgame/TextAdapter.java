package com.example.finalgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {

    private ArrayList nom_l, punts_l;

    public TextAdapter(ArrayList nom_l, ArrayList punts_l) {
        this.nom_l = nom_l;
        this.punts_l = punts_l;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item_layout, parent, false);
        return new TextViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.textViewName.setText(String.valueOf(nom_l.get(position)));
        holder.textViewPoints.setText(String.valueOf(punts_l.get(position)));
    }

    @Override
    public int getItemCount() {
        return nom_l.size();
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPoints;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPoints = itemView.findViewById(R.id.text_view_points);
        }

    }
}
