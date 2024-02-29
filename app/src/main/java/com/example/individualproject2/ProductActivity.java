package com.example.individualproject2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {
    private ArrayList<Car> carList;
    private EditText searchEditText;
    private Spinner sortSpinner;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        carList = generateCarList();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(carList, this);
        recyclerView.setAdapter(adapter);

        // Initialize search and sort views
        searchEditText = findViewById(R.id.searchEditText);
        sortSpinner = findViewById(R.id.sortSpinner);

        // Add a TextWatcher to the searchEditText
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterProducts(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Set up the sortSpinner
        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(
                this, R.array.sort_options, android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);

        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                sortProducts(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        if (position >= 0 && position < adapter.getItemCount()) {
            Car clickedCar = adapter.getClickedCar(position);
            Log.d("ProductActivity", "Clicked Car: " + clickedCar.getName() + ", " + clickedCar.getPrice());

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("car", clickedCar);
            startActivity(intent);
        } else {
            Log.e("ProductActivity", "Invalid position: " + position);
        }
    }








    private ArrayList<Car> generateCarList() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car(R.drawable.car1, "BMW", 200000.0, "Sedan", "Petrol", 2000, 50000.0));
        cars.add(new Car(R.drawable.car2, "Audi", 555000.0, "SUV", "Diesel", 2500, 30000.0));
        cars.add(new Car(R.drawable.car3, "Mercedes", 350000.0, "Coupe", "Petrol", 1800, 40000.0));
        cars.add(new Car(R.drawable.car4, "Nissan", 105000.0, "Hatchback", "Petrol", 1600, 80000.0));
        cars.add(new Car(R.drawable.car5, "Jeep", 505000.0, "SUV", "Petrol", 3500, 40000.0));
        cars.add(new Car(R.drawable.car6, "Range Rover", 705000.0, "SUV", "Diesel", 3000, 20000.0));
        cars.add(new Car(R.drawable.car5, "Toyota", 300000.0, "Hatchback", "Hybrid", 1500, 60000.0));
        cars.add(new Car(R.drawable.car8, "Honda", 180000.0, "Sedan", "Petrol", 2000, 70000.0));
        cars.add(new Car(R.drawable.car4, "Volkswagen", 250000.0, "Hatchback", "Diesel", 1600, 90000.0));
        return cars;
    }

    private void filterProducts(String searchText) {
        if (searchText.isEmpty()) {
            adapter.clearSearch();
        } else {
            ArrayList<Car> filteredList = new ArrayList<>();

            for (Car car : carList) {
                if (car.getName().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(car);
                }
            }

            adapter.filterList(filteredList);
        }
    }

    private void sortProducts(int option) {
        ArrayList<Car> filteredList = adapter.getFilteredCarList();

        switch (option) {
            case 0: // Sort by name
                Collections.sort(filteredList, (car1, car2) -> car1.getName().compareTo(car2.getName()));
                break;
            case 1: // Sort by price
                Collections.sort(filteredList, Comparator.comparingDouble(Car::getPrice));
                break;
        }

        adapter.notifyDataSetChanged();
    }
}
