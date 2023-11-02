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

public class RouteAdapter extends ArrayAdapter<Integer> {

    private final List<Integer> routeIds;

    public RouteAdapter(Context context, List<Integer> routeIds) {
        super(context, 0, routeIds);
        this.routeIds = routeIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Integer routeId = routeIds.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(String.valueOf(routeId));

        return convertView;
    }
}
