package com.anaskhaery.location_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    Spinner carSpinner, gasSpinner;
    ArrayList<String> cars = new ArrayList<>();
    ArrayList<String> gass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        carSpinner = findViewById(R.id.carSpinner);
        gasSpinner = findViewById(R.id.gasSpinner);

        cars.clear();
        gass.clear();
        Collections.addAll(cars, "Please, select your car", "car1", "car2", "car3");
        Collections.addAll(gass, "Please, select your car's fuel type", "80", "90", "95", "solar", "gas");

        //carList>>adapter>>spinner
        ArrayAdapter carsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cars);
        carSpinner.setAdapter(carsAdapter);

        //gassList>>adapter>>spinner
        ArrayAdapter gassAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gass);
        gasSpinner.setAdapter(gassAdapter);

    }

    public void next(View view) {
        String selectedCar = carSpinner.getSelectedItem().toString();
        String selectedGass = gasSpinner.getSelectedItem().toString();

        if (selectedCar.equalsIgnoreCase("Please, select your car")) {
            Toast.makeText(this, "Please choose a car", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedGass.equalsIgnoreCase("Please, select your car's fuel type")) {
            Toast.makeText(this, "Please choose a fuel type", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selected car", selectedCar);
        intent.putExtra("selected gas", selectedGass);
//        setResult(RESULT_OK, intent);
//        finish();
        startActivity(intent);

        carSpinner.setSelection(0);
        gasSpinner.setSelection(0);
    }

}