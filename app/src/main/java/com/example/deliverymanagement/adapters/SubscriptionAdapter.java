package com.example.deliverymanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SubscriptionAdapter extends ArrayAdapter<Integer> {
    private final List<Integer> subscriptionIds;

    public SubscriptionAdapter(Context context, List<Integer> subscriptionIds) {
        super(context, 0, subscriptionIds);
        this.subscriptionIds = subscriptionIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Integer subscriptionId = subscriptionIds.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(String.valueOf(subscriptionId));

        return convertView;
    }
}
