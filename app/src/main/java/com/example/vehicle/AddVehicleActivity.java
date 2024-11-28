package com.example.vehicle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText contactNumber, name, city, adDate, price, make, model, transmission, fuelType, modelYear;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        contactNumber = findViewById(R.id.contactNumber);
        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        adDate = findViewById(R.id.adDate);
        price = findViewById(R.id.price);
        make = findViewById(R.id.make);
        model = findViewById(R.id.model);
        transmission = findViewById(R.id.transmission);
        fuelType = findViewById(R.id.fuelType);
        modelYear = findViewById(R.id.modelYear);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveVehicleData());
    }

    private void saveVehicleData() {
        String contact = contactNumber.getText().toString();
        String nameText = name.getText().toString();
        String cityText = city.getText().toString();
        String adDateText = adDate.getText().toString();
        String priceText = price.getText().toString();
        String makeText = make.getText().toString();
        String modelText = model.getText().toString();
        String transmissionText = transmission.getText().toString();
        String fuelTypeText = fuelType.getText().toString();
        String modelYearText = modelYear.getText().toString();

        if (contact.isEmpty() || nameText.isEmpty() || cityText.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");
        String vehicleId = databaseReference.push().getKey();

        Map<String, String> vehicleData = new HashMap<>();
        vehicleData.put("contact", contact);
        vehicleData.put("name", nameText);
        vehicleData.put("city", cityText);
        vehicleData.put("adDate", adDateText);
        vehicleData.put("price", priceText);
        vehicleData.put("make", makeText);
        vehicleData.put("model", modelText);
        vehicleData.put("transmission", transmissionText);
        vehicleData.put("fuelType", fuelTypeText);
        vehicleData.put("modelYear", modelYearText);

        databaseReference.child(vehicleId).setValue(vehicleData).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AddVehicleActivity.this, "Vehicle added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(AddVehicleActivity.this, "Failed to add vehicle", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
