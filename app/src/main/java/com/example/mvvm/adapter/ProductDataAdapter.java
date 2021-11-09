package com.example.mvvm.adapter;

import android.view.View;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemBoldBinding;
import com.example.mvvm.model.data.OrderData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class ProductDataAdapter extends BaseAdapter<ItemBoldBinding>{

    private List<OrderData> data = new ArrayList<>();

    public void setData(List<OrderData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bold;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemBoldBinding binding) {
        return new ProductViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProductViewHolder extends BaseViewHolder<OrderData>{
        ItemBoldBinding binding;
        public ProductViewHolder(ItemBoldBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(OrderData data) {
            binding.txtItemTitle.setText(data.getTitle());
            binding.txtNumber.setText(String.valueOf(data.getNumber()));
        }
    }
}
