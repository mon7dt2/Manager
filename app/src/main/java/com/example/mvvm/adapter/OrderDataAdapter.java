package com.example.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.model.data.OrderData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class OrderDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int TYPE_TITLE = 1;
    private static final int TYPE_DETAILS = 2;
    private List<OrderData> list;
    private LayoutInflater mInflater;

    public List<OrderData> getList() {
        return list;
    }

    public OrderDataAdapter(Context context, List<OrderData> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_TITLE){
            view = mInflater.inflate(R.layout.item_bold,parent,false);
            return new TitleViewHolder(view);
        } else {
            view = mInflater.inflate(R.layout.item_normal,parent,false);
            return new DetailViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_TITLE){
            ((TitleViewHolder) holder).setItem(list.get(position));
        } else {
            ((DetailViewHolder) holder).setItem(list.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTYPE() == 1) {
            return TYPE_TITLE;
        } else{
            return TYPE_DETAILS;
        }
    }

    @Override
    public int getItemCount() {
        return list ==null ? 0 : list.size();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView txtItemTitle;
        private TextView txtNumber;
        public TitleViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
            txtItemTitle = view.findViewById(R.id.txtItemTitle);
            txtNumber = view.findViewById(R.id.txtNumber);
        }
        public void setItem(OrderData orderData){
            txtItemTitle.setText(orderData.getTitle());
            txtNumber.setText(String.valueOf(orderData.getNumber()));
        }
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private TextView txtItemTitle;
        private TextView txtNumber;
        public DetailViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            view = itemView;
            txtItemTitle = view.findViewById(R.id.txtItemTitle);
            txtNumber = view.findViewById(R.id.txtNumber);
        }
        public void setItem(OrderData orderData){
            txtItemTitle.setText(orderData.getTitle());
            txtNumber.setText(String.valueOf(orderData.getNumber()));
        }
    }
}
