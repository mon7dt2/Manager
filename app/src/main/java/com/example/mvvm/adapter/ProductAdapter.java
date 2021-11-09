package com.example.mvvm.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemProductBinding;
import com.example.mvvm.model.Product;
import com.example.mvvm.model.data.ProductPreview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mon7 on 10/10/2021.
 */
public class ProductAdapter extends BaseAdapter<ItemProductBinding> implements Filterable{
    private static ClickListener clickListener;
    private List<ProductPreview> products = new ArrayList<>();
    private List<ProductPreview> productsFull;

    public void setProducts(List<ProductPreview> products) {
        this.products = products;
        notifyDataSetChanged();
        productsFull = new ArrayList<>(products);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_product;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemProductBinding binding) {
        return new ProductViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((ProductViewHolder) holder).bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ProductPreview> products1 = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    products1.addAll(productsFull);
                } else {
                    String filter = constraint.toString().toLowerCase().trim();
                    for (ProductPreview product : productsFull){
                        Log.d("myLog", product.getName());
                        if (product.getName().toLowerCase().contains(filter)) {
                            products1.add(product);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = products1;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                products.clear();
                products.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ProductAdapter.clickListener = clickListener;
    }

    public class ProductViewHolder extends BaseViewHolder<ProductPreview> implements View.OnClickListener{
        ItemProductBinding binding;
        public ProductViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bind(ProductPreview data) {
            binding.tNameProduct.setText(data.getName());
            binding.txtId.setText(String.valueOf(data.getPrice()));
            binding.txtNumber.setText(String.valueOf(data.getQuantity()));
            binding.txtCategory.setText(data.getCategory());
            Random r = new Random();
            int token = r.nextInt();
            Glide.with(itemView.getContext())
                    .load(data.getLogoUrl() + "?" + token)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .placeholder(R.drawable.logozune)
                    .error(R.drawable.defaultimg)
                    .into(binding.imgProduct);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(), v);
        }
    }
}
