package com.example.individualproject2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Retrieve the Car object from the intent
        Car car = (Car) getIntent().getSerializableExtra("car");

        Log.d("DetailActivity", "Received Car: " + car.getName() + ", " + car.getPrice());


        // Set up views
        ImageView detailImageView = findViewById(R.id.detailImageView);
        TextView detailNameTextView = findViewById(R.id.detailNameTextView);
        TextView detailPriceTextView = findViewById(R.id.detailPriceTextView);
        TextView detailBodyTypeTextView = findViewById(R.id.detailBodyTypeTextView);
        TextView detailEngineTypeTextView = findViewById(R.id.detailEngineTypeTextView);
        TextView detailEngineCCTextView = findViewById(R.id.detailEngineCCTextView);
        TextView detailKmRunTextView = findViewById(R.id.detailKmRunTextView);

        // Set values to views
        detailImageView.setImageResource(car.getImageResource());
        detailNameTextView.setText(car.getName());
        detailPriceTextView.setText(String.valueOf(car.getPrice()));
        detailBodyTypeTextView.setText("Body Type: " + car.getBodyType());
        detailEngineTypeTextView.setText("Engine Type: " + car.getEngineType());
        detailEngineCCTextView.setText("Engine CC: " + car.getEngineCC());
        detailKmRunTextView.setText("Km Run: " + car.getKmRun());


        // Buy Now Button
        Button buyNowButton = findViewById(R.id.buyNowButton);
        buyNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBuyNowClick(car);
            }
        });


    }

    public void onBuyNowClick(Car clickedCar) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("car", clickedCar);
        startActivity(intent);
    }
}

