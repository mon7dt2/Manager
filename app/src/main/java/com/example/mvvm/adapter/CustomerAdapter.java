package com.example.mvvm.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.mvvm.R;
import com.example.mvvm.base.BaseAdapter;
import com.example.mvvm.databinding.ItemCustomerBinding;
import com.example.mvvm.model.Customer;
import com.example.mvvm.model.data.CustomerPreview;
import com.example.mvvm.model.data.ProductPreview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mon7 on 10/4/2021.
 */
public class CustomerAdapter extends BaseAdapter<ItemCustomerBinding> implements Filterable{
    private static ClickListener clickListener;
    private List<CustomerPreview> customers = new ArrayList<>();
    private List<CustomerPreview> customersFull;

    public void setCustomers(List<CustomerPreview> customers) {
        this.customers = customers;
        notifyDataSetChanged();
        customersFull = new ArrayList<>(customers);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_customer;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ItemCustomerBinding binding) {
        return new CustomerViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((CustomerViewHolder) holder).bind(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers == null ? 0 : customers.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<CustomerPreview> customers1 = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){
                    customers1.addAll(customersFull);
                } else {
                    String filter = constraint.toString().toLowerCase().trim();
                    for (CustomerPreview customerPreview : customersFull){
                        if (customerPreview.getFullName().toLowerCase().contains(filter)) {
                            customers1.add(customerPreview);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = customers1;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                customers.clear();
                customers.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CustomerAdapter.clickListener = clickListener;
    }

    public class CustomerViewHolder extends BaseViewHolder<CustomerPreview> implements View.OnClickListener {
        ItemCustomerBinding binding;
        public CustomerViewHolder(ItemCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAbsoluteAdapterPosition(), v);
        }

        @Override
        protected void bind(CustomerPreview data) {
            String name = data.getFullName();
            binding.txtName.setText(name);
            String id = "ID: " + data.getId();
            binding.txtId.setText(id);
        }
    }
}
