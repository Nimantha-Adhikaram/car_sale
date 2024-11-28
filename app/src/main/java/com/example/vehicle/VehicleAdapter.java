package com.example.vehicle;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private final Context context;
    private final List<Vehicle> vehicleList;

    public VehicleAdapter(Context context, List<Vehicle> vehicleList) {
        this.context = context;
        this.vehicleList = vehicleList;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        Vehicle vehicle = vehicleList.get(position);

        holder.modelTextView.setText(vehicle.getModel());
        holder.priceTextView.setText(vehicle.getPrice());
        holder.fuelTypeTextView.setText("Fuel: " + vehicle.getFuelType());
        holder.cityTextView.setText("City: " + vehicle.getCity());
        holder.makeTextView.setText(vehicle.getMake()); // Updated to bind the correct make
        holder.adDateTextView.setText("Ad Date: " + vehicle.getAdDate());

        holder.itemView.setOnClickListener(v -> showVehicleDetailsDialog(vehicle));
    }

    private void showVehicleDetailsDialog(Vehicle vehicle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_vehicle_details, null);

        ImageView vehicleImageView = dialogView.findViewById(R.id.dialogVehicleImage);
        TextView nameTextView = dialogView.findViewById(R.id.dialogName);
        TextView modelTextView = dialogView.findViewById(R.id.dialogModel);
        TextView modelYearTextView = dialogView.findViewById(R.id.dialogModelYear);
        TextView priceTextView = dialogView.findViewById(R.id.dialogPrice);
        TextView fuelTypeTextView = dialogView.findViewById(R.id.dialogFuelType);
        TextView transmissionTextView = dialogView.findViewById(R.id.dialogTransmission);
        TextView cityTextView = dialogView.findViewById(R.id.dialogCity);
        TextView contactTextView = dialogView.findViewById(R.id.dialogContact);
        TextView makeTextView = dialogView.findViewById(R.id.dialogMake);
        TextView adDateTextView = dialogView.findViewById(R.id.dialogAdDate);

        // Set values to dialog components
        vehicleImageView.setImageResource(vehicle.getImageResource());
        nameTextView.setText(vehicle.getName());
        modelTextView.setText(vehicle.getModel());
        modelYearTextView.setText(vehicle.getModelYear());
        priceTextView.setText(vehicle.getPrice());
        fuelTypeTextView.setText(vehicle.getFuelType());
        transmissionTextView.setText(vehicle.getTransmission());
        cityTextView.setText(vehicle.getCity());
        contactTextView.setText(vehicle.getContact());
        makeTextView.setText(vehicle.getMake()); // Updated to bind the correct make
        adDateTextView.setText(vehicle.getAdDate());

        builder.setView(dialogView);
        builder.setPositiveButton("Close", null);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return vehicleList.size();
    }

    static class VehicleViewHolder extends RecyclerView.ViewHolder {
        TextView modelTextView, priceTextView, fuelTypeTextView, cityTextView, adDateTextView, makeTextView;

        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            modelTextView = itemView.findViewById(R.id.vehicleNameTextView);
            priceTextView = itemView.findViewById(R.id.vehiclePriceTextView);
            fuelTypeTextView = itemView.findViewById(R.id.vehicleFuelTypeTextView);
            cityTextView = itemView.findViewById(R.id.vehicleCityTextView);
            adDateTextView = itemView.findViewById(R.id.vehicleAdDateTextView);
            makeTextView = itemView.findViewById(R.id.vehicleMakeTextView); // Added makeTextView
        }
    }
}
