package com.example.mvvm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.mvvm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class SpinnerAdapter extends ArrayAdapter<String>{
    private LayoutInflater layoutInflater;
    private List<String> list = new ArrayList<>();

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public SpinnerAdapter(@NonNull Context context) {
        super(context, R.layout.item_string);
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View v = layoutInflater.inflate(R.layout.item_selected, parent, false);
        String s = list.get(position);
        TextView tv = v.findViewById(R.id.txtItem);
        tv.setText(s);
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.item_string,parent, false);
        String s = list.get(position);
        TextView txtItem = view.findViewById(R.id.txtItem);
        txtItem.setText(s);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return list.get(position);
    }
}
