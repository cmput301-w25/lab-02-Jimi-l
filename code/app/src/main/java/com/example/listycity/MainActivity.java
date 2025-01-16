package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText editTextCity;
    Button confirmCityButton, deleteCityButton, addCityButton;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        cityList = findViewById(R.id.city_list);
        editTextCity = findViewById(R.id.editTextCity);
        confirmCityButton = findViewById(R.id.confirmCityButton);
        deleteCityButton = findViewById(R.id.deleteCityButton);
        addCityButton = findViewById(R.id.addCityButton);

        // Initialize city list
        String[] cities = {"Edmonton", "Montréal", "Vancouver"};
        dataList = new ArrayList<>();
        for (String city : cities) {
            dataList.add(city);
        }

        // Set up ListView with an adapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Add City Button: Show input and CONFIRM button
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCity.setVisibility(View.VISIBLE);
                confirmCityButton.setVisibility(View.VISIBLE);
                //editTextCity.requestFocus(); // Focus on the input field
                //editTextCity.setFocusable(true);//Ensures that the EditText can receive focus
                //editTextCity.setFocusableInTouchMode(true);
                //editTextCity.requestFocus();//Moves the focus to the EditText, allowing it to accept input.

            }
        });

        // Confirm Button: Add the city to the list
        confirmCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = editTextCity.getText().toString().trim();
                if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                    dataList.add(newCity);
                    cityAdapter.notifyDataSetChanged();
                }
                // Clear and hide input and CONFIRM button
                editTextCity.setText("");
                editTextCity.setVisibility(View.GONE);
                confirmCityButton.setVisibility(View.GONE);
            }
        });

        // Select City functionality
        cityList.setOnItemClickListener((parent, view, position, id) -> selectedPosition = position);

        // Delete City functionality
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged();
                    selectedPosition = -1; // Reset selection
                }
            }
        });
    }
}
