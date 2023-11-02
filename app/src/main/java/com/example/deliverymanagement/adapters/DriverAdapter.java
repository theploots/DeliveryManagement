package com.example.deliverymanagement.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverymanagement.R;
import com.example.deliverymanagement.models.DriverModel;

import java.util.ArrayList;
import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverHolder> {

    private List<DriverModel> allDrivers = new ArrayList<>();

    @NonNull
    @Override
    public DriverHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.list_item, null);

        return new DriverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverHolder holder, int position) {
        DriverModel currentDriver = allDrivers.get(position);
        holder.textViewDriverName.setText(currentDriver.getFirstName());
    }

    @Override
    public int getItemCount() {
        return allDrivers.size();
    }

    public void setAllDrivers(List<DriverModel> allDrivers) {
        this.allDrivers = allDrivers;
        notifyDataSetChanged();
    }

    class DriverHolder extends RecyclerView.ViewHolder {
        private TextView textViewDriverName;
        public DriverHolder(@NonNull View itemView) {
            super(itemView);
            textViewDriverName = itemView.findViewById(R.id.IdTextView);

        }
    }
}
