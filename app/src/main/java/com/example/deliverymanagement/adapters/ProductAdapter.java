package com.example.deliverymanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deliverymanagement.models.ProductModel;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Integer> {

    private final List<ProductModel> productIds;

    public ProductAdapter(Context context, List<ProductModel> productIds) {
        super(context, 0);
        this.productIds = productIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Integer productId = productIds.get(position).getId();
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(String.valueOf(productId));

        return convertView;
    }
}
