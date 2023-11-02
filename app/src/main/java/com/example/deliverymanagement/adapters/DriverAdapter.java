package com.example.deliverymanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.models.DriverModel;

import java.util.ArrayList;
import java.util.List;

public class DriverAdapter extends ArrayAdapter<Integer> {

    private final List<Integer> driverIds;

    public DriverAdapter(Context context, List<Integer> driverIds) {
        super(context, 0, driverIds);
        this.driverIds = driverIds;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        Integer driverId = driverIds.get(position);
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(String.valueOf(driverId));

        return convertView;
    }


}
