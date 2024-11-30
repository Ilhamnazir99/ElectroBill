package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Calculation_Guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_guide); // Your provided XML layout

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable back navigation on the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Bill Calculation Guide"); // Set toolbar title
        }

        // Add any additional logic here if required
        // For now, this class just manages the toolbar navigation.
    }

    // Enable back navigation when the toolbar's back button is pressed
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Close the activity and return to the previous screen
        return true;
    }
}
