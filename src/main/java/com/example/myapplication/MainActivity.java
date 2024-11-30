package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etUnits, etRebate;
    private TextView tvResult;
    private Button btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge UI
        getWindow().setDecorFitsSystemWindows(false);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        etUnits = findViewById(R.id.etUnits);
        etRebate = findViewById(R.id.etRebate);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        // Set the initial result to 0
        tvResult.setText("Total Charges(before Rebates): RM 0.00\nFinal Charges(After Rebate): RM 0.00");

        // Set onClickListener for the Calculate button
        btnCalculate.setOnClickListener(v -> calculateBill());

        // Set onClickListener for the Clear button
        btnClear.setOnClickListener(v -> clearFields());
    }

    // Method to calculate electricity bill
    private void calculateBill() {
        String unitStr = etUnits.getText().toString();
        String rebateStr = etRebate.getText().toString();

        // Validate the 'units' input (make rebate optional)
        if (unitStr.isEmpty()) {
            Toast.makeText(this, "Please enter valid units.", Toast.LENGTH_SHORT).show();
            return;
        }

        int units;
        double rebate = 0;  // Default value if rebate is not provided

        try {
            units = Integer.parseInt(unitStr);

            // If rebateStr is not empty, convert it to a percentage
            if (!rebateStr.isEmpty()) {
                rebate = Double.parseDouble(rebateStr) / 100;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format. Please check your inputs.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check for valid units and rebate range
        if (units < 0) {
            Toast.makeText(this, "Units must be positive.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rebate < 0 || rebate > 0.05) {
            Toast.makeText(this, "Rebate should be between 0% and 5%.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate charges based on blocks
        double totalCharges = 0;

        if (units > 600) {
            totalCharges += (units - 600) * 0.546;
            units = 600;
        }
        if (units > 300) {
            totalCharges += (units - 300) * 0.516;
            units = 300;
        }
        if (units > 200) {
            totalCharges += (units - 200) * 0.334;
            units = 200;
        }
        totalCharges += units * 0.218;

        // Apply rebate
        double finalCost = totalCharges - (totalCharges * rebate);

        // Display the result
        tvResult.setText(String.format("Total Charges(before Rebates): RM %.2f\nFinal Charges(After Rebate) RM %.2f", totalCharges, finalCost));
    }

    // Method to clear fields
    private void clearFields() {
        etUnits.setText("");
        etRebate.setText("");
        tvResult.setText("Total Charges: RM 0.00\nAfter Rebate: RM 0.00"); // Reset to initial value
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu from XML
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selections
        if (item.getItemId() == R.id.menu_about) {
            // Navigate to About activity
            startActivity(new Intent(MainActivity.this, About.class));
            return true;
        } else if (item.getItemId() == R.id.menu_calculation_guide) {
            // Navigate to Calculation Guide activity
            startActivity(new Intent(MainActivity.this, Calculation_Guide.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
