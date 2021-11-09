package com.example.mvvm.adapter;

import android.view.View;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemCategoryBinding;
import com.example.mvvm.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 9/30/2021.
 */
public class CategoryAdapter extends BaseAdapter<ItemCategoryBinding>{
    private static ClickListener clickListener;
    private List<Category> categories = new ArrayList<>();

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_category;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemCategoryBinding binding) {
        return new CategoryViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((CategoryViewHolder) holder).bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories==null ? 0 : categories.size();
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CategoryAdapter.clickListener = clickListener;
    }

    public class CategoryViewHolder extends BaseViewHolder<Category> implements View.OnClickListener{
        ItemCategoryBinding binding;
        public CategoryViewHolder(ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bind(Category data) {
            binding.txtItemTitle.setText(data.getTitle());
            //binding.txtNumber.setText(String.valueOf(data.getQuantity()));
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(), v);
        }
    }
}
