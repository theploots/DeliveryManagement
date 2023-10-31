//package com.example.deliverymanagement.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.deliverymanagement.R;
//import com.example.deliverymanagement.models.ClientModel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientHolder>{
//
//    private List<ClientModel> allClients = new ArrayList<>();
//
////    @NonNull
////    @Override
////    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        //create client_item.xml
////       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
////        return new ClientHolder(view) ;
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    class ClientHolder extends RecyclerView.ViewHolder {
//        private TextView textViewFirstName;
//        private TextView textViewLastName;
//        private TextView textViewAddress;
//        public ClientHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewFirstName = itemView.findViewById(R.id.textViewFirstName);
//            textViewLastName = itemView.findViewById(R.id.textViewLastName);
//            textViewAddress = itemView.findViewById(R.id.textViewAddress);
//        }
//    }
//}
