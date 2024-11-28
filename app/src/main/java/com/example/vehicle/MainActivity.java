package com.example.vehicle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView userNameTextView;
    private Button logoutButton, addVehicleButton;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;
    private List<Vehicle> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Vehicle");

        userNameTextView = findViewById(R.id.userNameTextView);
        logoutButton = findViewById(R.id.logoutButton);
        addVehicleButton = findViewById(R.id.addVehicleButton);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);

        if (currentUser != null) {
            loadUserName(currentUser.getUid());
        }

        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        addVehicleButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddVehicleActivity.class));
        });

        setupRecyclerView();
    }

    private void loadUserName(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        userRef.child("fullName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullName = dataSnapshot.getValue(String.class);
                if (fullName != null) {
                    userNameTextView.setText(fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Failed to load user name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        vehicleList = new ArrayList<>();
        vehicleAdapter = new VehicleAdapter(this, vehicleList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(vehicleAdapter);

        fetchVehicleData();
    }

    private void fetchVehicleData() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vehicleList.clear();
                for (DataSnapshot vehicleSnapshot : snapshot.getChildren()) {
                    Vehicle vehicle = vehicleSnapshot.getValue(Vehicle.class);
                    if (vehicle != null) {
                        vehicleList.add(vehicle);
                    }
                }
                vehicleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load vehicles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
