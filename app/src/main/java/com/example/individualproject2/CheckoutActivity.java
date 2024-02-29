package com.example.individualproject2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        Car car = (Car) getIntent().getSerializableExtra("car");

        TextView carNameTextView = findViewById(R.id.checkoutCarNameTextView);
        TextView carPriceTextView = findViewById(R.id.checkoutCarPriceTextView);

        carNameTextView.setText("Car Name: " + car.getName());
        carPriceTextView.setText("Car Price: " + car.getPrice());
    }

    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onBuyNowClick(View view) {
        // Retrieve values from the form fields
        String customerName = ((EditText) findViewById(R.id.customerNameEditText)).getText().toString().trim();
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString().trim();
        String street = ((EditText) findViewById(R.id.streetEditText)).getText().toString().trim();
        String city = ((EditText) findViewById(R.id.cityEditText)).getText().toString().trim();
        String province = ((EditText) findViewById(R.id.provinceEditText)).getText().toString().trim();
        String country = ((EditText) findViewById(R.id.countryEditText)).getText().toString().trim();
        String cardNumber = ((EditText) findViewById(R.id.cardNumberEditText)).getText().toString().trim();
        String cvc = ((EditText) findViewById(R.id.cvcEditText)).getText().toString().trim();



        // Check if any of the fields is empty
        if (customerName.isEmpty() || email.isEmpty() || street.isEmpty() || city.isEmpty() || province.isEmpty() || country.isEmpty() || cardNumber.isEmpty() || cvc.isEmpty()) {
            // Display a warning message if any field is empty
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
        } else if (cardNumber.length() != 16) {
            Toast.makeText(this, "Please enter 16 digit card number", Toast.LENGTH_SHORT).show();
        }else if (cvc.length() < 3 || cvc.length() > 4) {
            Toast.makeText(this, "Please enter valid CVC", Toast.LENGTH_SHORT).show();
        } else {
            // Display a success message
            String successMessage = "Thank you, " + customerName + "! Your purchase was successful.";
            Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();

            // Redirect to the homepage (MainActivity)
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

}

