package com.example.mvvm.adapter;

import android.view.View;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemCategoryBinding;
import com.example.mvvm.databinding.ItemOrderBinding;
import com.example.mvvm.model.Order;
import com.example.mvvm.model.data.OrderPreview;
import com.example.mvvm.utils.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mon7 on 10/1/2021.
 */
public class OrderAdapter extends BaseAdapter<ItemOrderBinding>{
    private static ClickListener clickListener;
    private List<OrderPreview> orders = new ArrayList<>();

    public void setOrders(List<OrderPreview> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_order;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemOrderBinding binding) {
        return new OrderViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((OrderViewHolder) holder).bind(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders==null ? 0 : orders.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        OrderAdapter.clickListener = clickListener;
    }

    public class OrderViewHolder extends BaseViewHolder<OrderPreview> implements View.OnClickListener {
        ItemOrderBinding binding;
        public OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(), v);
        }

        @Override
        protected void bind(OrderPreview data) {
            binding.txtItemTitle.setText(String.valueOf(data.getId()));

            Long time = data.getCreatedAt();
            Date date = new Date(time);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateTime = dateFormat.format(date);
            binding.txtNumber.setText(dateTime);

            String state = "";
            switch (data.getIsCheck()){
                case -1:
                    state = "Đã hủy";
                    break;
                case 1:
                    state = "Đã xử lý";
                    break;
                case 0:
                    state = "Đang xử lý";
                    break;
                default:
                    state = "loading...";
            }
            binding.txtState.setText(state);
        }
    }
}
