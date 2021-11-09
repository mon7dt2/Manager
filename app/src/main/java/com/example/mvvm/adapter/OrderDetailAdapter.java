package com.example.mvvm.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemProductOrderBinding;
import com.example.mvvm.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mon7 on 10/3/2021.
 */
public class OrderDetailAdapter extends BaseAdapter<ItemProductOrderBinding>{
    private List<OrderDetail> details = new ArrayList<>();

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_product_order;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemProductOrderBinding binding) {
        return new OrderDetailViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((OrderDetailViewHolder) holder).bind(details.get(position));
    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    public class OrderDetailViewHolder extends BaseViewHolder<OrderDetail>{
        ItemProductOrderBinding binding;
        public OrderDetailViewHolder(ItemProductOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(OrderDetail data) {
            binding.txtQuantity.setText(String.valueOf(data.getQuantity()));
            binding.txtSizeProduct.setText(data.getSize());
            binding.txtNameProduct.setText(data.getProductName());
            binding.txtPriceProduct.setText(String.valueOf(data.getPrice()));

            Random r = new Random();
            int token = r.nextInt();
            Glide.with(itemView.getContext())
                    .load(data.getProductAvatarUrl() + "?" + token)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .placeholder(R.drawable.logozune)
                    .error(R.drawable.defaultimg)
                    .into(binding.imgProduct);
        }
    }
}
